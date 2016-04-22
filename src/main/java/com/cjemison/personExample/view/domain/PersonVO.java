package com.cjemison.personExample.view.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by cjemison on 4/8/16.
 *
 * @todo Add unit tests for builder
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonVO extends ResourceSupport {
    private String personId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;

    public PersonVO() {
    }

    private PersonVO(String personId,
                     String firstName,
                     String middleName,
                     String lastName,
                     String emailAddress) {
        this.personId = personId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        if (!(o instanceof PersonVO)) return false;
        if (!super.equals(o)) return false;

        PersonVO personVO = (PersonVO) o;

        if (firstName != null ? !firstName.equals(personVO.firstName) : personVO.firstName != null) return false;
        if (middleName != null ? !middleName.equals(personVO.middleName) : personVO.middleName != null) return false;
        if (lastName != null ? !lastName.equals(personVO.lastName) : personVO.lastName != null) return false;
        return emailAddress != null ? emailAddress.equals(personVO.emailAddress) : personVO.emailAddress == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PersonVO{" +
                "personId='" + personId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }

    public static class Builder {
        private String personId;
        private String firstName;
        private String middleName;
        private String lastName;
        private String emailAddress;

        public Builder personId(final String id) {
            this.personId = id;
            return this;
        }

        public Builder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder middleName(final String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder emailAddress(final String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public PersonVO build() {
            return new PersonVO(personId,
                    firstName, middleName, lastName, emailAddress);
        }
    }
}
