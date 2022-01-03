package me.spring.security.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import lombok.RequiredArgsConstructor;
import me.spring.security.util.TimeProvider;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtManagerImpl implements JwtManager {

    private final String SECRET_KEY = "THIS_IS_SECRET_KEY";
    private final TimeProvider timeProvider;
    private final JwtParser jwtParser = Jwts.parser().setSigningKey(Base64UrlCodec.BASE64.decode(SECRET_KEY));

    @Override
    public Jwt generateJwt(OAuth2User oAuth2User) {
        String accessToken = Jwts.builder()
                .setClaims(createPrivateClaims(oAuth2User))
                .setSubject(oAuth2User.getName())
                .setHeader(createHeader())
                .setExpiration(timeProvider.tomorrow())
                .setIssuedAt(timeProvider.now())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        String refreshToken = Jwts.builder()
                .setClaims(createPrivateClaims(oAuth2User))
                .setSubject(oAuth2User.getName())
                .setHeader(createHeader())
                .setExpiration(timeProvider.nextWeek())
                .setIssuedAt(timeProvider.now())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return new Jwt(accessToken, refreshToken);
    }

    @Override
    public void valid(String token) {
        token = token.replace("Bearer ", "");
        jwtParser.parseClaimsJws(token);
        if (!jwtParser.isSigned(token)) throw new RuntimeException();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put(Header.TYPE, Header.JWT_TYPE);
        header.put(JwsHeader.ALGORITHM, SignatureAlgorithm.HS256.getValue());
        return header;
    }

    private Map<String, Object> createPrivateClaims(OAuth2User oAuth2User) {
        Map<String, Object> privateClaims = new HashMap<>();
        privateClaims.put("nickname", oAuth2User.getAttribute("nickname"));
        privateClaims.put("birth", oAuth2User.getAttribute("birth"));
        privateClaims.put("thumbnail", oAuth2User.getAttribute("thumbnail"));
        return privateClaims;
    }

}
