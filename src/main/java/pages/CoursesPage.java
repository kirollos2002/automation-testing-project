package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CoursesPage extends BasePage {

    @FindBy(css = "div[class*='rounded-2xl']")
    private List<WebElement> courseCards;

    @FindBy(css = "div[class*='rounded-2xl'] img[class*='object-cover']")
    private List<WebElement> courseImages;

    @FindBy(css = "div[class*='rounded-2xl'] h3")
    private List<WebElement> courseTitles;

    @FindBy(css = "div[class*='rounded-2xl'] h6")
    private List<WebElement> instructorNames;

    @FindBy(css = "div[class*='rounded-2xl'] button[class*='bg-mainBlue']")
    private List<WebElement> subscribeButtons;

    @FindBy(css = "a[href^='/ar/courses/']")
    private List<WebElement> courseCardLinks;

    public CoursesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean hasCourseCards() {
        try {
            wait.until(d -> !courseCards.isEmpty());
            return !courseCards.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstCourse() {
        waitForVisible(courseCardLinks.get(0));
        courseCardLinks.get(0).click();
        waitForPageLoad();
    }

    public boolean firstCardHasImage() {
        try {
            waitForVisible(courseImages.get(0));
            return courseImages.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasTitle() {
        try {
            waitForVisible(courseTitles.get(0));
            return !courseTitles.get(0).getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasInstructorName() {
        try {
            waitForVisible(instructorNames.get(0));
            return !instructorNames.get(0).getText().trim().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean firstCardHasSubscribeButton() {
        try {
            waitForVisible(subscribeButtons.get(0));
            return subscribeButtons.get(0).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void subscribeToFirstCourse() {
        waitForClickable(subscribeButtons.get(0));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", subscribeButtons.get(0));
        waitForPageLoad();
    }

    public String getFirstCourseTitle() {
        try {
            waitForVisible(courseTitles.get(0));
            return courseTitles.get(0).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    private void waitForPageLoad() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
        try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
    }
}
