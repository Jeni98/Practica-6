package com.icai.practicas.controller;

import com.icai.practicas.service.ProcessService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerE2ETest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ProcessService processService;
    @Autowired
    private ProcessController processcontroller;


    @Test
    public void controller_when_legacy_using_righ (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1-legacy";

        ProcessController.DataRequest data = new ProcessController.DataRequest("Juan Antonio Brena Moral","12345678Z","+34 600903434");
        //Validar request
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);
        Set<ConstraintViolation<ProcessController.DataRequest>> violations = validator.validate(data);
        //System.out.println(result);


        //Then
        ProcessController.DataResponse expectedResponse = new ProcessController.DataResponse("ok");

        then(violations.size()).isEqualTo(0);
        then(result.getBody());
        //then(result.getBody()).isEqualTo(expectedResponse);
    }

    //testing de legacy para valores errados
    @Test
    public void controller_when_legacy_using_bad (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1-legacy";

        ProcessController.DataRequest data = new ProcessController.DataRequest("Juan Antonio","12345678Z","2B34%20600903434");

        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);

        //System.out.println(result);


        //Then
        ProcessController.DataResponse expectedResponse = new ProcessController.DataResponse("ok");

        then(result.getBody());
        //then(result.getBody()).isEqualTo(expectedResponse);
    }

    //testing de legacy para valores nulos
    @Test
    public void controller_when_legacy_using_null (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1-legacy";

        ProcessController.DataRequest data = new ProcessController.DataRequest(null,null,null);

        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);
        //System.out.println(result);


        //Then
        ProcessController.DataResponse expectedResponse = new ProcessController.DataResponse("ok");

        then(result.getBody());
        //then(result.getBody()).isEqualTo(expectedResponse);
    }


    //testing para step1 (segundo endpoint)
    @Test
    public void controller_when_step1_using_righ (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1";

        ProcessController.DataRequest data = new ProcessController.DataRequest("Juan Antonio Brena Moral","12345678Z","+34 600903434");
        //Validar request
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);
        Set<ConstraintViolation<ProcessController.DataRequest>> violations = validator.validate(data);
        //System.out.println(result);


        //Then
        then(violations.size()).isEqualTo(0);
        then(result.getBody());
    }


    //testing de step1 para valores errados (segundo endpoint)
    @Test
    public void controller_when_step1_using_bad (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1-legacy";

        ProcessController.DataRequest data = new ProcessController.DataRequest("Juan Antonio","12345678Z","2B34%20600903434");

        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);
        //System.out.println(result);


        //Then
        then(result.getBody());

    }

    //testing de legacy para valores nulos (segundo endpoint)
    @Test
    public void controller_when_step1_using_null (){
        //Given
        String address = "http://localhost:"+port+"/api/v1/process-step1-legacy";

        ProcessController.DataRequest data = new ProcessController.DataRequest(null,null,null);

        //MultivalueMap
        MultiValueMap<String,String> data1= new LinkedMultiValueMap<>();
        data1.add("fullName",data.fullName());
        data1.add("dni",data.dni());
        data1.add("telefono",data.telefono());
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        //entityRequest
        HttpEntity<MultiValueMap> request = new HttpEntity<>(data1,headers);


        //When

        ResponseEntity<String> result = this.restTemplate.postForEntity(address,request, String.class);
        //System.out.println(result);


        //Then
        then(result.getBody());

    }


}
