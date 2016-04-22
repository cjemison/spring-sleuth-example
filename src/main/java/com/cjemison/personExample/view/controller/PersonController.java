package com.cjemison.personExample.view.controller;

import com.cjemison.personExample.view.domain.PersonVO;
import org.springframework.http.ResponseEntity;

/**
 * Created by cjemison on 4/9/16.
 */
public interface PersonController {

    ResponseEntity<?> findAll();

    ResponseEntity<?> findById(final String id);

    ResponseEntity<?> createPersonVO(final PersonVO personVO);

    ResponseEntity<?> updatePersonVO(final String id,
                                     final PersonVO personVO);

    ResponseEntity<?> deletePerson(final String id);
}
