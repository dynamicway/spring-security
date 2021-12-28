package me.spring.security.security.testdouble;

import me.spring.security.util.TimeProvider;

import java.util.Date;

public class SpyTimeProvider implements TimeProvider {

    public Date now_returns;
    public Date nextWeek_returns;

    @Override
    public Date now() {
        return now_returns;
    }

    @Override
    public Date nextWeek() {
        return nextWeek_returns;
    }
}
