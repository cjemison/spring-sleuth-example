package com.cjemison.personExample.service;

import com.cjemison.personExample.persistence.model.PersonEO;
import com.cjemison.personExample.service.domain.PersonNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Created by cjemison on 4/8/16.
 */
public interface PersonService {

    List<PersonEO> findAll();

    Optional<PersonEO> findById(final String id) throws PersonNotFoundException;

    Optional<PersonEO> create(final PersonEO PersonEO);

    Optional<PersonEO> update(final PersonEO personEO) throws PersonNotFoundException;

    boolean delete(final String id) throws PersonNotFoundException;
}
