import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class AboutTest extends TestSetup{

    @BeforeMethod
    public void openAboutPage(){
        open("https://testautoqa.000webhostapp.com/about.html");
    }

    @Test()
    public void checkAboutPageURl() {
        assertThat(url()).isEqualTo("https://testautoqa.000webhostapp.com/about.html");
    }

    @Test()
    public void checkAboutPageMenu() {
        assertThat($("a[href='products.html']").is(visible)).isTrue();
        assertThat($("a[href='about.html']").is(visible)).isTrue();
        assertThat($("a[href='about.html']").getAttribute("class")).contains("active");
        assertThat($("a[href='delivery.html']").is(visible)).isTrue();
    }

    @Test()
    public void checkText() {
        $$("*").stream().filter(d -> d.getText().contains("PageMaker")).findFirst().orElseThrow(AssertionError::new);
    }

    @Test()
    public void checkBottomDiv() {
        assertThat($("div[data-id='contactMessage']").getAttribute("style")).contains("background-color: red");
        assertThat($("div[data-id='contactMessage']").getText()).contains("If you have any further questions, please do not hesitate to contact us.");

    }

    @Test()
    public void checkIOS() {
        $("a[href=\"https://www.apple.com/\"]").click();
        assertThat(url()).isEqualTo("https://www.apple.com/");
    }

    @Test()
    public void checkAndroid() {
        $("a[href='https://www.google.com/']").click();
        assertThat(url()).isEqualTo("https://www.google.com/");
    }

}
