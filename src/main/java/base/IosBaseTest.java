package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;
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
//        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream("/Users/max_horokhov/Desktop/Projects/Appium/AppiumAmazonTask/src/main/java/resources/global.properties");
//        properties.load(fileInputStream);
//
//        String ip = properties.getProperty("ip");
//        String portString = properties.getProperty("port");
//        String deviceName = properties.getProperty("appleDeviceName");
//        String browserName = properties.getProperty("browserApple");
//        String applePlatformVersion = properties.getProperty("applePlatformVersion");
//        int port = Integer.parseInt(portString);
//        String pathToMainJs = properties.getProperty("pathToMainJs");
//        String androidDriverMachineUrl = properties.getProperty("androidDriverMachineUrl");

//        service = new AppiumServiceBuilder().withAppiumJS(new File(pathToMainJs)).withIPAddress(ip).usingPort(port).build();
//        service.start();

        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "iOS");
        caps.setCapability("browserName", "Safari");
        caps.setCapability("appium:deviceName", "iPhone Simulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion", "16.2");
        caps.setCapability("appium:automationName", "XCUITest");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "2.0.0");
        sauceOptions.setCapability("username", "oauth-max_horokhov-859e7");
        sauceOptions.setCapability("accessKey", "42a8932b-f474-495b-bcdf-7c6d8c421d84");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new IOSDriver(url, caps);
//        options.setDeviceName(deviceName);
//        options.setCapability("browserName", browserName);
//        options.setCapability("platformVersion", applePlatformVersion);
//        options.setCapability("newCommandTimeout", 300);
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
