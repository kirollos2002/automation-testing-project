package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url + "/ar");
        waitForPageLoad();
    }

    public void searchFor(String keyword) {
        WebElement searchInput = driver.findElement(By.cssSelector("input[type='search']"));
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
        WebElement joinUsLink = driver.findElement(By.cssSelector("a[href='/ar/auth/register']"));
        waitForClickable(joinUsLink).click();
        waitForPageLoad();
    }

    public void clickLogin() {
        WebElement loginLink = driver.findElement(By.cssSelector("a[href='/ar/auth/login']"));
        waitForClickable(loginLink).click();
        waitForPageLoad();
    }

    public void clickFacebookLink() {
        scrollToFooter();
        WebElement facebookLink = driver.findElement(By.cssSelector("a[href*='facebook.com']"));
        jsClick(facebookLink);
    }

    public void clickLinkedInLink() {
        scrollToFooter();
        WebElement linkedInLink = driver.findElement(By.cssSelector("a[href*='linkedin.com']"));
        jsClick(linkedInLink);
    }

    public void clickYouTubeLink() {
        scrollToFooter();
        WebElement youTubeLink = driver.findElement(By.cssSelector("a[href*='youtube.com']"));
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
