package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url + "/ar/my-courses");
    }

    public boolean isCourseInCart(String courseTitle) {
        List<WebElement> cartItemTitles = driver.findElements(By.cssSelector(".cart-item .course-title, .my-course .title, .enrolled-course h3"));
        for (WebElement item : cartItemTitles) {
            if (item.getText().contains(courseTitle)) {
                return true;
            }
        }
        return false;
    }

    public boolean cartIsNotEmpty() {
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item, .my-course, .enrolled-course, .course-card"));
        return !cartItems.isEmpty();
    }
}
