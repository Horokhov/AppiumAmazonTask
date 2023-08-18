package pages;

import entities.Book;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonBookPage {
    AppiumDriver driver;
    public AmazonBookPage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    By bookTitle = new By.ByXPath("//h1[@id='title']|//h4[@id='title']|//span[@id='ebooksTitle']");
    By bookPrice = new By.ByXPath("//div[@id='tmm-grid-swatch-PAPERBACK']|//div[@id='tmm-grid-swatch-HARDCOVER']");
    By bookBestsellerStatus = new By.ByXPath("//div[@role='main']");
    By bookAuthor = new By.ByXPath("//div[@id='bylineInfo']");
    String bestSeller = "Best Seller";
    public Book getBookInfo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitle));

        String name = driver.findElement(bookTitle).getText();

        String author = driver.findElement(bookAuthor).getText();

        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,400)","");
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookPrice));

        String price = driver.findElement(bookPrice).getText();

        boolean status = driver.findElement(bookBestsellerStatus).getText().contains(bestSeller);

        Book bookInList = new Book(name, author, price, status);
        return bookInList;
    }

    public Book getBookInfoApple(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitle));

        String name = driver.findElement(bookTitle).getText();

        String author = driver.findElement(bookAuthor).getText();

        wait.until(ExpectedConditions.visibilityOfElementLocated(bookPrice));

        String price = driver.findElement(bookPrice).getText();

        boolean status = driver.findElement(bookBestsellerStatus).getText().contains(bestSeller);

        Book bookInList = new Book(name, author, price, status);
        return bookInList;
    }
}
