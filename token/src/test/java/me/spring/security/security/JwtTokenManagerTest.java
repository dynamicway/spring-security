package me.spring.security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.Base64UrlCodec;
import me.spring.security.security.testdouble.SpyTimeProvider;
import me.spring.security.user.UserEntity;
import me.spring.security.user.UserRoleEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenManagerTest {

    private final JwtParser jwtParser = Jwts.parser()
            .setSigningKey(Base64UrlCodec.BASE64.decode("THIS_IS_SECRET_KEY"));
    private JwtTokenManager jwtTokenManager;
    private SpyTimeProvider spyTimeProvider;

    @BeforeEach
    void setUp() {
        spyTimeProvider = new SpyTimeProvider();
        jwtTokenManager = new JwtTokenManagerImpl(spyTimeProvider);
    }

    @Test
    void generateJwt() {
        UserAuthenticationToken givenUserAuthenticationToken = new UserAuthenticationToken();
        UserEntity givenUserEntity = new UserEntity(
                "email",
                "password",
                LocalDate.of(2021, 12, 24)
        );
        Date given_expiration = new Date(2000000000000L);
        Date given_issuedAt = new Date(2000100000000L);

        spyTimeProvider.now_returns = given_issuedAt;
        spyTimeProvider.nextWeek_returns = given_expiration;
        givenUserEntity.getRoles().add(new UserRoleEntity(1L, UserRoleEntity.Role.USER));

        givenUserAuthenticationToken.applyPrincipal(givenUserEntity);
        String generateJwt_returns = jwtTokenManager.generateJwt(givenUserAuthenticationToken);

        Jws<Claims> claims = jwtParser.parseClaimsJws(generateJwt_returns);

        var header = claims.getHeader();
        assertThat(header.getType()).isEqualTo("JWT");
        assertThat(header.getAlgorithm()).isEqualTo("HS256");

        Claims body = claims.getBody();

        assertThat(body.getSubject()).isEqualTo(givenUserEntity.getEmail());
        assertThat(body.getExpiration()).isEqualTo(given_expiration);
        assertThat(body.getIssuedAt()).isEqualTo(given_issuedAt);
        assertThat(body.get("birth")).isEqualTo(givenUserEntity.getBirth().toString());

        assertThat(jwtParser.isSigned(generateJwt_returns)).isTrue();
    }

}
