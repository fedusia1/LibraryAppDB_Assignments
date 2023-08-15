package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US_02_LibrarianBorrowNumberStepDefs {

  LoginPage loginPage = new LoginPage();
  DashBoardPage dashBoardPage = new DashBoardPage();

  String expectedBorrowBooksCount;
  String actualBorrowBooksNumValue;


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String string) {
        //  DB_Util.createConnection(); we don't need this feature because we add @db tag
                                       //  in HOOKS class and in Feature class:  @Before("@db")   @After("@db")
                                        // and @db tags runs create connection with DB and close connection with DB

        loginPage.login(string);



    }
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {


      System.out.println("dashBoardPage.borrowedBooksNumber.isDisplayed() = " + dashBoardPage.borrowedBooksNumber.isDisplayed());

      actualBorrowBooksNumValue = dashBoardPage.borrowedBooksNumber.getText();

      System.out.println(actualBorrowBooksNumValue);

      //or
     // System.out.println("dashBoardPage.getModuleCount("Borrowed Books") = " + dashBoardPage.getModuleCount("Borrowed Books"));

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

      String query="select count(*) from book_borrow where is_returned=0";
      DB_Util.runQuery(query);

      expectedBorrowBooksCount = DB_Util.getFirstRowFirstColumn();
      System.out.println(expectedBorrowBooksCount );

      Assert.assertEquals(expectedBorrowBooksCount,actualBorrowBooksNumValue);

    }

}
