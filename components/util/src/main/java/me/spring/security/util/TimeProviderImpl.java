package me.spring.security.util;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TimeProviderImpl implements TimeProvider {

    @Override
    public Date now() {
        return new Date();
    }

    @Override
    public Date nextWeek() {
        return new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7));
    }
}
