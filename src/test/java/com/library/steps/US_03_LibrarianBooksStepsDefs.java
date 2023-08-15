package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;


public class US_03_LibrarianBooksStepsDefs {


    DashBoardPage dashBoardPage = new DashBoardPage();
    BookPage bookPage = new BookPage();


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String module) {



        System.out.println("dashBoardPage.BooksLink.isDisplayed() = " + dashBoardPage.BooksLink.isDisplayed());
        bookPage.navigateModule(module);

        BrowserUtil.waitFor(3);

        //click on Module Books
       // dashBoardPage.navigateModule(module); // method from abstract class BasePage. Use this because need it for my US02 and US07

        //or can use this also
        //dashBoardPage.BooksLink.click();

        System.out.println("bookPage.pageHeader.isDisplayed() = " + bookPage.pageHeader.isDisplayed());

    }
    List<String> actualModules;
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {

        actualModules = BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        System.out.println("All book category " +  actualModules);

        actualModules.remove(0);
        System.out.println("actualModules = " + actualModules);
    }


    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {


        String query="select name from book_categories";
        DB_Util.runQuery(query);

        List<String> expectedModules = DB_Util.getColumnDataAsList("name");
        System.out.println("expected category modules = " + expectedModules);


        Assert.assertEquals("Verify category list is matching",expectedModules, actualModules);



    }

}
