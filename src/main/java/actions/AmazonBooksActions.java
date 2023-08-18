package actions;

import base.BaseActions;
import entities.Book;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import pages.AmazonBookList;
import pages.AmazonBookPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AmazonBooksActions extends BaseActions {
    List<Book> bookList = new ArrayList<>();
    AppiumDriver driver;
    AmazonBookList amazonBookList;
    public AmazonBooksActions(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    public List<Book> collectEachBookInTheList(){
        amazonBookList = new AmazonBookList(driver);
        System.out.println(amazonBookList.getSize());
        for (int i = 1; i <= amazonBookList.getSize(); i++) {
            By eachNextBookLink = amazonBookList.setEveryNextProductLink(new By.ByXPath("(//a[@title='product-image'])"+"["+i+"]"));
            Book book = amazonBookList.checkEveryProductStats(eachNextBookLink);;
            bookList.add(book);
            if(i>=16){
                break;
            }
        }
        return bookList;
    }
    public Book getGivenBookInfo(String url){
        driver.get(url);
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String child = it.next();
        driver.switchTo().window(child);
        wait(5);
        AmazonBookPage amazonBookPage = new AmazonBookPage(driver);
        Book givenBook = amazonBookPage.getBookInfo();
        return givenBook;
    }

    public List<Book> collectEachBookInTheListApple(){
        amazonBookList = new AmazonBookList(driver);
        System.out.println(amazonBookList.getSize());
        for (int i = 1; i <= amazonBookList.getSize(); i++) {
            By eachNextBookLink = amazonBookList.setEveryNextProductLink(new By.ByXPath("(//a[@title='product-image'])"+"["+i+"]"));
            Book book = amazonBookList.checkEveryProductStatsApple(eachNextBookLink);;
            bookList.add(book);
            if(i>=16){
                break;
            }
        }
        return bookList;
    }
    public Book getGivenBookInfoApple(String url){
        driver.get(url);
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String child = it.next();
        driver.switchTo().window(child);
        wait(5);
        AmazonBookPage amazonBookPage = new AmazonBookPage(driver);
        Book givenBook = amazonBookPage.getBookInfo();
        return givenBook;
    }
}
