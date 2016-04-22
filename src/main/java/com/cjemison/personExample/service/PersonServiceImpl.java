package com.cjemison.personExample.service;

import com.cjemison.personExample.persistence.domain.Status;
import com.cjemison.personExample.persistence.model.PersonEO;
import com.cjemison.personExample.persistence.repository.PersonRepository;
import com.cjemison.personExample.service.domain.PersonNotFoundException;
import com.cjemison.personExample.util.DateTimeUtil;
import com.cjemison.personExample.util.ValidatorUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by cjemison on 4/8/16.
 *
 * @todo Add unit tests.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    private final PersonRepository repository;
    private final ValidatorUtil validatorUtil;
    private final DateTimeUtil dateTimeUtil;

    @Autowired
    public PersonServiceImpl(final PersonRepository repository,
                             final ValidatorUtil validatorUtil,
                             final DateTimeUtil dateTimeUtil) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(validatorUtil);
        Objects.requireNonNull(dateTimeUtil);

        this.repository = repository;
        this.validatorUtil = validatorUtil;
        this.dateTimeUtil = dateTimeUtil;
    }

    @Override
    public List<PersonEO> findAll() {
        Iterable<PersonEO> iterable = repository.findAll();
        List<PersonEO> personEOList = iterable != null ? Lists.newArrayList(iterable) : Collections.EMPTY_LIST;
        LOGGER.debug("List<PersonEO>: {}", personEOList);
        return personEOList;
    }

    @Override
    public Optional<PersonEO> findById(final String id) {
        Optional<PersonEO> optional = Optional.empty();
        if (StringUtils.isNoneBlank(id)) {
            LOGGER.debug("Id: {}", id);
            PersonEO person = repository.findOne(id);
            if (person == null) {
                throw new PersonNotFoundException(String.format("Person: %s not found.", id));
            }
            LOGGER.debug("Person: {}", person);
            optional = Optional.of(person);
        }
        return optional;
    }

    @Override
    public Optional<PersonEO> create(final PersonEO personEO) {
        validatePerson(personEO);
        personEO.setCreatedDate(dateTimeUtil.getCurrentUTCTime().toDate());
        personEO.setStatus(Status.ACTIVE);
        repository.save(personEO);
        return Optional.of(personEO);
    }

    private void validatePerson(final PersonEO personVO) {
        validatorUtil.validateObject(personVO, "object is null.");
        LOGGER.debug("PersonVO: {}", personVO);
        validatorUtil.validateString(personVO.getFirstName(), "firstName is a required field");
        validatorUtil.validateString(personVO.getLastName(), "lastName is a required field");
    }

    @Override
    public Optional<PersonEO> update(final PersonEO personEO) {
        Objects.requireNonNull(personEO);
        LOGGER.debug("PersonVO: {}", personEO);
        PersonEO existing = repository.findOne(personEO.getId());
        if (existing == null) {
            throw new PersonNotFoundException(String.format("Person: %s not found", personEO.getId()));
        }
        setDeltas(existing, personEO);
        existing.setUpdatedDate(dateTimeUtil.getCurrentUTCTime().toDate());
        repository.save(existing);
        LOGGER.debug("PersonEO: {}", existing);
        return Optional.of(existing);
    }

    private void setDeltas(PersonEO existing, PersonEO personEO) {
        if (StringUtils.isNotBlank(personEO.getFirstName())
                && !personEO.getFirstName().equals(existing.getFirstName())) {
            existing.setFirstName(personEO.getFirstName());
        }

        if (StringUtils.isNotBlank(personEO.getMiddleName())
                && !personEO.getMiddleName().equals(existing.getMiddleName())) {
            existing.setMiddleName(personEO.getMiddleName());
        }

        if (StringUtils.isNotBlank(personEO.getLastName())
                && !personEO.getLastName().equals(existing.getLastName())) {
            existing.setLastName(personEO.getLastName());
        }

        if (StringUtils.isNotBlank(personEO.getEmailAddress())
                && !personEO.getEmailAddress().equals(existing.getEmailAddress())) {
            existing.setEmailAddress(personEO.getEmailAddress());
        }
    }

    @Override
    public boolean delete(final String id) {
        boolean deleteFlag = false;
        if (StringUtils.isNoneBlank(id)) {
            LOGGER.debug("Id: {}", id);
            PersonEO person = repository.findOne(id);
            if (person == null) {
                throw new PersonNotFoundException(String.format("Person: %s not found.", id));
            }
            LOGGER.debug("Person: {}", person);
            repository.delete(person);
            deleteFlag = true;
        }
        return deleteFlag;
    }
}
