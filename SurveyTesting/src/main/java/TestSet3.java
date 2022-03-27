import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import lombok.Getter;

/**
 * Second test set. Tests whether the edit response functionality works.
 */
public class TestSet3 implements TestSet {
    private final MobileDriver<WebElement> driver;

    @Getter
    private TestSetResult result;

    /**
     * Creates this test set by fetching its data from the JSON resource file.
     *
     * @param driver The driver to use for this test
     */
    public TestSet3(MobileDriver<WebElement> driver) {
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

            // Filling all required fields
            String name = "Test";
            String surname = "Test";
            String bdate = "27/03/2021";
            String city = "Ankara";
            String gender = "Male";
            String vaccine = "BIONTECH";
            String sideEffect = "Pain";
            model.fillForm(name, surname, bdate, city, gender, vaccine, List.of(sideEffect));
            model.submitSurvey();


            return name.equals(model.getName()) && surname.equals(model.getSurname())
                    && bdate.equals(model.getBirthDate()) && city.equals(model.getCity())
                    && gender.equals(model.getGender()) && vaccine.equals(model.getVaccine())
                    && sideEffect.equals(model.getSideEffects());
        } catch (Exception e) {
            return false;
        }
    }
}