package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;


public class US_06_AddNewBookStepDefs {

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.isDisplayed();
        bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String string) {

        bookPage.bookNameInputBox.click();
        bookPage.bookNameInputBox.sendKeys(string);

    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String string) {

         bookPage.isbn.click();
         bookPage.isbn.sendKeys(string);

    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String string) {

        bookPage.year.click();
        bookPage.year.sendKeys(string);

    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String string) {
        bookPage.author.click();
        bookPage.author.sendKeys(string);


    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
            bookPage.categoryDropdown.click();
            BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown,category);




    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        System.out.println("SaveChanges.isDisplayed() = " + bookPage.saveChanges.isDisplayed());
        bookPage.saveChanges.click();

    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expected_message) {

        System.out.println("ToastContainer.isDisplayed() = " + bookPage.toastContainer.isDisplayed());
        String actualMessage = bookPage.toastContainer.getText();

        System.out.println("ToastContainer.getText() = " + bookPage.toastContainer.getText());
        Assert.assertEquals(expected_message,actualMessage);

    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

        String query ="select name from books\n" +
                "where name = '"+expectedBookName+"'\n" +
                "order by id desc";


        DB_Util.runQuery(query);
        Map<String,String> rowMap = DB_Util.getRowMap(1);
        String actualBookName = rowMap.get("name");
        Assert.assertEquals(expectedBookName, actualBookName);



    }

}
