package pages;

import actions.AmazonBooksActions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {
    AppiumDriver driver;
    AmazonBooksActions amazonBooksActions;
    public AmazonHomePage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @FindBy(xpath = "//i[@class='nav-icon-a11y nav-sprite']")
    WebElement filterOptions;
    @FindBy(xpath = "//a[@class='hmenu-item' and contains(text(), 'Books')][1]")
    WebElement bookFilter;
    @FindBy(xpath = "//input[@placeholder='Search Amazon']")
    WebElement searchBar;
    @FindBy(xpath = "//input[@class='nav-input']")
    WebElement searchButton;
    public void openFilters() {
        filterOptions.click();
    }
    public void selectBooksFilter(){
        bookFilter.click();
    }
    public void fillUpSearchBar(String text){
        searchBar.sendKeys(text);
    }
    public AmazonBooksActions clickSearchButton(){
        searchButton.click();
        amazonBooksActions = new AmazonBooksActions(driver);
        return amazonBooksActions;
    }

}
