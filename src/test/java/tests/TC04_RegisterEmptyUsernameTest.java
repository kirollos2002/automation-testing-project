package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegisterPage;

@Story("Registration Validation")
public class TC04_RegisterEmptyUsernameTest extends BaseTest {

    @Test
    @Description("Register with empty name field and assert validation error is displayed")
    public void registerWithEmptyUsername() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open(baseUrl);

        registerPage.fillFormWithoutFullName(
            "testuser@test.com",
            "TestPassword123!"
        );

        registerPage.clickCreate();

        Assert.assertTrue(
            registerPage.isValidationErrorDisplayed(),
            "Validation error should be displayed when name field is empty"
        );
    }
}
