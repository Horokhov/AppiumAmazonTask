package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.AmazonHomePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class BaseTestAndroid {
    AndroidDriver driver;
    AppiumDriverLocalService service;
    AmazonHomePage amazonHomePage;

    @BeforeMethod(alwaysRun = true)
    public void setUpAndroid() throws IOException {
//        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream("/Users/max_horokhov/Desktop/Projects/Appium/AppiumAmazonTask/src/main/java/resources/global.properties");
//        properties.load(fileInputStream);
//
//        String ip = properties.getProperty("ip");
//        String portString = properties.getProperty("portAndroid");
//        int port = Integer.parseInt(portString);
//        String pathToMainJs = properties.getProperty("pathToMainJs");
//        String pathToChromedriverExecutable = properties.getProperty("pathToChromedriverExecutable");
//        String browser = properties.getProperty("browserAndroid");
//        String platformVersion = properties.getProperty("androidPlatformVersion");
//        String androidDeviceName = properties.getProperty("androidDeviceName");
//        String androidDriverMachineUrl = properties.getProperty("androidDriverMachineUrl");
//
//        service = new AppiumServiceBuilder().withAppiumJS(new File(pathToMainJs)).withIPAddress(ip).usingPort(port).build();
//        service.start();
//
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName(androidDeviceName);
//        options.setChromedriverExecutable(pathToChromedriverExecutable);
//        options.setCapability("browserName", browser);
//        options.setCapability("platformVersion", platformVersion);
//        options.setCapability("newCommandTimeout", 300);
//
//        driver = new AndroidDriver(new URL(androidDriverMachineUrl), options);
//        Set<String> context = driver.getContextHandles();
//        driver.context("CHROMIUM");
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        caps.setCapability("appium:deviceOrientation", "portrait");
        caps.setCapability("appium:platformVersion", "current_major");
        caps.setCapability("appium:automationName", "UiAutomator2");
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("appiumVersion", "2.0.0");
        sauceOptions.setCapability("username", "oauth-max_horokhov-859e7");
        sauceOptions.setCapability("accessKey", "42a8932b-f474-495b-bcdf-7c6d8c421d84");
        caps.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        driver = new AndroidDriver(url, caps);
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
