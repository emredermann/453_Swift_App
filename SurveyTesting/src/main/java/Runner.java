import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Runner {
    private static final String APPIUM_SERVER = "http://localhost:4723/wd/hub";

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("bundleId", "com.google.Chrome");
        capabilities.setCapability("updatedWDABundleId", "com.google.chrome.ios");


        IOSDriver driver = new IOSDriver(new URL(APPIUM_SERVER), capabilities);

        // Initial loading wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        new AppModel(driver);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        new TestSet(driver).run();

        driver.quit();
        System.out.println();
    }
}