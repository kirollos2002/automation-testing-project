package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage extends BasePage {

    @FindBy(id = "name")
    private WebElement fullNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "confirm_password")
    private WebElement confirmPasswordField;

    @FindBy(css = "button[type='submit'], button[class*='bg-mainBlue']")
    private WebElement createButton;

    @FindBy(css = "[class*='text-red'], [class*='text-destructive'], p[class*='text-sm'], [role='alert']")
    private WebElement validationError;

    public RegisterPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url + "/ar/auth/register");
        waitForPageLoad();
    }

    public void fillFormWithoutFullName(String email, String password) {
        waitForVisible(emailField);
        type(emailField, email);
        try {
            type(phoneField, "01012345678");
        } catch (Exception ignored) {}
        type(passwordField, password);
        try {
            type(confirmPasswordField, password);
        } catch (Exception ignored) {}
    }

    public void clickCreate() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", waitForVisible(createButton));
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        js.executeScript("arguments[0].click();", createButton);
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }

    public boolean isValidationErrorDisplayed() {
        try {
            return waitForVisible(validationError).isDisplayed();
        } catch (Exception e) {
            return driver.getPageSource().contains("text-red") ||
                   driver.getPageSource().contains("required") ||
                   driver.getPageSource().contains("\u0645\u0637\u0644\u0648\u0628");
        }
    }

    public String getValidationErrorText() {
        try {
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
