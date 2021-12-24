package me.spring.security.security;

import me.spring.security.error.BadCredentialException;
import me.spring.security.error.NotFoundEntityException;
import me.spring.security.security.testdouble.SpyBCryptPasswordEncoder;
import me.spring.security.security.testdouble.SpyUserRepository;
import me.spring.security.user.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserAuthenticationProviderTest {

    private UserAuthenticationProviderImpl userAuthenticationProvider;
    private SpyUserRepository spyUserRepository;
    private SpyBCryptPasswordEncoder spyBCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        spyUserRepository = new SpyUserRepository();
        spyBCryptPasswordEncoder = new SpyBCryptPasswordEncoder();
        userAuthenticationProvider = new UserAuthenticationProviderImpl(spyUserRepository, spyBCryptPasswordEncoder);
    }

    @Test
    void authenticate_returnsAuthenticatedToken() {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken();
        authenticationToken.getUserAuthorities().add(new SimpleGrantedAuthority("USER"));
        spyUserRepository.findByEmail_returns = new UserEntity();
        spyBCryptPasswordEncoder.matches_returns = true;
        UserAuthenticationToken authenticate_returns = userAuthenticationProvider.authenticate(authenticationToken);
        assertThat(authenticate_returns.isAuthenticated()).isTrue();
        assertThat(authenticate_returns.getUserEntity()).isEqualTo(spyUserRepository.findByEmail_returns);
    }

    @Test
    void authenticate_callsFindByEmail_inUserRepository() {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken();
        spyUserRepository.findByEmail_returns = new UserEntity();
        spyBCryptPasswordEncoder.matches_returns = true;
        userAuthenticationProvider.authenticate(authenticationToken);
        assertThat(spyUserRepository.findByEmail_isCalled).isTrue();
    }

    @Test
    void authenticate_throwsNotFoundEntityException_when_userIsNotExists() {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken();
        spyUserRepository.findByEmail_returns = null;
        assertThatThrownBy(() -> userAuthenticationProvider.authenticate(authenticationToken))
                .isInstanceOf(NotFoundEntityException.class)
                .hasMessage("not found user");
    }

    @Test
    void authenticate_callsMatches_inBCryptPasswordEncoder() {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken();
        spyUserRepository.findByEmail_returns = new UserEntity();
        spyBCryptPasswordEncoder.matches_returns = true;
        userAuthenticationProvider.authenticate(authenticationToken);
        assertThat(spyBCryptPasswordEncoder.matches_isCalled).isTrue();
    }

    @Test
    void authenticate_throwsBadCredentialException_when_matchesReturnsFalse() {
        UserAuthenticationToken authenticationToken = new UserAuthenticationToken();
        spyUserRepository.findByEmail_returns = new UserEntity();
        assertThatThrownBy(() -> userAuthenticationProvider.authenticate(authenticationToken))
                .isInstanceOf(BadCredentialException.class)
                .hasMessage("invalid password");
    }

}
