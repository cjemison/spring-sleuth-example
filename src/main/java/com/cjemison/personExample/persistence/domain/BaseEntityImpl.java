package com.cjemison.personExample.persistence.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cjemison on 4/8/16.
 */
@MappedSuperclass
public abstract class BaseEntityImpl implements BaseEntity {
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status enumStatus) {
        this.status = enumStatus;
    }
}
