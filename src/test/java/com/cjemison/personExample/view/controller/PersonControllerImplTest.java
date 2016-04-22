package com.cjemison.personExample.view.controller;

import com.cjemison.personExample.Application;
import com.cjemison.personExample.persistence.model.PersonEO;
import com.cjemison.personExample.persistence.repository.PersonRepository;
import com.cjemison.personExample.util.Constants;
import com.cjemison.personExample.util.ValidatorUtil;
import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by cjemison on 4/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest({"server.port=9090", "management.port=0"})
public class PersonControllerImplTest {
    private final int port = 9090;
    private final String host = "http://localhost:" + port;

    @Autowired
    private PersonRepository repository;

    @Test
    public void findAll() throws Exception {
        PersonEO personVO = new PersonEO.Builder(ValidatorUtil.DEFAULT)
                .firstName("Cornelius")
                .lastName("Jemison")
                .emailAddress("cjemison@ymail.com").build();
        repository.save(personVO);

        Response response = given().accept(Constants.CONTENT_TYPE)
                .get(host + "/v1/person");

        assertThat(response, is(notNullValue()));
        String json = response.asString();
        assertThat(StringUtils.isNoneBlank(json), is(equalTo(true)));

        String firstName = JsonPath.read(json, "$.content[0].firstName");
        assertThat(StringUtils.isNoneBlank(firstName), is(equalTo(true)));

        String lastName = JsonPath.read(json, "$.content[0].lastName");
        assertThat(StringUtils.isNoneBlank(lastName), is(equalTo(true)));

        String emailAddress = JsonPath.read(json, "$.content[0].emailAddress");
        assertThat(StringUtils.isNoneBlank(emailAddress), is(equalTo(true)));
    }

    @Test
    public void findById() throws Exception {
        PersonEO personVO = new PersonEO.Builder(ValidatorUtil.DEFAULT)
                .firstName("Cornelius")
                .lastName("Jemison")
                .emailAddress("cjemison@ymail.com").build();
        repository.save(personVO);

        Response response = given().accept(Constants.CONTENT_TYPE)
                .get(host + "/v1/person/" + personVO.getId());

        assertThat(response, is(notNullValue()));

        String json = response.asString();
        assertThat(StringUtils.isNoneBlank(json), is(equalTo(true)));

        String firstName = JsonPath.read(json, "$.firstName");
        assertThat(StringUtils.isNoneBlank(firstName), is(equalTo(true)));

        String lastName = JsonPath.read(json, "$.lastName");
        assertThat(StringUtils.isNoneBlank(lastName), is(equalTo(true)));

        String emailAddress = JsonPath.read(json, "$.emailAddress");
        assertThat(StringUtils.isNoneBlank(emailAddress), is(equalTo(true)));

    }

    @Test
    public void createPersonVO() throws Exception {
        String json = "{\"firstName\":\"Cornelius\",\"lastName\":\"Jemison\",\"emailAddress\":\"cornelius.jemison@ymail.com\",\"middleName\":\"Jackson\"}";
        Response response = given().accept(Constants.CONTENT_TYPE)
                .contentType(Constants.CONTENT_TYPE).body(json).post(host + "/v1/person");
        assertThat(response, is(notNullValue()));

        String responseJson = response.asString();
        assertThat(StringUtils.isNoneBlank(responseJson), is(equalTo(true)));

        String firstName = JsonPath.read(responseJson, "$.firstName");
        assertThat(StringUtils.isNoneBlank(firstName), is(equalTo(true)));

        String lastName = JsonPath.read(responseJson, "$.lastName");
        assertThat(StringUtils.isNoneBlank(lastName), is(equalTo(true)));

        String emailAddress = JsonPath.read(responseJson, "$.emailAddress");
        assertThat(StringUtils.isNoneBlank(emailAddress), is(equalTo(true)));

    }

    @Test
    public void updatePersonVO() throws Exception {
        String json = "{\"firstName\":\"Cornelius\",\"lastName\":\"Jemison\",\"emailAddress\":\"cornelius.jemison@ymail.com\",\"middleName\":\"Jackson\"}";
        Response response = given().accept(Constants.CONTENT_TYPE)
                .contentType(Constants.CONTENT_TYPE).body(json).post(host + "/v1/person");
        assertThat(response, is(notNullValue()));

        String responseJson = response.asString();
        assertThat(StringUtils.isNoneBlank(responseJson), is(equalTo(true)));

        String firstName = JsonPath.read(responseJson, "$.firstName");
        assertThat(StringUtils.isNoneBlank(firstName), is(equalTo(true)));

        String lastName = JsonPath.read(responseJson, "$.lastName");
        assertThat(StringUtils.isNoneBlank(lastName), is(equalTo(true)));

        String emailAddress = JsonPath.read(responseJson, "$.emailAddress");
        assertThat(StringUtils.isNoneBlank(emailAddress), is(equalTo(true)));

        String personId = JsonPath.read(responseJson, "$.personId");
        assertThat(StringUtils.isNoneBlank(personId), is(equalTo(true)));

        json = "{\"firstName\":\"Cornelius\",\"lastName\":\"Jemison\",\"emailAddress\":\"cornelius.jemison@gmail.com\",\"middleName\":\"Jackson\"}";
        response = given().accept(Constants.CONTENT_TYPE)
                .contentType(Constants.CONTENT_TYPE).body(json).put(host + "/v1/person/" + personId);
        assertThat(response, is(notNullValue()));

        responseJson = response.asString();
        assertThat(StringUtils.isNoneBlank(responseJson), is(equalTo(true)));

        firstName = JsonPath.read(responseJson, "$.firstName");
        assertThat(StringUtils.isNoneBlank(firstName), is(equalTo(true)));

        lastName = JsonPath.read(responseJson, "$.lastName");
        assertThat(StringUtils.isNoneBlank(lastName), is(equalTo(true)));

        emailAddress = JsonPath.read(responseJson, "$.emailAddress");
        assertThat(StringUtils.isNoneBlank(emailAddress), is(equalTo(true)));
        assertThat(emailAddress, is(equalTo("cornelius.jemison@gmail.com")));

    }

    @Test
    public void deletePerson() throws Exception {
        String json = "{\"firstName\":\"Cornelius\",\"lastName\":\"Jemison\",\"emailAddress\":\"cornelius.jemison@ymail.com\",\"middleName\":\"Jackson\"}";
        Response response = given().accept(Constants.CONTENT_TYPE)
                .contentType(Constants.CONTENT_TYPE).body(json).post(host + "/v1/person");
        assertThat(response, is(notNullValue()));

        String responseJson = response.asString();
        assertThat(StringUtils.isNoneBlank(responseJson), is(equalTo(true)));

        String personId = JsonPath.read(responseJson, "$.personId");
        assertThat(StringUtils.isNoneBlank(personId), is(equalTo(true)));

        response = given().accept(Constants.CONTENT_TYPE).delete(host + "/v1/person/" + personId);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStatusCode(), is(equalTo(200)));
    }

}