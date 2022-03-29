import java.util.List;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import lombok.Getter;

/**
 * Second test set. Tests whether the survey submit button appears only after
 * all the fields are filled, and is not visible any other time.
 */
public class TestSet {
    private final IOSDriver driver;


    public TestSet(IOSDriver driver) {
        this.driver = driver;
    }
    public boolean test_2()
    {
        try {
            AppModel model = new AppModel(driver);
            // Filling all required fields
            model.fillForm("Test", "Test", "01.10.2022", "Ankara", "Male", "Biontecth","Pain"," None");
            if (!model.isSubmitButtonVisible()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public void run() {
        test();
        test_2();
    }

    private boolean test() {
        try {
            AppModel model = new AppModel(driver);
            model.fillForm("Emre", "Derman", "", "Ankara", "Male", "Biontecth","Pain"," None");
            if (!model.isSubmitButtonVisible()) {
                return false;
            }
            model.enterName("");
            if (model.isSubmitButtonVisible()) {
                return false;
            }
            model.enterName("Test");
            model.enterSurname("");
            if (model.isSubmitButtonVisible()) {
                return false;
            }
            model.enterSurname("Test");
            model.enterCity("");
            if (model.isSubmitButtonVisible()) {
                return false;
            }
            model.enterCity("Izmir");
          model.toggleSideEffects("Pain and rush");
            return model.isSubmitButtonVisible();
        } catch (Exception e) {
            return false;
        }
    }
}