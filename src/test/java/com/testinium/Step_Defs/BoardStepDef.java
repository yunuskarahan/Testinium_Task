package com.testinium.Step_Defs;

import com.testinium.Utilities.ConfigurationReader;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class BoardStepDef {

    String boardId, labelId, cardId;


    @Before
    public static void before() {
        baseURI = ConfigurationReader.getProperty("baseURI");
        basePath = ConfigurationReader.getProperty("basePath");
    }

    @Given("Create board")
    public void create_board() {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("name", "BoardTest").when().post("/boards");

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        boardId = jsonPath.getString("id");
        System.out.println("BoardId: " + boardId);

    }

    @When("Create a list on board.")
    public void createAListOnBoard() {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id", boardId)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("name", "BoardLabel").when().post("/boards/{id}/lists");

        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        labelId = jsonPath.getString("id");
        System.out.println("LabelId: " + labelId);

    }

    @And("Create a new card.")
    public void createANewCard() {

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("idList", labelId).when().post("/cards");
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        cardId = jsonPath.getString("id");
        System.out.println("cardId = " + cardId);


    }


    @And("Update card.")
    public void updateCard() {
        String expectedNamed = "updatedName";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id", cardId)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .queryParam("name", expectedNamed).when().put("/cards/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String actualName = jsonPath.getString("name");
        System.out.println("Actual name: " + actualName);
        assertEquals(expectedNamed, actualName);


    }

    @And("Delete card.")
    public void deleteCard() {

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id", cardId)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .when().delete("/cards/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String result = jsonPath.getString("limit");
        System.out.println("Card delete result: " + result);
        assertNull(result);

    }

    @And("Delete board.")
    public void deleteBoard() {
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .pathParam("id", boardId)
                .queryParam("key", ConfigurationReader.getProperty("key"))
                .queryParam("token", ConfigurationReader.getProperty("token"))
                .when().delete("/boards/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json; charset=utf-8", response.contentType());
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        String result = jsonPath.getString("_value");
        System.out.println("Board delete result: " + result);
        assertNull(result);


    }
}
