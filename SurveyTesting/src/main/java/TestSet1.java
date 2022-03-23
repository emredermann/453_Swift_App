import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import lombok.Getter;

/**
 * First test set. Includes test cases for testing various combinations of
 * inputs. In particular, tests valid values, and invalid values for each input
 * field.
 */
public class TestSet1 implements TestSet {
    private final JSONArray data;
    private final MobileDriver<WebElement> driver;

    @Getter
    private TestSetResult result;

    /**
     * Creates this test set by fetching its data from the JSON resource file.
     *
     * @param driver The driver to use for this test
     */
    public TestSet1(MobileDriver<WebElement> driver) {
        JSONTokener tokener = new JSONTokener(getClass().getResourceAsStream("TestCaseOneData.json"));
        data = new JSONArray(tokener);
        this.driver = driver;
        result = new TestSetResult(0, data.length());
    }

    public TestSetResult run() {
        data.forEach(obj -> {
            boolean caseResult = run((JSONObject) obj);
            if (caseResult) {
                result.incrementPassedCases();
            }
        });
        return result;
    }

    /**
     * Runs a test case specified by the JSON object.
     *
     * @param data JSON data object
     * @return true if the test succeeds, false otherwise
     */
    private boolean run(JSONObject data) {
        String caseResult;
        try {
            AppModel model = new AppModel(driver);
            model.startSurvey();

            List<String> sideEffects = data.getJSONArray("side_effects").toList().stream().map(Object::toString)
                    .collect(Collectors.toList());
            model.fillForm(data.getString("name"), data.getString("surname"), data.getString("birthdate"),
                    data.getString("city"), data.getString("gender"), data.getString("vaccine"), sideEffects);
            model.submitSurvey();

            if (model.isSubmitted()) {
                caseResult = "success";
                model.goHomeAfterSurvey();
            } else {
                caseResult = "fail";
            }
        } catch (Exception e) {
            caseResult = "fail";
        }
        return caseResult.equals(data.getString("expected_result"));
    }
}