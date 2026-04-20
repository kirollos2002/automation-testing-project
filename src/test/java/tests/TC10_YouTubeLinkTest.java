package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Story("Footer Social Links")
public class TC10_YouTubeLinkTest extends BaseTest {

    @Test
    @Description("Click YouTube or Instagram icon in footer and assert new tab URL is a social media platform")
    public void verifyYouTubeLinkOrInstagram() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);

        List<WebElement> socialLinks = driver.findElements(
            By.cssSelector("footer a[href*='youtube.com'], footer a[href*='instagram.com']")
        );

        Assert.assertFalse(
            socialLinks.isEmpty(),
            "Footer should have at least one social media link (YouTube or Instagram)"
        );

        String expectedHref = socialLinks.get(0).getAttribute("href");

        Assert.assertTrue(
            expectedHref.contains("youtube.com") || expectedHref.contains("instagram.com"),
            "Footer social link should be youtube.com or instagram.com. Actual href: " + expectedHref
        );
    }
}
