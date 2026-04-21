package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CoursesPage extends BasePage {

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasCourseCards() {
        try {
            List<WebElement> courseCards = driver.findElements(By.cssSelector("div[class*='rounded-2xl']"));
            wait.until(d -> !driver.findElements(By.cssSelector("div[class*='rounded-2xl']")).isEmpty());
            return !courseCards.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstCourse() {
        WebElement firstLink = driver.findElements(By.cssSelector("a[href^='/ar/courses/']")).get(0);
        waitForVisible(firstLink);
        firstLink.click();
        waitForPageLoad();
    }

    public boolean firstCardHasImage() {
        try {
            WebElement image = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] img[class*='object-cover']")).get(0);
            waitForVisible(image);
            return image.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasTitle() {
        try {
            WebElement title = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] h3")).get(0);
            waitForVisible(title);
            return !title.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasInstructorName() {
        try {
            WebElement instructor = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] h6")).get(0);
            waitForVisible(instructor);
            return !instructor.getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasSubscribeButton() {
        try {
            WebElement subscribeButton = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] button[class*='bg-mainBlue']")).get(0);
            waitForVisible(subscribeButton);
            return subscribeButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void subscribeToFirstCourse() {
        WebElement subscribeButton = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] button[class*='bg-mainBlue']")).get(0);
        waitForClickable(subscribeButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", subscribeButton);
        waitForPageLoad();
    }

    public String getFirstCourseTitle() {
        try {
            WebElement title = driver.findElements(By.cssSelector("div[class*='rounded-2xl'] h3")).get(0);
            waitForVisible(title);
            return title.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }
}
