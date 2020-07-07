import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static com.codeborne.selenide.logevents.SelenideLogger.removeListener;

public class TestSetup {
    @BeforeSuite
    public void setup(){
        Configuration.browser = "firefox";
        Configuration.browserVersion = "77";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserSize = "1920x1080";
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("enableVNC", true);
        firefoxOptions.setCapability("enableVideo", false);
        Configuration.browserCapabilities = firefoxOptions;
        Configuration.timeout = 5;
        addListener("allure", new AllureSelenide());
    }

    @AfterSuite
    public void teardown(){
        removeListener("allure");
    }
}
