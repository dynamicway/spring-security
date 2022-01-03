package me.spring.security.util;

import java.util.Date;

public interface TimeProvider {

    Date now();

    Date nextWeek();

    Date tomorrow();

}
