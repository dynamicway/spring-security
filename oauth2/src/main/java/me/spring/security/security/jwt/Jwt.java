package me.spring.security.security.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Jwt {
    private final String accessToken;
    private final String refreshToken;
}
