package com.cjemison.personExample.persistence.domain;

import com.cjemison.personExample.util.ValidatorUtil;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by cjemison on 4/12/16.
 */
public abstract class AbstractBuilder {
    private final ValidatorUtil validator;

    public AbstractBuilder(final ValidatorUtil validator) {
        Objects.requireNonNull(validator);
        this.validator = validator;
    }

    protected <T> void validateCollection(final Collection<T> collection,
                                          final String fieldName) {
        validator.validateCollection(collection, build(fieldName));
    }

    protected void validateObject(final Object o,
                                  final String fieldName) {
        validator.validateObject(o, build(fieldName));
    }

    protected void validateString(final String value,
                                  final String fieldName) {
        validator.validateString(value, build(fieldName));
    }

    protected String build(final String fieldName) {
        validator.validateString(fieldName, "fieldName is a required field. It cannot be null or empty.");
        return String.format("%s is a required field. It cannot be null or empty", fieldName);
    }
}
