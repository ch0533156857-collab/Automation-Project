package base.btl;

import enums.menu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BtlBasePage extends basePage{
    public BtlBasePage(WebDriver driver) {
        super(driver);
    }
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public void navigate(menu mainMenuItem,String subMenu) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id(mainMenuItem.getMainMenuItem()))
        );
        element.click();
        driver.findElement(By.linkText(subMenu)).click();
    }

    public void search(String strSearch)
    {
        driver.findElement(By.id("TopQuestions")).sendKeys(strSearch);
        driver.findElement(By.id("ctl00_SiteHeader_reserve_btnSearch")).click();
    }

    public basePage clickBranches() {
        driver.findElement(By.id("ctl00_Topmneu_BranchesHyperLink")).click();
        return new basePage(driver);
    }
}
