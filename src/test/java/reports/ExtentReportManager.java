package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            // הגדרת הנתיב שבו יישמר הדו"ח
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport/ExtentReport.html");

            // --- הוספת הגדרות עיצוב (מומלץ מאוד) ---
            spark.config().setReportName("דו''ח הרצת בדיקות אוטומטיות"); // שם הדו"ח שיופיע למעלה
            spark.config().setDocumentTitle("BTL Automation Results"); // כותרת הלשונית בדפדפן
            spark.config().setTheme(Theme.STANDARD); // ניתן לשנות ל-DARK אם מעדיפים רקע שחור
            spark.config().setEncoding("utf-8"); // חשוב מאוד כדי שתהיה תמיכה בעברית בדו"ח

            extent = new ExtentReports();
            extent.attachReporter(spark);

            // ניתן להוסיף מידע כללי שיופיע בדף הראשי של הדו"ח
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Student Name");
        }
        return extent;
    }
}