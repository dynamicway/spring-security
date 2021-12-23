package me.spring.security.error;

public class HasNotUserRoleException extends RuntimeException {
    public HasNotUserRoleException(String message) {
        super(message);
    }
}
