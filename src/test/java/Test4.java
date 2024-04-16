import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertEquals;

public class Test4 extends BaseTest{

    @Test
    public void alertTest (){
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        String[] message = {""};
        page.onceDialog(alert -> {
            message[0] = alert.message();
            alert.accept("Test message");
        });
        page.getByText("Click for JS Prompt").click();

        assertEquals(message[0], "I am a JS prompt");

        String resultText = page.textContent("id=result");
        assertEquals(resultText, "You entered: Test message");

        page.close();

    }

    @Test
    public void scrollTest(){
        Page page = browser.newPage();
        page.navigate("https://playwright.dev/java/");

        BoundingBox element = page.locator("(//div[@class='col col--6'])[4]").boundingBox();
        double x = element.x + (element.width / 2);
        double y = element.y + (element.height / 2);
        page.mouse().wheel(x, y);
        assertThat(page.getByText("Resilient • No flaky tests")).isVisible();

        page.close();

    }
}
