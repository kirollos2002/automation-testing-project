package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.ArrayList;

@Story("Footer Social Links")
public class TC09_LinkedInLinkTest extends BaseTest {

    @Test
    @Description("Click LinkedIn icon in footer and assert URL contains linkedin.com")
    public void verifyLinkedInLink() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLinkedInLink();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        String urlToCheck;
        if (tabs.size() > 1) {
            driver.switchTo().window(tabs.get(1));
            urlToCheck = driver.getCurrentUrl();
        } else {
            urlToCheck = driver.getCurrentUrl();
        }

        Assert.assertTrue(
            urlToCheck.contains("linkedin.com"),
            "URL should contain linkedin.com. Actual URL: " + urlToCheck
        );
    }
}
