package com.github.vikaclass.tryra;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestEndpoints {
    Faker faker;
    User userPayload;

    @BeforeTest
    public void beforeTest(){
        System.out.println("--------------USERNAME UNDER TEST--------------");
        faker = new Faker();
        userPayload = new User();
        userPayload.setUserID(faker.idNumber().hashCode());
        userPayload.setUserName(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());
        System.out.println("Some random user with username " + userPayload.getUserName());
        System.out.println(userPayload);
        System.out.println("-----------------------------------------------");
    }
    @Test(priority = 1)
    public void testPostUser(){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("id",userPayload.getUserID());
        bodyMap.put("username",userPayload.getUserName());
        bodyMap.put("firstName",userPayload.getFirstName());
        bodyMap.put("lastName",userPayload.getLastName());
        bodyMap.put("email",userPayload.getEmail());
        bodyMap.put("password",userPayload.getPassword());
        bodyMap.put("phone",userPayload.getPhone());

        String payload = new Gson().toJson(bodyMap);
        Response response = UserEndpoints.createUser(payload);
        response.then().log().all();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertTrue(response.getStatusLine().contains("OK"));

    }
    @Test(priority = 2)
    public void testGetUser(){
        Response response = UserEndpoints.readUser(userPayload.getUserName());
        response.then().log().body().statusCode(200);

    }
    @Test(priority = 3)
    public void testUpdateUser(){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("id",userPayload.getUserID());
        bodyMap.put("username",userPayload.getUserName());


        bodyMap.put("firstName",userPayload.getFirstName() + " is my firstname");
        bodyMap.put("lastName",userPayload.getLastName() + " is my lastName");
        bodyMap.put("email",userPayload.getEmail() + " is my email");
        bodyMap.put("password",userPayload.getPassword() + " is my password");
        bodyMap.put("phone",userPayload.getPhone() + " is my phone");
        bodyMap.put("userStatus", 1);

        String payload = new Gson().toJson(bodyMap);

        Response response = UserEndpoints.updateUser(userPayload.getUserName(), payload);
        response.then().log().body().statusCode(200);

        Response afterUpdateResponse = UserEndpoints.readUser(userPayload.getUserName());
        afterUpdateResponse.then().log().body().statusCode(200);



    }
    @Test(priority = 4)
    public void testDeleteUser(){
        Response response = UserEndpoints.deleteUser(userPayload.getUserName());
        response.then().log().body().statusCode(200);

    }
}
