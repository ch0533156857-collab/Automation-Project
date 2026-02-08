package enums;

public enum menu {
    RIGHTS("ctl00_Topmneu_BenefitsHyperLink"),
    SALES("ctl00_Topmneu_HyperLink3"),
    BT("ctl00_Topmneu_InsuranceHyperLink"),
    CONNECT("ctl00_Topmneu_ContactsHyperLink");

    String mainMenuItem;

    menu(String mainMenuItem) {
        this.mainMenuItem = mainMenuItem;
    }

    public String getMainMenuItem() {
        return mainMenuItem;
    }
}
