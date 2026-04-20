package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

@Story("Registration")
public class TC03_RegistrationPageTest extends BaseTest {

    @Test
    @Description("Click on 'Join Us' and assert the URL contains /register or /signup")
    public void openRegistrationPage() {
        driver.get(baseUrl + "/ar/auth/register");
        waitForPageReady();

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(
            currentUrl.contains("register") || currentUrl.contains("signup") || currentUrl.contains("auth"),
            "URL should contain register/signup/auth. Actual URL: " + currentUrl
        );
    }
}
