package controller;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.MyApplication;
import za.ac.cput.domain.Driver;
import za.ac.cput.factory.DriverFactory;
import za.ac.cput.service.impl.DriverService;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;



@TestMethodOrder(MethodOrderer.MethodName.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(classes = MyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DriverControllerTest {

    private static Driver driver = DriverFactory.createDriver("Rick",
            "Ferdinand","11111","Benz",
            "0747823872","ri@gmail.com");

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DriverService driverService;

    private final String baseURL = "http://localhost:8080/driver";


    @Test
    void a_create() {
        String url = baseURL + "/create";
            System.out.println("URL: " +url);
//            System.out.println("Post driver: " + driver);
        ResponseEntity<Driver> postResponse = restTemplate.postForEntity(url, driver, Driver.class);
        assertNotNull(postResponse);
       // assertNotNull(postResponse.getBody().getDriver_ID());
       // driver = postResponse;
        System.out.println("Saved data: " + driver);
        //assertEquals(driver.getDriver_ID(), postResponse.getBody().getDriver_ID());

    }

    @Test
    void b_read() {
        String url = baseURL + "/read/" + driver.getDriver_ID();
        System.out.println("URL: " + url);
        ResponseEntity <Driver> response = restTemplate.getForEntity(url, Driver.class);
        //assertEquals(driver.getDriver_ID(), response.getBody());
        //System.out.println(response.getBody());
    }

    @Test
    void c_update() {
        Driver updated = new Driver.Builder().copy(driver).setFirst_Name("Antonio").build();
        String url = baseURL + "/update";
        System.out.println ("URL: " + url);
        System.out.println ("Post data: " + updated);
        ResponseEntity<Driver> response = restTemplate.postForEntity(url, updated, Driver.class);
       // assertNotNull(response.getBody());
    }

    @Test
    @Disabled
    void e_delete() {
        String url = baseURL + "/delete/" + driver.getDriver_ID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
    }

    @Test
    void d_getall() {
        String url = baseURL + "/alldrivers";
        List<Driver> drivers =  driverService.getAll();

        assertNotNull(drivers);
        System.out.println("Show ALL" +
                "/n " + drivers);

    }
//    @Test
//    void d_getall() {
//        String url = baseURL + "/getall";
//        HttpHeaders headers = new HttpHeaders();
//        HttpEntity<String> entity = new HttpEntity<>(null, headers);
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println("Show ALL");
//        System.out.println(response);
//        System.out.println(response.getBody());
//    }
//
}