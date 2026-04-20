package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Story("Login Validation")
public class TC06_LoginEmptyFieldsTest extends BaseTest {

    @Test
    @Description("Login with empty fields and assert validation messages appear")
    public void loginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);

        loginPage.clickLoginWithEmptyFields();

        Assert.assertTrue(
            loginPage.isErrorDisplayed(),
            "Validation error messages should appear when login form is submitted empty"
        );
    }
}
