package me.spring.security.security;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import me.spring.security.util.TimeProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenManagerImpl implements JwtTokenManager {

    private final String SECRET_KEY = "THIS_IS_SECRET_KEY";
    private final TimeProvider timeProvider;

    @Override
    public String generateJwt(UserAuthenticationToken authentication) {

        return Jwts.builder()
                .setClaims(createPrivateClaims(authentication))
                .setSubject(authentication.getEmail())
                .setHeader(createHeader())
                .setExpiration(timeProvider.nextWeek())
                .setIssuedAt(timeProvider.now())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    @Override
    public void valid(String token) {
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put(Header.TYPE, Header.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, SignatureAlgorithm.HS256.getValue());
        return header;
    }

    private Map<String, Object> createPrivateClaims(UserAuthenticationToken authenticationToken) {
        HashMap<String, Object> privateClaims = new HashMap<>();
        privateClaims.put("birth", authenticationToken.getBirth().toString());
        return privateClaims;
    }

}
