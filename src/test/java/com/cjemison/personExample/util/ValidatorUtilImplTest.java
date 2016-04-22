package com.cjemison.personExample.util;

import com.cjemison.personExample.util.domain.RequiredAttributeException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cjemison on 4/8/16.
 */
public class ValidatorUtilImplTest {
    private ValidatorUtil validatorUtil = ValidatorUtil.DEFAULT;

    @Test(expected = RequiredAttributeException.class)
    public void validateObject_nullValue_happyPath() throws Exception {
        Object object = null;
        validatorUtil.validateObject(object, "blah blah blah");
    }

    @Test
    public void validateObject_happyPath() throws Exception {
        String object = "";
        validatorUtil.validateObject(object, "blah blah blah");
    }

    @Test(expected = RequiredAttributeException.class)
    public void validateString_emptyString_happyPath() throws Exception {
        String object = "";
        validatorUtil.validateString(object, "blah blah blah");
    }

    @Test(expected = RequiredAttributeException.class)
    public void validateString_blanks_happyPath() throws Exception {
        String object = "    ";
        validatorUtil.validateString(object, "blah blah blah");
    }

    @Test
    public void validateString_happyPath() throws Exception {
        String object = "abcd1234";
        validatorUtil.validateString(object, "blah blah blah");
    }

    @Test
    public void validateCollection_happyPath() throws Exception {
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        validatorUtil.validateCollection(stringList, "blah blah blah");
    }

    @Test(expected = RequiredAttributeException.class)
    public void validateCollection_emptyList_happyPath() throws Exception {
        List<String> stringList = new ArrayList<>();
        validatorUtil.validateCollection(stringList, "blah blah blah");
    }

    @Test(expected = RequiredAttributeException.class)
    public void validateCollection_nullList_happyPath() throws Exception {
        List<String> stringList = null;
        validatorUtil.validateCollection(stringList, "blah blah blah");
    }
}