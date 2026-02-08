package junitExtensions;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.ScreenshotUtils;

public class ExtentReportExtension implements BeforeAllCallback, AfterAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        // מאתחל את הדו"ח פעם אחת לכל הפרויקט
        ExtentReportManager.getExtentReports();
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        // יוצר רישום לטסט הספציפי שעומד לרוץ
        ExtentTestManager.startTest(context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        // שליפת הדרייבר מהמחסן (Store) של JUnit
        WebDriver driver = context.getStore(ExtensionContext.Namespace.GLOBAL).get("driver", WebDriver.class);

        // בדיקה האם הטסט נכשל
        if (context.getExecutionException().isPresent()) {
            // צילום מסך במקרה של כישלון
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, context.getDisplayName());

            ExtentTestManager.getTest().log(Status.FAIL, "הטסט נכשל: " + context.getExecutionException().get().getMessage(),
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        } else {
            // דיווח הצלחה
            ExtentTestManager.getTest().log(Status.PASS, "הטסט עבר בהצלחה");
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        // שמירת הדו"ח וסגירתו בסוף כל ההרצות
        ExtentReportManager.getExtentReports().flush();
    }
}