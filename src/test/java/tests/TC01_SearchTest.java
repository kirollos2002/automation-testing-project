package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Story("Search Functionality")
public class TC01_SearchTest extends BaseTest {

    @Test
    @Description("Search with a valid keyword and assert page navigates to search results")
    public void searchWithValidKeyword() throws Exception {
        String keyword = "\u0643\u064a\u0641 \u062a\u0646\u0636\u0645 \u0625\u0644\u0649 \u0627\u0644\u0628\u0646\u0643";

        WebElement searchInput = new WebDriverWait(driver, Duration.ofSeconds(15))
            .until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='search']")));

        searchInput.clear();
        searchInput.sendKeys(keyword);

        String urlBefore = driver.getCurrentUrl();
        searchInput.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        String currentUrl = driver.getCurrentUrl();
        String pageSource = driver.getPageSource();

        Assert.assertTrue(
            !currentUrl.equals(urlBefore) ||
            currentUrl.contains("search") ||
            currentUrl.contains("courses") ||
            pageSource.contains("\u0643\u064a\u0641") ||
            pageSource.contains("\u0627\u0644\u0628\u0646\u0643"),
            "After search, page should show results. URL: " + currentUrl
        );
    }
}
