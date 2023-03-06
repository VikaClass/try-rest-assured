package com.github.vikaclass.tryra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
    public static Response createUser(String payload){
        RestAssured.baseURI = Routes.BASE_URI;
        return RestAssured
                .given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)
                .when().post(Routes.POST_URI);

    }
    public static Response readUser(String userName){
        RestAssured.baseURI = Routes.BASE_URI;
        return RestAssured
                .given().pathParam("username", userName)
                .when().get(Routes.GET_PUT_DELETE_URI);
    }
    public  static Response updateUser(String userName, String payload){
        RestAssured.baseURI = Routes.BASE_URI;
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.GET_PUT_DELETE_URI);
    }
    public static Response deleteUser(String userName){
        RestAssured.baseURI = Routes.BASE_URI;
        return RestAssured
                .given().pathParam("username", userName)
                .when().delete(Routes.GET_PUT_DELETE_URI);

    }

}
