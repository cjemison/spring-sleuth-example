package com.cjemison.personExample.util;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by cjemison on 4/8/16.
 */
public class DateTimeUtilImplTest {
    @Test
    public void getCurrentUTCTime1() throws Exception {
        DateTimeUtil dateTimeUtil = DateTimeUtil.DEFAULT;
        assertThat(dateTimeUtil.getCurrentUTCTime(), is(notNullValue()));
        assertThat(dateTimeUtil.getCurrentUTCTime() instanceof DateTime, is(equalTo(true)));
    }

}