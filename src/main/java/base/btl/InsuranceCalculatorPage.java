package base.btl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class InsuranceCalculatorPage extends BtlBasePage {

    public InsuranceCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void calculateYeshivaStudent(String birthDate) {
        driver.findElement(By.linkText("מחשבון לחישוב דמי הביטוח")).click();

        String pageTitle = driver.findElement(By.tagName("h1")).getText();
        if (!pageTitle.contains("חישוב דמי ביטוח עבור עצמאי, תלמיד, שוהה בחוץ לארץ ומי שלא עובד")) {
            System.out.println("שגיאה: לא הגענו לדף המחשבון הנכון!");
        }

        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_employeType_1_lbl")).click();
        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_Gender_0_lbl")).click();
        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_DynDatePicker_BirthDate_Date")).sendKeys(birthDate);
        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StartNavigationTemplateContainerID_StartNextButton")).click();

        // תיקון סוגריים 1: מחכים לטקסט "צעד שני"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("header"), "צעד שני"));

        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_rdb_GetNechut_1_lbl")).click();
        driver.findElement(By.id("ctl00_ctl43_g_642b1586_5c41_436a_a04c_e3b5ba94ba69_ctl00_InsuranceNotSachirWizard_StepNavigationTemplateContainerID_StepNextButton")).click();

        // תיקון סוגריים 2: מחכים לטקסט "סיום"
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("header"), "סיום"));

        List<WebElement> results = driver.findElements(By.cssSelector("ul.CalcResult li strong"));
        boolean btlAmount = results.get(0).getText().trim().contains("43");
        boolean healthAmount = results.get(1).getText().trim().contains("120");
        boolean totalAmount = results.get(2).getText().trim().contains("163");

        if(!btlAmount || !healthAmount || !totalAmount)
            System.out.println("החישוב לא נכון!!");
    }

    public void sumBTL(String date) {
        driver.findElement(By.linkText("למחשבוני דמי אבטלה")).click();
        driver.findElement(By.linkText("חישוב דמי אבטלה")).click();

        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_DynDatePicker_PiturimDate_Date")).sendKeys(date);
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_rdb_age_1_lbl")).click();
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StartNavigationTemplateContainerID_StartNextButton")).click();

        // תיקון סוגריים 3: מחכים ששדה השכר יהיה לחיץ (זה אומר שהדף נטען)
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl07_Txt_Sallary")));

        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl07_Txt_Sallary")).sendKeys("30000");
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl06_Txt_Sallary")).sendKeys("40000");
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl05_Txt_Sallary")).sendKeys("33000");
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl04_Txt_Sallary")).sendKeys("35000");
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl03_Txt_Sallary")).sendKeys("33598");
        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_IncomeGrid_ctl02_Txt_Sallary")).sendKeys("39456");

        driver.findElement(By.id("ctl00_ctl43_g_2ccdbe03_122a_4c30_928f_60300c0df306_ctl00_AvtalaWizard_StepNavigationTemplateContainerID_StepNextButton")).click();

        // תיקון סוגריים 4: מחכים לטקסט "סיום" בכותרת
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("header"), "סיום"));

        List<WebElement> results = driver.findElements(By.cssSelector("div.CalcResult.ResultText ul li"));
        if (results.size() > 0) {
            boolean btlAmount = results.get(0).getText().contains("שכר יומי ממוצע");
            boolean healthAmount = results.get(1).getText().contains("דמי אבטלה ליום");
            boolean totalAmount = results.get(2).getText().contains("דמי אבטלה לחודש");

            if(!btlAmount || !healthAmount || !totalAmount) {
                System.out.println("חישוב האבטלה לא נכון!!");
            }
        }
    }
}