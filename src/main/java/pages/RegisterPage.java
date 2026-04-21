package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage extends BasePage {

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url + "/ar/auth/register");
        waitForPageLoad();
    }

    public void fillFormWithoutFullName(String email, String password) {
        WebElement emailField = driver.findElement(By.id("email"));
        waitForVisible(emailField);
        type(emailField, email);
        try {
            WebElement phoneField = driver.findElement(By.id("phone"));
            type(phoneField, "01012345678");
        } catch (Exception ignored) {}
        WebElement passwordField = driver.findElement(By.id("password"));
        type(passwordField, password);
        try {
            WebElement confirmPasswordField = driver.findElement(By.id("confirm_password"));
            type(confirmPasswordField, password);
        } catch (Exception ignored) {}
    }

    public void clickCreate() {
        WebElement createButton = driver.findElement(By.cssSelector("button[type='submit']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", waitForVisible(createButton));
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        js.executeScript("arguments[0].click();", createButton);
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isValidationErrorDisplayed() {
        try {
            WebElement validationError = driver.findElement(By.cssSelector("[class*='text-red'], [class*='text-destructive'], p[class*='text-sm'], [role='alert']"));
            return waitForVisible(validationError).isDisplayed();
        } catch (Exception e) {
            return driver.getPageSource().contains("text-red") ||
                   driver.getPageSource().contains("required") ||
                   driver.getPageSource().contains("\u0645\u0637\u0644\u0648\u0628");
        }
    }

    public String getValidationErrorText() {
        try {
            WebElement validationError = driver.findElement(By.cssSelector("[class*='text-red'], [class*='text-destructive'], p[class*='text-sm'], [role='alert']"));
            return waitForVisible(validationError).getText();
        } catch (Exception e) {
            return "";
        }
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }
}
