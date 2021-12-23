package me.spring.security.error;

import org.springframework.security.core.AuthenticationException;

public class BadCredentialException extends AuthenticationException {
    public BadCredentialException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BadCredentialException(String msg) {
        super(msg);
    }
}
