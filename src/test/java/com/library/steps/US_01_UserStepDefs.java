package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US_01_UserStepDefs {

    String actualUserCount;
    List<String> actualColumnsNames;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {

        //make conn with library    in Hooks @Before or DB_Util.createConnection();
       // DB_Util.createConnection();
        System.out.println("-----------------------------------------------------");
        System.out.println("-----CONNECTION WILL BE DONE WITH @BEFORE HOOKS CLASS----------");
        System.out.println("-----------------------------------------------------");

    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query="select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "SELECT count(distinct ID) FROM users"; //distinct mens unique
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUserCount);


        //close connection
       // DB_Util.destroy();

        System.out.println("-----------------------------------------------------");
        System.out.println("-----CONNECTION WILL BE DONE WITH @AFTER HOOKS CLASS----------");
        System.out.println("-----------------------------------------------------");


    }

    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() {

       // DB_Util.createConnection();

        String query = "select * from users";
        DB_Util.runQuery(query);

        actualColumnsNames = DB_Util.getAllColumnNamesAsList();
        System.out.println(actualColumnsNames);

    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedColumnsNames) {
        System.out.println(expectedColumnsNames);

        Assert.assertEquals(expectedColumnsNames,actualColumnsNames);

        //close conn  in Hooks @After
       // DB_Util.destroy(); - we dont need it because we add @db in Scenario: verify users table columns
                             // and this tag also added to the HOOKS CLASS in @Before("@db") and @After("@db")
                             // and it will create and close connection

    }
}
