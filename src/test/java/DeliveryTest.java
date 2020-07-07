import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.By.xpath;

public class DeliveryTest extends TestSetup{

    @BeforeMethod
    public void openDelivery(){
        open("https://testautoqa.000webhostapp.com/delivery.html");
    }

    @Test()
    public void checkDeliveryPageURl() {
        assertThat(url()).isEqualTo("https://testautoqa.000webhostapp.com/delivery.html");
    }

    @Test()
    public void checkDeliveryPageMenu() {
        assertThat($("a[href='products.html']").is(visible)).isTrue();
        assertThat($("a[href='about.html']").is(visible)).isTrue();
        assertThat($("a[href='delivery.html']").is(visible)).isTrue();
        assertThat($("a[href='delivery.html']").getAttribute("class")).contains("active");
    }

    @Test()
    public void checkDefaultImage() {
        assertThat($("img[id='vehicle-img']").getAttribute("src")).isEqualTo("https://testautoqa.000webhostapp.com/plane.png");
        assertThat($(xpath("//option[text()='plane']")).is(attribute("selected"))).isTrue();
    }

    @Test()
    public void checkSwitchToCar() {
        $("select[id='picDD']").selectOption("car");
        assertThat($("img[id='vehicle-img']").getAttribute("src")).isEqualTo("https://testautoqa.000webhostapp.com/car.png");
    }

    @Test()
    public void checkSwitchToPlane() {
        $("select[id='picDD']").selectOption("plane");
        assertThat($("img[id='vehicle-img']").getAttribute("src")).isEqualTo("https://testautoqa.000webhostapp.com/plane.png");
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
