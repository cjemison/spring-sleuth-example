package com.cjemison.personExample.persistence.domain;

import java.util.Date;

/**
 * Created by cjemison on 4/8/16.
 */
public interface BaseEntity {

    Date getCreatedDate();

    void setCreatedDate(Date createdDate);

    Date getUpdatedDate();

    void setUpdatedDate(Date updatedDate);

    Status getStatus();

    void setStatus(Status status);
}
