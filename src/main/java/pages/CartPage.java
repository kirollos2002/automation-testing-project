package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cart-item, .my-course, .enrolled-course, .course-card")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart-item .course-title, .my-course .title, .enrolled-course h3")
    private List<WebElement> cartItemTitles;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url + "/ar/my-courses");
    }

    public boolean isCourseInCart(String courseTitle) {
        for (WebElement item : cartItemTitles) {
            if (item.getText().contains(courseTitle)) {
                return true;
            }
        }
        return false;
    }

    public boolean cartIsNotEmpty() {
        return !cartItems.isEmpty();
    }
}
