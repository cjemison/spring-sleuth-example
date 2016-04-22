package com.cjemison.personExample.view.assembler;

import com.cjemison.personExample.persistence.model.PersonEO;
import com.cjemison.personExample.util.ValidatorUtil;
import com.cjemison.personExample.view.controller.PersonControllerImpl;
import com.cjemison.personExample.view.domain.PersonVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by cjemison on 4/9/16.
 *
 * @todo Add unit tests.
 */
@Component
public class PersonAssembler extends ResourceAssemblerSupport<PersonEO, PersonVO> {
    private final Logger logger = LoggerFactory.getLogger(PersonAssembler.class);
    @Autowired
    private ValidatorUtil validatorUtil;

    public PersonAssembler() {
        super(PersonControllerImpl.class, PersonVO.class);
    }

    @Override
    public PersonVO toResource(PersonEO personEO) {
        logger.debug("PersonEO: {}", personEO);
        PersonVO personVO = new PersonVO.Builder()
                .personId(personEO.getId())
                .firstName(personEO.getFirstName())
                .middleName(personEO.getMiddleName())
                .emailAddress(personEO.getEmailAddress())
                .lastName(personEO.getLastName()).build();
        personVO.add(linkTo(methodOn(PersonControllerImpl.class).findById(personEO.getId())).withSelfRel());
        return personVO;
    }

    public PersonEO toPersonEO(PersonVO personVO) {
        logger.debug("PersonVO: {}", personVO);
        PersonEO.Builder builder = new PersonEO.Builder(validatorUtil)
                .firstName(personVO.getFirstName())
                .lastName(personVO.getLastName())
                .emailAddress(personVO.getEmailAddress());
        if (StringUtils.isNotBlank(personVO.getMiddleName())) {
            builder.middleName(personVO.getMiddleName());
        }
        PersonEO personEO = builder.build();
        personEO.setId(personVO.getPersonId());
        return builder.build();
    }
}
