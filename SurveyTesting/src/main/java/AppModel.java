import java.time.Duration;
import java.util.List;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static java.lang.Thread.sleep;


public class AppModel {

    private static final By SUBMIT = MobileBy.AccessibilityId("add_button");
    private static final By NAME = MobileBy.AccessibilityId("name_attr");
    private static final By SURNAME = MobileBy.AccessibilityId("surname_attr");
    private static final By BIRTH_DATE = MobileBy.AccessibilityId("birth_date_attr");
    private static final By CITY = MobileBy.AccessibilityId("city_attr");
    private static final By GENDER = MobileBy.AccessibilityId("gender_attr");
    private static final By VACCINE_TYPE = MobileBy.AccessibilityId("applied_vaccine_attr");
    private static final By SIDE_EFFECTS = MobileBy.AccessibilityId("side_effect_vaccination_attr");
    private static final By SYMPTOMS = MobileBy.AccessibilityId("pcr_positive_cases_and_symproms_attr");

    private static final int SLEEP_AMOUNT = 250;

    private IOSDriver driver;

    public AppModel(IOSDriver driver) {
        this.driver = driver;
    }

    public void submitSurvey() throws InterruptedException {
        driver.findElement(SUBMIT).click();
        sleep(100);
    }

    public void enterName(String name) {
        if (name == null) {
            getNameField().clear();
        } else
            getNameField().sendKeys(name);

    }


    public void enterSurname(String surname){
        if (surname == null) {
            getSurnameField().clear();
        } else {
            getSurnameField().sendKeys(surname);
        }

    }

    public void enterBirthDate(String bdate){
       if (bdate == null) {
            getBirthDate().clear();
        } else {
            getBirthDate().sendKeys(bdate);
        }
    }

    public void enterGender(String gender) {
        if (gender == null) {
            getGenderField().clear();
        } else {
            getGenderField().sendKeys(gender);
        }
    }


    public void enterCity(String city) {
        if (city == null) {
            getCityField().clear();
        } else {
            getCityField().sendKeys(city);
        }
    }

    public void enterVaccineType(String vaccineType){
        if (vaccineType == null) {
            getVaccineType().clear();
        } else {
            getVaccineType().sendKeys(vaccineType);
        }
    }
    public void enterSymptoms(String symptoms) {
        if (symptoms == null) {
            getSymptoms().clear();
        } else {
            getSymptoms().sendKeys(symptoms);
        }
    }

    public void toggleSideEffects(String sideEffects) {
        if (sideEffects == null) {
            getSideEffects().clear();
        } else {
            getSideEffects().sendKeys(sideEffects);
        }
    }


    public void fillForm(String name, String surname, String bdate, String city, String gender, String vaccine,
                         String sideEffects,String symptoms) {
        enterName(name);
        enterSurname(surname);
        enterBirthDate(bdate);
        enterCity(city);
        enterGender(gender);
        enterVaccineType(vaccine);
        enterSymptoms(symptoms);
        toggleSideEffects(sideEffects);
    }


    public String getName() {
        return getNameField().getText();
    }


    public String getSurname() {
        return getSurnameField().getText();
    }


    public String getCity() {
        return getCityField().getText();
    }
    
    public boolean isSubmitButtonVisible() {
        try {
            WebElement btn = driver.findElement(SUBMIT);
            return btn != null && btn.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    private void loseFocus() {
        new TouchAction<>(driver).press(PointOption.point(0, 100))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100))).release().perform();
    }
    private boolean doesExist(By selector) {
        return !driver.findElements(selector).isEmpty();
    }
    private WebElement getSurnameField() {
            List<WebElement> list = driver.findElements(SURNAME);
            return list.get(list.size() - 1);
        }
    private WebElement getSideEffects() {
        List<WebElement> list = driver.findElements(SIDE_EFFECTS);
        return list.get(list.size() - 1);
    }
    private WebElement getNameField() {
            List<WebElement> list = driver.findElements(NAME);
            return list.get(list.size() - 1);
        }
    private WebElement getSymptoms() {
            List<WebElement> list = driver.findElements(SYMPTOMS);
            return list.get(list.size() - 1);
        }
    private WebElement getGenderField() {
            List<WebElement> list = driver.findElements(GENDER);
            return list.get(list.size() - 1);
        }
    private WebElement getCityField() {
            List<WebElement> list = driver.findElements(CITY);
            return list.get(list.size() - 1);
        }
    private WebElement getVaccineType() {
            List<WebElement> list = driver.findElements(VACCINE_TYPE);
            return list.get(list.size() - 1);
        }
    private WebElement getBirthDate() {
            List<WebElement> list = driver.findElements(BIRTH_DATE);
            return list.get(list.size() - 1);
        }
}