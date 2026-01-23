package uiApi.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ExtentReports extent = ExtentManager.getInstance();

    // Get current test
    public static ExtentTest getTest() {
        return test.get();
    }

    // Start a new test with just a name
    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

}