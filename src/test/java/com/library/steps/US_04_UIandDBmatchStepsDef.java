package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class US_04_UIandDBmatchStepsDef {

    BookPage bookPage = new BookPage();
    String globalBookName;
    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {


        System.out.println("bookPage.search.isDisplayed() = " + bookPage.search.isDisplayed());
       // bookPage.search.click();
        //bookPage.search.sendKeys(bookName, Keys.ENTER);
        //or
        globalBookName=bookName;
        bookPage.bookSearch(bookName);



    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {
      //  System.out.println("bookPage.edditBookButtonInTable.isDisplayed() = " + bookPage.edditBookButtonInTable.isDisplayed());
      //   bookPage.edditBookButtonInTable.click();
      //  or

        bookPage.editBook(globalBookName).click();//use method editBook()


    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {


       /*

       //UI
       String titleAddBookWindow =  bookPage.editBookWindow.getText();

        System.out.println("titleAddBookWindow = " + titleAddBookWindow);

        BrowserUtil.switchToWindow(titleAddBookWindow);

        System.out.println("bookPage.bookNameInputBox.isDisplayed() = " + bookPage.bookNameInputBox.isDisplayed());
        System.out.println("bookPage.bookNameInputBox.getText() = " + bookPage.bookNameInputBox.getText());

        String expectedBookNameText = bookPage.bookName.getText();
        System.out.println("expectedBookNameText = " + expectedBookNameText);

        String expectedAutorName = bookPage.author.getText();
        System.out.println("expectedAutorName = " + expectedAutorName);

        String expectedISBN = bookPage.isbn.getText();
        System.out.println("expectedISBN = " + expectedISBN);

        String expectedYear = bookPage.year.getText();
        System.out.println("expectedYear = " + expectedYear);

        String expectedBookCategory = bookPage.categoryDropdown.getText();
        System.out.println("expectedBookCategory = " + expectedBookCategory);

        String expectedDescription = bookPage.description.getText();
        System.out.println("expectedDescription = " + expectedDescription);
*/


        //get name from db
        String query="select * from books where name ='Liudas book'";
        DB_Util.runQuery(query);

        Map<String, String> mapDataFromDB = DB_Util.getRowMap(1);
        System.out.println(mapDataFromDB);

        //get the name from db
        String name_db = mapDataFromDB.get("name");
        System.out.println("name_db = " + name_db);

        //get name from ui
        BrowserUtil.waitFor(3);

        String name_ui = bookPage.getBookInfo("Book Name");
        System.out.println("name_ui = " + name_ui);
        Assert.assertEquals(name_db,name_ui);


        //get year from db
        String year_db = mapDataFromDB.get("year");
        System.out.println("year_db = " + year_db);


        //get year from ui
        String year_ui = bookPage.getBookInfo("Year");
        System.out.println("year_ui = " + year_ui);
        Assert.assertEquals(year_db,year_ui);

        //get isbn from db
        String isbn_db = mapDataFromDB.get("isbn");
        System.out.println("isbn_db = " + isbn_db);

        //get isbn from ui
        String isbn_ui = bookPage.getBookInfo("ISBN");
        System.out.println("isbn_ui = " + isbn_ui);
        Assert.assertEquals(isbn_db,isbn_ui);


        //get author from db
        String author_db = mapDataFromDB.get("author");
        System.out.println("author_db = " + author_db);

        //get author from ui
        String author_ui = bookPage.getBookInfo("Author");
        System.out.println("author_ui = " + author_ui);
        Assert.assertEquals(author_db,author_ui);

        //get book category from db
        String book_category_db = mapDataFromDB.get("book_category_id");
        System.out.println("book_Category_id = " + book_category_db);
       /* String query1 ="select name\n" +
                "from book_categories\n" +
                "where id=(select book_category_id from books\n" +
                "          where id='14042');";
                   DB_Util.runQuery(query1);

                   String book_category_name_db = DB_Util.getCellValue(1,1);
        System.out.println("book_category_name_db = " + book_category_name_db);
*/

      //  BrowserUtil.waitFor(3);

        //get book category from ui
       // List<Integer> category = new ArrayList<>();
      //  System.out.println("BrowserUtil.getAllSelectOptions(bookPage.listMainCategoryElement.get(6)) = " + BrowserUtil.getAllSelectOptions(bookPage.listMainCategoryElement.get(6)));

        //String book_category_ui = bookPage.getBookInfo("Book Category");
        // System.out.println("Book_Category = " + book_category_ui);
        //Assert.assertEquals(book_category_name_db,book_category_ui);

        //get description from db
       // String description_db = mapDataFromDB.get("description");
       // System.out.println("description_db = " + description_db);

      //get description from ui
      //  String description_ui = bookPage.getBookInfo("Description");
      //  System.out.println("description_ui = " + description_ui);
      //  Assert.assertEquals(description_db,description_ui);



       /*
       //DB
       String actualBookNameText = DB_Util.getCellValue(1,2);
        System.out.println("actualBookNameText = " + actualBookNameText);
        Assert.assertEquals(expectedBookNameText,actualBookNameText);

        String actualAutorName = DB_Util.getCellValue(1,5);
        System.out.println("actualAutorName = " + actualAutorName);
        Assert.assertEquals(expectedAutorName,actualAutorName);

        String actualISBN = DB_Util.getCellValue(1,3);
        System.out.println("actualISBN = " + actualISBN);
        Assert.assertEquals(expectedISBN,actualISBN);


        String actualYear = DB_Util.getCellValue(1,4);
        System.out.println("actualYear = " + actualYear);
        Assert.assertEquals(expectedYear,actualYear);

        String actualBookCategory = DB_Util.getCellValue(1,6);
        System.out.println("actualBookCategory = " + actualBookCategory);
        Assert.assertEquals(expectedBookCategory,actualBookCategory);


        String actualDescription = DB_Util.getCellValue(1,7);
        System.out.println("actualDescription = " + actualDescription);
        Assert.assertEquals(expectedDescription,actualDescription);

*/
    }

}
