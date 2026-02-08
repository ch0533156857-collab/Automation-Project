package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extent = ExtentReportManager.getExtentReports();

    public static ExtentTest getTest() { return extentTest.get(); }

    public static void startTest(String testName) {
        extentTest.set(extent.createTest(testName));
    }
}