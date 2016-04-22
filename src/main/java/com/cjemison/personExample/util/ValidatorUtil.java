package com.cjemison.personExample.util;

import com.cjemison.personExample.util.domain.RequiredAttributeException;

import java.util.Collection;

/**
 * Created by cjemison on 4/8/16.
 */
public interface ValidatorUtil {

    ValidatorUtil DEFAULT = new ValidatorUtilImpl();

    <T> void validateCollection(final Collection<T> collection,
                                final String message) throws RequiredAttributeException;

    void validateObject(final Object obj,
                        final String message) throws RequiredAttributeException;

    void validateString(final String value,
                        final String message) throws RequiredAttributeException;
}
