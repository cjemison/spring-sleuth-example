package com.cjemison.personExample.persistence.model;

import com.cjemison.personExample.persistence.domain.AbstractBuilder;
import com.cjemison.personExample.persistence.domain.BaseEntity;
import com.cjemison.personExample.persistence.domain.BaseEntityImpl;
import com.cjemison.personExample.util.ValidatorUtil;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by cjemison on 4/8/16.
 */
@Entity
@Table(name = "person_tbl")
public class PersonEO extends BaseEntityImpl implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "person_id")
    private String id;

    @Column(name = "person_first_name")
    private String firstName;

    @Column(name = "person_middle_name")
    private String middleName;

    @Column(name = "person_last_name")
    private String lastName;

    @Column(name = "person_email_address")
    private String emailAddress;

    public PersonEO() {
    }

    protected PersonEO(final String firstName,
                       final String middleName,
                       final String lastName,
                       final String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName == null ? "" : middleName;
        this.emailAddress = emailAddress == null ? "" : emailAddress;
    }

    public static PersonEO.Builder builder() {
        return builder(ValidatorUtil.DEFAULT);
    }

    public static PersonEO.Builder builder(final ValidatorUtil validatorUtil) {
        return new PersonEO.Builder(validatorUtil);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonEO)) return false;

        PersonEO personEO = (PersonEO) o;

        if (firstName != null ? !firstName.equals(personEO.firstName) : personEO.firstName != null) return false;
        if (middleName != null ? !middleName.equals(personEO.middleName) : personEO.middleName != null) return false;
        if (lastName != null ? !lastName.equals(personEO.lastName) : personEO.lastName != null) return false;
        return emailAddress != null ? emailAddress.equals(personEO.emailAddress) : personEO.emailAddress == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonEO{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static class Builder extends AbstractBuilder {
        private String firstName;
        private String middleName = "";
        private String lastName;
        private String emailAddress;

        public Builder(final ValidatorUtil validatorUtil) {
            super(validatorUtil);
        }

        public Builder firstName(final String firstName) {
            validateString(firstName, "firstName");
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(final String middleName) {
            validateString(middleName, "middleName");
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(final String lastName) {
            validateString(lastName, "lastName");
            this.lastName = lastName;
            return this;
        }

        public Builder emailAddress(final String emailAddress) {
            validateString(emailAddress, "emailAddress");
            this.emailAddress = emailAddress;
            return this;
        }

        public PersonEO build() {
            return new PersonEO(firstName, middleName, lastName, emailAddress);
        }
    }
}
