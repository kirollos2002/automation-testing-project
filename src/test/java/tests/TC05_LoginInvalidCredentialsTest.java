package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Story("Login Functionality")
public class TC05_LoginInvalidCredentialsTest extends BaseTest {

    @Test
    @Description("Login with invalid credentials and assert error message is displayed")
    public void loginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.loginWith(
            config.getProperty("invalid.email"),
            config.getProperty("invalid.password")
        );

        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Error message should be displayed after login with invalid credentials"
        );
    }
}
