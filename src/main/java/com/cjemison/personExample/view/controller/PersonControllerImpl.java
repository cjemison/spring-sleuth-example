package com.cjemison.personExample.view.controller;

import com.cjemison.personExample.persistence.model.PersonEO;
import com.cjemison.personExample.service.PersonService;
import com.cjemison.personExample.util.Constants;
import com.cjemison.personExample.util.ValidatorUtil;
import com.cjemison.personExample.view.assembler.PersonAssembler;
import com.cjemison.personExample.view.domain.PersonVO;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by cjemison on 4/9/16.
 *
 * @todo needs global error controller
 */
@RestController
@RequestMapping("/v1")
public class PersonControllerImpl implements PersonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonControllerImpl.class);
    private final PersonService personService;
    private final PersonAssembler personAssembler;
    private final ValidatorUtil validatorUtil;

    @Autowired
    public PersonControllerImpl(final PersonService personService,
                                final PersonAssembler personAssembler,
                                final ValidatorUtil validatorUtil) {
        Objects.requireNonNull(personService);
        Objects.requireNonNull(personAssembler);
        this.personAssembler = personAssembler;
        this.personService = personService;
        this.validatorUtil = validatorUtil;
    }

    @Override
    @RequestMapping(value = "/person",
            produces = Constants.CONTENT_TYPE,
            method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<PersonEO> personEOList = personService.findAll();
        List<PersonVO> list = new ArrayList<>();
        list.addAll(Lists.transform(personEOList, c -> personAssembler.toResource(c)));
        Resources<PersonVO> resources = new Resources<>(list);
        resources.add(linkTo(methodOn(PersonControllerImpl.class).findAll()).withRel("self"));
        return ResponseEntity.ok(resources);
    }

    @Override
    @RequestMapping(value = "/person/{id}",
            produces = Constants.CONTENT_TYPE,
            method = RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        ResponseEntity<?> responseEntity;
        Optional<PersonEO> personEOOptional = personService.findById(id);
        if (personEOOptional.isPresent()) {
            responseEntity = ResponseEntity.ok(personAssembler.toResource(personEOOptional.get()));
        } else {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }

    @Override
    @RequestMapping(value = "/person",
            consumes = Constants.CONTENT_TYPE,
            produces = Constants.CONTENT_TYPE,
            method = RequestMethod.POST)
    public ResponseEntity<?> createPersonVO(@RequestBody PersonVO personVO) {
        ResponseEntity<?> responseEntity;
        Optional<PersonEO> optional = personService.create(personAssembler.toPersonEO(personVO));
        if (optional.isPresent()) {
            responseEntity = new ResponseEntity<>(personAssembler.toResource(optional.get()), HttpStatus.CREATED);
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @Override
    @RequestMapping(value = "/person/{id}",
            consumes = Constants.CONTENT_TYPE,
            produces = Constants.CONTENT_TYPE,
            method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonVO(@PathVariable("id") final String id,
                                            @RequestBody final PersonVO personVO) {
        ResponseEntity<?> responseEntity;
        PersonEO personEO = personAssembler.toPersonEO(personVO);
        personEO.setId(id);
        Optional<PersonEO> personEOOptional = personService.update(personEO);
        if (personEOOptional.isPresent()) {
            responseEntity = new ResponseEntity<>(personAssembler.toResource(personEOOptional.get()), HttpStatus.ACCEPTED);
        } else {
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @Override
    @RequestMapping(value = "/person/{id}",
            produces = Constants.CONTENT_TYPE,
            method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePerson(@PathVariable("id") final String id) {
        personService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
