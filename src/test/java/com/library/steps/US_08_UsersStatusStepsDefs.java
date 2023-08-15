package com.library.steps;

import com.library.pages.UsersPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US_08_UsersStatusStepsDefs {


    UsersPage usersPage=new UsersPage();
    @When("the user selected status {string}")
    public void the_user_selected_status(String status) {
        BrowserUtil.selectOptionDropdown(usersPage.statusDropdown,status);
    }
    String actualCount;
    @When("the gets number of users")
    public void the_gets_number_of_users() {
        BrowserUtil.waitFor(3);
        actualCount = usersPage.getUserCount();
        System.out.println(usersPage.getUserCount());

    }

    @Then("verify {string} status users count matching with DB")
    public void verify_status_users_count_matching_with_db(String status) {
        String query="select count(*) from users where status='"+status+"' and user_group_id<>1 ";

        DB_Util.runQuery(query);

        String expectedCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedCount);

        Assert.assertEquals(expectedCount,actualCount);
    }



}
