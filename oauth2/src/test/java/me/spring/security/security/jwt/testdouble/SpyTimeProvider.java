package me.spring.security.security.jwt.testdouble;

import me.spring.security.util.TimeProvider;

import java.util.Date;

public class SpyTimeProvider implements TimeProvider {

    @Override
    public Date now() {
        return null;
    }

    @Override
    public Date nextWeek() {
        return null;
    }

    @Override
    public Date tomorrow() {
        return null;
    }

}
