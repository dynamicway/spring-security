package me.spring.security.security;

import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
public class UserAuthenticationToken {
    private String email;
    private String password;
}
