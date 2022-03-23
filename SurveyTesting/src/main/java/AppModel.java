import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;



public class AppModel {
    private static final By START_SURVEY = MobileBy.AccessibilityId("formInitBtn");
    private static final By SUBMIT = MobileBy.AccessibilityId("submissionBtn");

    private static final By GO_BACK = MobileBy.AccessibilityId("navBtnForm");
    private static final By SUBMITTED_GO_HOME = MobileBy.AccessibilityId("homeNavBtn");
    private static final By EDIT_RESPONSE = MobileBy.AccessibilityId("formNavBtn");

    private static final By NAME = MobileBy.AccessibilityId("nameInput");
    private static final By SURNAME = MobileBy.AccessibilityId("surnameInput");
    private static final By BIRTH_DATE = MobileBy.AccessibilityId("birthDateInput");
    private static final By CITY = MobileBy.AccessibilityId("cityInput");
    private static final By GENDER = MobileBy.AccessibilityId("genderSelection");
    private static final By VACCINE_TYPE = MobileBy.AccessibilityId("vaccineSelection");
    private static final By SIDE_EFFECTS = MobileBy.AccessibilityId("sideEffectSelection");

    private static final By RANDOM_DATE_ANDROID = By.xpath("/hierarchy/android.widget.FrameLayout/"
            + "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/"
            + "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
            + "android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup/"
            + "android.view.ViewGroup/android.view.ViewGroup[5]/android.view.ViewGroup[1]");
    private static final By RANDOM_DATE_IOS = By.xpath("(//XCUIElementTypeOther[@name=\"28\"])[1]");

    private static final int SLEEP_AMOUNT = 250;

    private MobileDriver<WebElement> driver;

    public AppModel(MobileDriver<WebElement> driver) {
        this.driver = driver;

        if (!canStartSurvey()) {
            goHome();
        }
    }


    public void startSurvey() {
        driver.findElement(START_SURVEY).click();
        sleep();
    }


    public void submitSurvey() {
        driver.findElement(SUBMIT).click();
        sleep();
    }

    /**
     * Write name into survey. If name is null, clear the field.
     *
     * @param name name to write
     */
    public void enterName(String name) {
        if (name == null) {
            getNameField().clear();
        } else
            getNameField().sendKeys(name);
        sleep();
    }

    /**
     * Write surname into survey. If surname is null, clear the field.
     *
     * @param surname surname to write
     */
    public void enterSurname(String surname) {
        if (surname == null) {
            getSurnameField().clear();
        } else {
            getSurnameField().sendKeys(surname);
        }
        sleep();
    }

    /**
     * Write birth date into survey
     *
     * @param bdate birth date to write
     */
    public void enterBirthDate(String bdate) {
        // Due to Appium limitations, cannot test actual bdate value
        driver.findElement(BIRTH_DATE).click();
        driver.findElement("ios".equals(driver.getPlatformName()) ? RANDOM_DATE_IOS : RANDOM_DATE_ANDROID).click();
        sleep();
    }

    /**
     * Write gender into survey
     *
     * @param gender gender to write
     */
    public void enterGender(String gender) {
        driver.findElement(GENDER).click();
        driver.findElementByAccessibilityId(gender).click();
        sleep();
    }

    /**
     * Write city into survey. If city is null, clear the field.
     *
     * @param city city to write
     */
    public void enterCity(String city) {
        if (city == null) {
            getCityField().clear();
        } else {
            getCityField().sendKeys(city);
        }
        sleep();
    }

    /**
     * Write vaccine type into survey
     *
     * @param type vaccine type to write
     */
    public void enterVaccineType(String type) {
        driver.findElement(VACCINE_TYPE).click();
        driver.findElementByAccessibilityId(type).click();
        sleep();
    }

    /**
     * Toggle side effect in survey
     *
     * @param sideEffect side effect to toggle
     */
    public void toggleSideEffect(String sideEffect) {
        toggleSideEffects(List.of(sideEffect));
    }

    /**
     * Toggle a list of side effect in survey
     *
     * @param sideEffects side effects to toggle
     */
    public void toggleSideEffects(List<String> sideEffects) {
        if (sideEffects.isEmpty())
            return;

        driver.findElement(SIDE_EFFECTS).click();
        sideEffects.forEach(sideEffect -> driver.findElementByAccessibilityId(sideEffect).click());
        loseFocus();
        sleep();
    }

    /**
     * Fills form with the given data
     *
     * @param name
     * @param surname
     * @param bdate
     * @param city
     * @param gender
     * @param vaccine
     * @param sideEffects
     */
    public void fillForm(String name, String surname, String bdate, String city, String gender, String vaccine,
                         List<String> sideEffects) {
        enterName(name);
        enterSurname(surname);
        enterBirthDate(bdate);
        enterCity(city);
        enterGender(gender);
        enterVaccineType(vaccine);
        toggleSideEffects(sideEffects);
    }

    /**
     * @return Currently entered name value in the survey
     */
    public String getName() {
        return getNameField().getText();
    }

    /**
     * @return Currently entered surname value in the survey
     */
    public String getSurname() {
        return getSurnameField().getText();
    }

    /**
     * @return Currently entered city value in the survey
     */
    public String getCity() {
        return getCityField().getText();
    }

    /**
     * @return Currently entered birth date value in the survey
     */
    public String getBirthDate() {
        return driver.findElement(BIRTH_DATE).findElement(By.xpath("//android.widget.TextView")).getText();
    }

    /**
     * @return Currently entered gender value in the survey
     */
    public String getGender() {
        return driver.findElement(GENDER).findElement(By.xpath("//android.widget.TextView[@text!='Gender']")).getText();
    }

    /**
     * @return Currently entered vaccine type value in the survey
     */
    public String getVaccine() {
        return driver.findElement(VACCINE_TYPE)
                .findElement(By.xpath("//android.widget.TextView[@text!='Vaccine Type']")).getText();
    }

    /**
     * @return Currently entered side effects value in the survey
     */
    public String getSideEffects() {
        return driver.findElement(SIDE_EFFECTS)
                .findElement(By.xpath("//android.widget.TextView[@text!='Side Effects']")).getText();
    }

    /**
     * @return true if the home page is shown and survey start button is visible,
     * false otherwise
     */
    public boolean canStartSurvey() {
        return doesExist(START_SURVEY);
    }

    /**
     * @return true if the survey submit button is visible, false otherwise
     */
    public boolean isSubmitButtonVisible() {
        try {
            WebElement btn = driver.findElement(SUBMIT);
            return btn != null && btn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @return true if the survey submitted page is shown and go home button is
     * visible
     */
    public boolean isSubmitted() {
        return doesExist(SUBMITTED_GO_HOME);
    }

    /**
     * Tries going back from the survey screen.
     */
    public void goBack() {
        driver.findElement(GO_BACK).click();
        sleep();
    }

    /**
     * Tries going home from the survey submitted page.
     */
    public void goHomeAfterSurvey() {
        driver.findElement(SUBMITTED_GO_HOME).click();
    }

    /**
     * Tries going to home screen.
     */
    public void goHome() {
        if (doesExist(GO_BACK)) {
            goBack();
        } else {
            goHomeAfterSurvey();
        }
        sleep();
    }

    /**
     * Requests to edit the currently submitted response.
     */
    public void editResponse() {
        driver.findElement(EDIT_RESPONSE).click();
    }

    /**
     * Sleep to make sure UI keeps up.
     */
    private void sleep() {
        try {
            if ("ios".equals(driver.getPlatformName())) {
                Thread.sleep(SLEEP_AMOUNT / 2);
                loseFocus();
                Thread.sleep(SLEEP_AMOUNT / 2);
            } else {
                Thread.sleep(SLEEP_AMOUNT);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Lose focus of the current element by clicking aside.
     */
    private void loseFocus() {
        new TouchAction<>(driver).press(PointOption.point(0, 100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).release().perform();
    }

    /**
     * @param selector
     * @return true if element exists, false otherwise
     */
    private boolean doesExist(By selector) {
        return !driver.findElements(selector).isEmpty();
    }

    /**
     * @return the survey name input element
     */
    private WebElement getNameField() {
        List<WebElement> list = driver.findElements(NAME);
        return list.get(list.size() - 1);
    }

    /**
     * @return the survey surname input element
     */
    private WebElement getSurnameField() {
        List<WebElement> list = driver.findElements(SURNAME);
        return list.get(list.size() - 1);
    }

    /**
     * @return the survey city input element
     */
    private WebElement getCityField() {
        List<WebElement> list = driver.findElements(CITY);
        return list.get(list.size() - 1);
    }
}