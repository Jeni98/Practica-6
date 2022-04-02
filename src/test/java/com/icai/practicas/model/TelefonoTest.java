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
public class TelefonoTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void given_app_when_Telefono_using_right_then_ok(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        Telefono telefono = new Telefono("2B34%20600903434");
        Set<ConstraintViolation<Telefono>>violations = validator.validate(telefono);

        //Then
        then(violations.size()).isEqualTo(0);

    }

    @Test
    public void given_app_when_Telefono_using_vacio(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        Telefono telefono = new Telefono(" ");
        Set<ConstraintViolation<Telefono>> violations = validator.validate(telefono);

        //Then
        then(violations.size()).isGreaterThan(0);
    }

    @Test
    public void given_app_when_Telefono_using_null(){
        //Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        //When
        Telefono telefono = new Telefono(null);
        Set<ConstraintViolation<Telefono>> violations = validator.validate(telefono);

        //Then
        then(violations.size()).isGreaterThan(0);
    }


}
