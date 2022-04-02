package com.icai.practicas.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DNITest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_DNI_using_right_then_ok(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        DNI dni = new DNI("12345678Z");
        Set<ConstraintViolation<DNI>> violations = validator.validate(dni);

        //Then
        then(violations.size()).isEqualTo(0);

    }

    @Test
    public void given_app_when_DNI_using_vacio(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        DNI dni = new DNI(" ");
        Set<ConstraintViolation<DNI>> violations = validator.validate(dni);

        //Then
        then(violations.size()).isGreaterThan(0);
    }

    @Test
    public void given_app_when_DNI_using_null(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        DNI dni = new DNI(null);
        Set<ConstraintViolation<DNI>> violations = validator.validate(dni);

        //Then
        then(violations.size()).isGreaterThan(0);
    }


}
