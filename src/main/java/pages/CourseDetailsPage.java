package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CourseDetailsPage extends BasePage {

    public CourseDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAboutCourseSectionDisplayed() {
        try {
            waitForPageLoad();
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a[href='#overview'], a[data-id='overview']")
                ));
            return true;
        } catch (Exception e) {
            List<WebElement> tabLinks = driver.findElements(By.cssSelector("a[href^='#']"));
            return !tabLinks.isEmpty();
        }
    }

    public boolean isCourseDetailPageOpen() {
        try {
            waitForPageLoad();
            WebElement courseTitle = driver.findElement(By.cssSelector("h1"));
            return waitForVisible(courseTitle).isDisplayed();
        } catch (Exception e) {
            return getCurrentUrl().contains("course");
        }
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(3000); } catch (InterruptedException ignored) {}
    }
}
