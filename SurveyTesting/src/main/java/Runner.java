import io.appium.java_client.MobileDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;


/**
 * Runs all the test sets and displays their results.
 */
public class Runner {
    private static final String APP_RESOURCE = "app.apk";
    private static final String APPIUM_SERVER = "http://localhost:4723/wd/hub";

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        String path = Paths.get(Runner.class.getResource(APP_RESOURCE).toURI()).toFile().getAbsolutePath();
        capabilities.setCapability("app", path);
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "13.3");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("bundleId", "com.google.Chrome");
        capabilities.setCapability("updatedWDABundleId", "com.google.chrome.ios");


        IOSDriver driver = new IOSDriver<>(new URL(APPIUM_SERVER), capabilities);

        // Initial loading wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new AppModel(driver);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

      
        TestSet[] sets = { new TestSet1(driver), new TestSet2(driver) };

        for (int setNum = 0; setNum < sets.length; setNum++) {
            System.out.println("Test Set " + (setNum + 1) + ": " + sets[setNum].run());
            overall.add(sets[setNum].getResult());
        }
        driver.quit();
        System.out.println();
        System.out.println("Overall Test Results: \t" + overall);
    }
}