package com.cjemison.personExample.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

/**
 * Created by cjemison on 4/8/16.
 */
@Component
public class DateTimeUtilImpl implements DateTimeUtil {

    @Override
    public DateTime getCurrentUTCTime() {
        return new DateTime(DateTimeZone.UTC);
    }
}
