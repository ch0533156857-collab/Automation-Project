import base.btl.HomePage;
import enums.menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BtlBreadcrumbsTest extends btlBaseTest {


    @ParameterizedTest
    @CsvSource({
            "אבטלה, אבטלה",
            "אזרח ותיק, אזרח ותיק",
            "קצבת ילדים, ילדים",
            "הבטחת הכנסה, הבטחת הכנסה",
            "נכות כללית, נכות כללית"
    })
    public void testBreadcrumbs(String menuLink, String expectedKeyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        HomePage hp = new HomePage(driver);

        hp.navigate(menu.RIGHTS, menuLink);

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class*='breadcrumb'], #breadcrumb"))
        );
        String actualBreadcrumb = element.getText().replace("\n", " ").trim();

        System.out.println("--- Testing Page: " + menuLink + " ---");
        System.out.println("Actual Breadcrumb: " + actualBreadcrumb);

        Assertions.assertTrue(actualBreadcrumb.contains(expectedKeyword),
                "שגיאה: בדף '" + menuLink + "' ה-Breadcrumb היה '" + actualBreadcrumb + "' ולא הכיל '" + expectedKeyword + "'");
    }
}