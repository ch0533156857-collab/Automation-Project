import base.btl.HomePage;
import base.btl.InsuranceCalculatorPage;
import enums.menu;
import junitExtensions.ExtentReportExtension;
import junitExtensions.WebDriverExtenssion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Assertions;

@ExtendWith({WebDriverExtenssion.class, ExtentReportExtension.class})public class btlTest extends btlBaseTest {
    @Test
    public void testYeshivaStudentCalculator() {
        HomePage hp = new HomePage(driver);

        hp.navigate(menu.BT, "דמי ביטוח לאומי");

        InsuranceCalculatorPage calculator = new InsuranceCalculatorPage(driver);
        calculator.calculateYeshivaStudent("01/01/2000");
        Assertions.assertTrue(driver.getTitle().contains("ביטוח לאומי"), "הדף לא נטען כראוי");
    }
    @Test
    public void testSumBTL()
    {

        HomePage hp = new HomePage(driver);
        hp.navigate(menu.SALES, "אבטלה");
        InsuranceCalculatorPage calculator = new InsuranceCalculatorPage(driver);
        calculator.sumBTL("01/01/2026");

        String pageTitle = driver.getTitle();

        Assertions.assertTrue(pageTitle.contains("אבטלה"),
                "שגיאה: הכותרת לא מכילה את המילה אבטלה, כנראה לא בדף הנכון");
    }

}
