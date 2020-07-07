import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;

public class ProductsTest extends TestSetup{

    @BeforeMethod
    public void openProducts(){
        open("https://testautoqa.000webhostapp.com/products.html");
    }

    @Test()
    public void checkURl() {
        assertThat(url()).isEqualTo("https://testautoqa.000webhostapp.com/products.html");
    }

    @Test()
    public void checkMenu() {
        assertThat($("a[href='products.html']").is(visible)).isTrue();
        assertThat($("a[href='products.html']").getAttribute("class")).contains("active");
        assertThat($("a[href='about.html']").is(visible)).isTrue();
        assertThat($("a[href='delivery.html']").is(visible)).isTrue();
    }

    @Test()
    public void checkProducts() {
        assertThat($$("div.container").size()).isEqualTo(8);
        assertThat($$("div.container  img").size()).isEqualTo(8);
        assertThat($$("div.container  input[type='checkbox']").size()).isEqualTo(8);
        assertThat($$(xpath("//div[@class='container']//div[contains(text(),.)]")).size()).isEqualTo(8);
    }

    @Test()
    public void selectOnlyUHD() {
        $("button[onclick=\"filterSelection('uhd')\"]").click();
        $$(xpath("//div[contains(text(),'UHD')]/preceding-sibling::div/input")).forEach(c -> assertThat(c.is(checked)).isTrue());
        assertThat($((".selectedValue span")).getText()).isEqualTo("2");
    }

    @Test()
    public void resetSelection() {
        $$(xpath("//input[@type='checkbox']")).forEach(SelenideElement::click);
        $("button[onclick=\"filterSelection('none')\"]").click();
        $$(xpath("(//input[@type='checkbox'])")).forEach(c -> assertThat(c.is(not(Condition.checked))));
        assertThat($((".selectedValue span")).getText()).isEqualTo("0");
    }

    @Test()
    public void checkBoxesCheck() {
        AtomicInteger check = new AtomicInteger();
        $$(xpath("//input[@type='checkbox']")).forEach(c -> {
            c.click();
            assertThat(c.is(checked)).isTrue();
            assertThat(parseInt($((".selectedValue span")).getText())).isEqualTo(check.incrementAndGet());
        });
    }

    @Test()
    public void unCheckCheckBoxes() {
        $$(xpath("//input[@type='checkbox']")).forEach(SelenideElement::click);
        AtomicInteger check = new AtomicInteger(8);
        $$(xpath("//input[@type='checkbox']")).forEach(c -> {
            c.click();
            assertThat(c.is(not(checked))).isTrue();
            assertThat(parseInt($((".selectedValue span")).getText())).isEqualTo(check.decrementAndGet());
        });
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

    @Test()
    public void checkBestProducts() {
        $("a[href='https://bit.ly/2NhHZwe']").click();
        assertThat(url()).isEqualTo("https://bit.ly/2NhHZwe");
    }

    @Test()
    public void checkNewProducts() {
        $("a[href='https://bit.ly/37O2ytt']").click();
        assertThat(url()).isEqualTo("https://bit.ly/37O2ytt");
    }
}
