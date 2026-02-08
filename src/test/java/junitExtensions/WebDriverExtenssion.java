package junitExtensions;

import driver.DriverFactory;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Field;

public class WebDriverExtenssion implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        // אתחול הדרייבר דרך ה-Factory כפי שמופיע במצגת
        DriverFactory.initDriver();
        WebDriver driver = DriverFactory.getDriver();

        context.getStore(ExtensionContext.Namespace.GLOBAL).put("driver", driver);
        driver.manage().window().maximize();
        driver.get("https://www.btl.gov.il/Pages/default.aspx");

        // הזרקת הדרייבר למחלקת הטסט (btlBaseTest)
        Object testInstance = context.getRequiredTestInstance();
        Field driverField = testInstance.getClass().getSuperclass().getDeclaredField("driver");
        driverField.setAccessible(true);
        driverField.set(testInstance, driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        DriverFactory.quitDriver();
    }
}