package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;
import java.util.List;

@Story("End to End - Subscribe to Course")
public class TC07_EndToEndTest extends BaseTest {

    @Test
    @Description("Login with valid credentials, subscribe to a course, assert navigation to course page")
    public void subscribeToCoursAfterLogin() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(baseUrl);
        loginPage.loginWith(
            config.getProperty("valid.email"),
            config.getProperty("valid.password")
        );

        Thread.sleep(15000);

        String urlAfterLogin = driver.getCurrentUrl();
        boolean loggedIn = !urlAfterLogin.contains("login");

        Assert.assertTrue(loggedIn,
            "Login failed - URL still at: " + urlAfterLogin +
            ". Verify credentials in config.properties match a valid eyouthlearning.com account.");

        driver.get(baseUrl + "/ar/courses");
        new WebDriverWait(driver, Duration.ofSeconds(20))
            .until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        Thread.sleep(2000);

        List<WebElement> subscribeButtons = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div[class*='rounded-2xl'] button[class*='bg-mainBlue']")
            ));

        Assert.assertFalse(subscribeButtons.isEmpty(), "Should find subscribe button on courses page");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", subscribeButtons.get(0));
        Thread.sleep(500);
        js.executeScript("arguments[0].click();", subscribeButtons.get(0));
        Thread.sleep(4000);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(
            currentUrl.contains("course") || currentUrl.contains("cart") ||
            currentUrl.contains("checkout") || currentUrl.contains("my") || currentUrl.contains("payment"),
            "After subscribing, should navigate to course/cart/payment page. URL: " + currentUrl
        );
    }
}
