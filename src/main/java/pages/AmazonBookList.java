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

public class AmazonBookList {
    AppiumDriver driver;
    public AmazonBookList(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @FindBy(xpath = "//a[@title='product-image']")
    List<WebElement> books;
    By everyNextProductLink = new By.ByXPath("");
    public int getSize() {
        int size = books.size();
        return size;
    }
    public By setEveryNextProductLink(By everyNextProductLink) {
        this.everyNextProductLink = everyNextProductLink;
        return everyNextProductLink;
    }
    public Book checkEveryProductStats(By everyNextProductLink){
        Actions a = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(everyNextProductLink));
        a.moveToElement(driver.findElement(everyNextProductLink)).perform();
        a.click().perform();
        Book book = new AmazonBookPage(driver).getBookInfo();
        driver.navigate().back();
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,200)","");
        wait.until(ExpectedConditions.visibilityOfElementLocated(everyNextProductLink));
        return book;
    }
    public Book checkEveryProductStatsApple(By everyNextProductLink){
        Actions a = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(everyNextProductLink));
        driver.findElement(everyNextProductLink).click();
        Book book = new AmazonBookPage(driver).getBookInfoApple();
        driver.navigate().back();
        Map<String, Object> params = new HashMap<>();

        wait.until(ExpectedConditions.visibilityOfElementLocated(everyNextProductLink));
        return book;
    }


    }
