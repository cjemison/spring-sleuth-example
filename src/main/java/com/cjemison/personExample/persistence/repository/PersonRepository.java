package com.cjemison.personExample.persistence.repository;

import com.cjemison.personExample.persistence.model.PersonEO;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by cjemison on 4/8/16.
 */
public interface PersonRepository extends PagingAndSortingRepository<PersonEO, String> {
}
