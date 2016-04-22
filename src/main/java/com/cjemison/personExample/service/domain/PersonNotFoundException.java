package com.cjemison.personExample.service.domain;

/**
 * Created by cjemison on 4/9/16.
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
