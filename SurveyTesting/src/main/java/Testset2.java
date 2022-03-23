import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import lombok.Getter;

/**
 * Second test set. Tests whether the survey submit button appears only after
 * all the fields are filled, and is not visible any other time.
 */
public class Testset2 implements TestSet {
    private final MobileDriver<WebElement> driver;

    @Getter
    private TestSetResult result;

    /**
     * Creates this test set by fetching its data from the JSON resource file.
     *
     * @param driver The driver to use for this test
     */
    public TestSet2(MobileDriver<WebElement> driver) {
        this.driver = driver;
        result = new TestSetResult(0, 1);
    }

    public TestSetResult run() {
        if (test()) {
            result.incrementPassedCases();
        }
        return result;
    }

    private boolean test() {
        try {

            AppModel model = new AppModel(driver);
            model.startSurvey();

            // Filling all required fields
            model.fillForm("Test", "Test", "01.01.2001", "Batman", "Male", "AZD1222", List.of());
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
            model.enterCity("Adana");

            model.toggleSideEffects(List.of("Headache", "Swelling", "Pain"));
            return model.isSubmitButtonVisible();
        } catch (Exception e) {
            return false;
        }
    }
}