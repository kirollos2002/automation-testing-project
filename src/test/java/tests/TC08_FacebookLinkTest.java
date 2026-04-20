package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.ArrayList;

@Story("Footer Social Links")
public class TC08_FacebookLinkTest extends BaseTest {

    @Test
    @Description("Click Facebook icon in footer and assert URL contains facebook.com")
    public void verifyFacebookLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickFacebookLink();

        String originalWindow = driver.getWindowHandle();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        String urlToCheck;
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            urlToCheck = driver.getCurrentUrl();
        } else {
            urlToCheck = driver.getCurrentUrl();
        }

        Assert.assertTrue(
            urlToCheck.contains("facebook.com"),
            "URL should contain facebook.com. Actual URL: " + urlToCheck
        );
    }
}
