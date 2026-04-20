package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class HomePage extends BasePage {

    @FindBy(css = "input[type='search']")
    private WebElement searchInput;

    @FindBy(css = "a[href='/ar/courses'].font-light, a[href='/ar/courses']")
    private WebElement allCoursesLink;

    @FindBy(css = "a[href='/ar/auth/register']")
    private WebElement joinUsLink;

    @FindBy(css = "a[href='/ar/auth/login']")
    private WebElement loginLink;

    @FindBy(css = "a[href*='facebook.com']")
    private WebElement facebookLink;

    @FindBy(css = "a[href*='linkedin.com']")
    private WebElement linkedInLink;

    @FindBy(css = "a[href*='youtube.com']")
    private WebElement youTubeLink;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url + "/ar");
        waitForPageLoad();
    }

    public void searchFor(String keyword) {
        waitForVisible(searchInput).clear();
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
        waitForPageLoad();
    }

    public void clickAllCourses() {
        driver.get(driver.getCurrentUrl().split("/ar")[0] + "/ar/courses");
        waitForPageLoad();
    }

    public void clickJoinUs() {
        waitForClickable(joinUsLink).click();
        waitForPageLoad();
    }

    public void clickLogin() {
        waitForClickable(loginLink).click();
        waitForPageLoad();
    }

    public void clickFacebookLink() {
        scrollToFooter();
        jsClick(facebookLink);
    }

    public void clickLinkedInLink() {
        scrollToFooter();
        jsClick(linkedInLink);
    }

    public void clickYouTubeLink() {
        scrollToFooter();
        jsClick(youTubeLink);
    }

    private void scrollToFooter() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    private void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", waitForVisible(element));
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }
}
