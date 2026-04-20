package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.HomePage;

@Story("Course Card UI")
public class TC11_CourseCardUITest extends BaseTest {

    @Test
    @Description("Verify the first course card contains image, title, instructor name, and subscribe button")
    public void verifyCourseCardUI() {
        HomePage homePage = new HomePage(driver);
        homePage.clickAllCourses();

        CoursesPage coursesPage = new CoursesPage(driver);

        Assert.assertTrue(
            coursesPage.hasCourseCards(),
            "Courses page should have at least one course card"
        );

        Assert.assertTrue(
            coursesPage.firstCardHasImage(),
            "First course card should have an image"
        );

        Assert.assertTrue(
            coursesPage.firstCardHasTitle(),
            "First course card should have a title"
        );

        Assert.assertTrue(
            coursesPage.firstCardHasInstructorName(),
            "First course card should have an instructor name"
        );

        Assert.assertTrue(
            coursesPage.firstCardHasSubscribeButton(),
            "First course card should have a subscribe button"
        );
    }
}
