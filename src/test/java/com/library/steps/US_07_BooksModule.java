package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.pages.DashBoardPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;



public class US_07_BooksModule {
// for this US works I need to create new book by myself: go to library as a librarian  ->  addBook -> create new book
    // ex: "Martha" or "LiudasNewBook" or "Treasure" new name for new book. Then insert this new book name into config.properties(book_name)
    // insert new book name into library.sql US07, insert new book name into us07.feature file. Then start test.

    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    BookPage bookPage  = new BookPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String globalBookName;
    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book()  {

        globalBookName = ConfigurationReader.getProperty("book_name") ;

        // switch to table

        borrowedBooksPage.borrowBookButton(globalBookName).isDisplayed();

        System.out.println("borrowedBooksPage.borrowBookButton.isDisplayed() = " + borrowedBooksPage.borrowBookButton(globalBookName).isDisplayed());

        bookPage.borrowBook(globalBookName).click();

        BrowserUtil.waitFor(2);



        // borrowedBooksPage.borrowBookButton.click();

        BrowserUtil.verifyElementDisplayed(borrowedBooksPage.borrowMessage);

        System.out.println("borrowedBooksPage.borrowMessage.getText() = " + borrowedBooksPage.borrowMessage.getText());

    }
    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String module) {

        System.out.println("borrowedBooksPage.borrowingBooksLink.isDisplayed() = " + borrowedBooksPage.borrowingBooksLink.isDisplayed());

        dashBoardPage.navigateModule(module);

       // borrowedBooksPage.borrowingBooksLink.click();


        System.out.println("borrowedBooksPage.getBorrowBooksTitle.isDisplayed() = " + borrowedBooksPage.getBorrowBooksTitle.isDisplayed());
        String actualtitleBorrowingBook = borrowedBooksPage.getBorrowBooksTitle.getText();
        System.out.println("titleBorrowingBook = " + actualtitleBorrowingBook);

        Assert.assertEquals(module,actualtitleBorrowingBook);

        BrowserUtil.waitFor(3);

        Assert.assertTrue(BrowserUtil.getElementsText(borrowedBooksPage.allBorrowedBooksName).contains(globalBookName));

       String actualStatus =  borrowedBooksPage.returnedNotReturnedText(globalBookName).getText();
       String expectedStatus = "NOT RETURNED";

       Assert.assertEquals(expectedStatus,actualStatus);


    }
    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {



        String query="select full_name,b.name,bb.borrowed_date from users u " +
                "inner join book_borrow bb on u.id = bb.user_id " +
                "       inner join books b on bb.book_id = b.id " +
                "        where full_name='Test Student 5' and name='"+globalBookName+"'" +
                "        order by 3 desc";
        DB_Util.runQuery(query);

       /*List<String> actualList = DB_Util.getColumnDataAsList("name");
        Assert.assertTrue(actualList.contains(globalBookName));*/


        String actualBorrowBooks = DB_Util.getCellValue(1,2);
        System.out.println("actualBorrowBooks = " + actualBorrowBooks);
        Assert.assertEquals(globalBookName,actualBorrowBooks);


        BrowserUtil.waitFor(3);

        String actualStatus = "NOT RETURNED";
        System.out.println("actualStatus = " + actualStatus);
        String expectedBorrowBooksCount = borrowedBooksPage.tableLiudasBook(globalBookName).getText();
        System.out.println("expected status = "+expectedBorrowBooksCount );



        Assert.assertEquals(expectedBorrowBooksCount,actualStatus);


    }



}
