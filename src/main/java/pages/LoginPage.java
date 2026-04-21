package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url + "/ar/auth/login");
        waitForPageLoad();
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(By.id("email"));
        type(emailField, email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("password"));
        type(passwordField, password);
    }

    public void clickLogin() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", waitForClickable(loginButton));
    }

    public void loginWith(String email, String password) {
        WebElement emailField = driver.findElement(By.id("email"));
        waitForVisible(emailField);
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public void clickLoginWithEmptyFields() {
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", waitForClickable(loginButton));
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isErrorDisplayed() {
        try {
            Thread.sleep(2000);
            WebElement errorMessage = driver.findElement(By.cssSelector("[class*='text-red'], [class*='text-destructive'], [class*='error'], [role='alert'], .text-red-500"));
            return waitForVisible(errorMessage).isDisplayed();
        } catch (Exception e) {
            return driver.getPageSource().contains("text-red") || driver.getPageSource().contains("invalid");
        }
    }

    public boolean isLoggedIn() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("login")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }
}
