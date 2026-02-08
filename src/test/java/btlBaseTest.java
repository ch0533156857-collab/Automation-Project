import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class btlBaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        // כאן אנחנו יוצרים את הדרייבר - זה חייב להיות כאן!
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // עכשיו כשהדרייבר קיים, אפשר ליצור את ה-wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.btl.gov.il/");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}