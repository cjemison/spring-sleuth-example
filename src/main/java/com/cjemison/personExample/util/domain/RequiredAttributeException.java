package com.cjemison.personExample.util.domain;

/**
 * Created by cjemison on 4/12/16.
 */
public class RequiredAttributeException extends RuntimeException {

    public RequiredAttributeException(String message) {
        super(message);
    }
}
