package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CoursesPage;
import pages.CourseDetailsPage;
import pages.HomePage;

@Story("Course Details")
public class TC02_CourseDetailsTest extends BaseTest {

    @Test
    @Description("Open a course details page and assert the 'About Course' section is displayed")
    public void openCourseDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.clickAllCourses();

        CoursesPage coursesPage = new CoursesPage(driver);
        coursesPage.clickFirstCourse();

        CourseDetailsPage courseDetailsPage = new CourseDetailsPage(driver);

        Assert.assertTrue(
            courseDetailsPage.isCourseDetailPageOpen(),
            "Course details page should be open"
        );

        Assert.assertTrue(
            courseDetailsPage.isAboutCourseSectionDisplayed(),
            "Section '\u062d\u0648\u0644 \u0627\u0644\u062f\u0648\u0631\u0629 \u0627\u0644\u062a\u062f\u0631\u064a\u0628\u064a\u0629' should be displayed"
        );
    }
}
