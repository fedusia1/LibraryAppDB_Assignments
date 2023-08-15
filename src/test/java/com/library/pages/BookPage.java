package com.library.pages;

import com.library.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(xpath = "//option[@value='1']")
    public WebElement firstInMainCategory;

    @FindBy(xpath = "//select[@class= 'form-control select2']")
    public List<WebElement> listMainCategoryElement;

    @FindBy(xpath = "//form[@id='add_book_form']//select[@id= 'book_group_id']")
    public List<WebElement> ListMainCategoryInForm;

    @FindBy(xpath = "//a[@class='btn btn-primary btn-sm']")
    public WebElement edditBookButton;

    @FindBy(xpath = "//table[@id='tbl_books']//td[.='Liudas book']/../td[1]//a")
    public WebElement edditBookButtonInTable;

    @FindBy(xpath ="(//input[@class='form-control'])[1]")
    public WebElement bookNameInputBox;


    //@FindBy(xpath = "//h5[@class='modal-title']")
    //@FindBy(id="edit_book_modal")
    @FindBy(id="edit_book_form")
    public WebElement editBookWindow;

    @FindBy(xpath = "//td[.='Liudas book']")
    public WebElement newBookName;

    @FindBy(name = "name")
    public WebElement bookName;


    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;

    @FindBy(xpath = "//div[@class='portlet-title']//a")
    public WebElement addBook;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveChanges;


    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;

    @FindBy(id="toast-container")
    public WebElement toastContainer;

    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "book_group_id")
    public WebElement categoryDropdown;



    @FindBy(id = "description")
    public WebElement description;


    @FindBy(xpath = "//div[text()='The book has been created.']")
    public WebElement popupMessage;


    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement borrowBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public String getBookInfo(String infoName){
        String locator="//form[@id='edit_book_form']//label[.='"+infoName+"']/../input";
        return Driver.getDriver().findElement(By.xpath(locator)).getAttribute("value");
    }

    public void bookSearch(String bookName) {
        search.sendKeys(bookName);

    }

}
