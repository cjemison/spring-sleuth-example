package com.cjemison.personExample.util;

import com.cjemison.personExample.util.domain.RequiredAttributeException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by cjemison on 4/8/16.
 */
@Component
public class ValidatorUtilImpl implements ValidatorUtil {

    @Override
    public <T> void validateCollection(final Collection<T> collection,
                                       final String message) throws RequiredAttributeException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new RequiredAttributeException(message);
        }

    }

    @Override
    public void validateObject(final Object obj,
                               final String message) throws RequiredAttributeException {
        if (obj == null) {
            throw new RequiredAttributeException(message);
        }
    }

    @Override
    public void validateString(final String value,
                               final String message) throws RequiredAttributeException {
        if (StringUtils.isBlank(value)) {
            throw new RequiredAttributeException(message);
        }
    }
}
