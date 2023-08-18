package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AmazonHomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class IosBaseTest {
    IOSDriver driver;
    AppiumDriverLocalService service;
    AmazonHomePage amazonHomePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpIos() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("/Users/max_horokhov/Desktop/Projects/Appium/AppiumAmazonTask/src/main/java/resources/global.properties");
        properties.load(fileInputStream);

        String ip = properties.getProperty("ip");
        String portString = properties.getProperty("port");
        String deviceName = properties.getProperty("appleDeviceName");
        String browserName = properties.getProperty("browserApple");
        String applePlatformVersion = properties.getProperty("applePlatformVersion");
        int port = Integer.parseInt(portString);
        String pathToMainJs = properties.getProperty("pathToMainJs");
        String androidDriverMachineUrl = properties.getProperty("androidDriverMachineUrl");

        service = new AppiumServiceBuilder().withAppiumJS(new File(pathToMainJs)).withIPAddress(ip).usingPort(port).build();
        service.start();

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName(deviceName);
        options.setCapability("browserName", browserName);
        options.setCapability("platformVersion", applePlatformVersion);
        options.setCapability("newCommandTimeout", 300);

        driver = new IOSDriver(new URL(androidDriverMachineUrl), options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
        service.stop();
    }
    public AmazonHomePage launchBrowserApplication(String url){
        driver.get(url);
        driver.navigate().refresh();
        amazonHomePage = new AmazonHomePage(driver);
        return amazonHomePage;
    }
}
