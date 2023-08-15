package com.library.pages;

import com.library.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BorrowedBooksPage extends BasePage{


    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> allBorrowedBooksName;

    //@FindBy(xpath = "//div[@class='toast-message']")
    @FindBy(id="toast-container")
    public WebElement borrowMessage;


    public WebElement tableLiudasBook(String book){
        String xpath = "//table[@id='borrowed_list']//td[.='"+ book +"']//..//td[6]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

   /* @FindBy(xpath = "//table[@id='tbl_books']//tr//td[.='Shcodareser']//..//a//i")
    public WebElement borrowBookButton;
*/
    public WebElement borrowBookButton(String book) {
        String xpath = "//table[@id='tbl_books']//tr//td[.='" + book + "']//..//a//i";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    @FindBy(xpath = "(//span[@class='title'])[2]")
    public WebElement borrowingBooksLink;

    @FindBy(xpath = "//h3[.='Borrowing Books']")
    public WebElement getBorrowBooksTitle;


    public WebElement returnedNotReturnedText(String book){
        String xpath ="//table[@id='borrowed_list']//td[.='"+ book +"']/../td[6]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }








}
