package com.cjemison.personExample.util;

import org.joda.time.DateTime;

/**
 * Created by cjemison on 4/8/16.
 */
public interface DateTimeUtil {

    DateTimeUtil DEFAULT = new DateTimeUtilImpl();

    DateTime getCurrentUTCTime();
}
