package base.btl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BtlBasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean useSearch(){
        search("חישוב סכום דמי לידה ליום");
        if(driver.getTitle().equals("תוצאות חיפוש עבור חישוב סכום דמי לידה ליום")){
            return true;
        }
        return false;
    }

    public boolean useClickBranches() {
        basePage branchesPage = clickBranches();

        if (branchesPage.driver.getTitle().equals("סניפים וערוצי שירות")) {
            List<WebElement> lst = driver.findElements(By.className("SnifName"));

            if (!lst.isEmpty()) {
                lst.get(0).click();

                try { Thread.sleep(3000); } catch (Exception e) {}

                boolean isAddressVisible = driver.findElements(By.xpath("//label[contains(text(),'כתובת')]")).size() > 0;
                boolean isReceptionVisible = driver.findElements(By.xpath("//label[contains(text(),'קבלת קהל')]")).size() > 0;
                boolean isPhoneVisible = driver.findElements(By.xpath("//label[contains(text(),'מענה טלפוני')]")).size() > 0;

                System.out.println("Address: " + isAddressVisible + ", Reception: " + isReceptionVisible + ", Phone: " + isPhoneVisible);

                return isAddressVisible && isReceptionVisible && isPhoneVisible;
            }
        }
        return false;
    }


}
