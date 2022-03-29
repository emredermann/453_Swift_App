import java.util.List;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileDriver;
import lombok.Getter;


public class TestSet {
    private final IOSDriver driver;

    public TestSet(IOSDriver driver) {
        this.driver = driver;
    }

    public void run() {
        test();
        test_2();
        test_3();
        test_4();
        test_5();
    }
    // With Empty inputs
    private boolean test_5() {
        try{
            AppModel model = new AppModel(driver);
            model.fillForm("",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "");
            return model.isSubmitButtonVisible();
        }catch (Exception e){
            return false;
        }
    }
    // Test the extensive string parameter
    private boolean test_4() {
        try {
            AppModel model = new AppModel(driver);
            model.fillForm("adwlkadlwwlakdjawkljdklwajdklwajdlkawjdkljawdkkawdljawkldawkljdklawjdlwajdawlkdjklawjdkllawjdlwadjawlkdjlakwjdljwakwdjlkdajlkwjdlkdjwalkjdlwkadkljawlkdjwlakjdljawdjlkjdwakdawljwakljdawldawldwaj",
                    "Derman",
                    "",
                    "Ankara",
                    "Male",
                    "Biontecth",
                    "Pain",
                    " None");
            return model.isSubmitButtonVisible();
        } catch (Exception e){
            return false;
        }
    }

    // Empty input case and add button relation
    public boolean test_3() {
        try {
            AppModel model = new AppModel(driver);
            model.fillForm("Emre", "Derman", "", "Ankara", "Male", "Biontecth", "Pain", " None");
            return model.isSubmitButtonVisible();
        } catch (Exception e) {
            return false;
        }
    }
    //fills all fields appropriate.
    public boolean test_2()
    {
        try {
            AppModel model = new AppModel(driver);
            model.fillForm("Test", "Test", "01.10.2022", "Ankara", "Male", "Biontecth","Pain"," None");
            if (!model.isSubmitButtonVisible()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    // Test the city field
    private boolean test() {
        try {
            AppModel model = new AppModel(driver);
            model.fillForm("Emre", "Derman", "", "kara", "Male", "Biontecth","Pain"," None");
            return model.isSubmitButtonVisible();
        } catch (Exception e) {
            return false;
        }
    }
}