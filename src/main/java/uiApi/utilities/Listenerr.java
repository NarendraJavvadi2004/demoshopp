package uiApi.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import ui.base.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Listenerr implements ITestListener {

    private static final Logger logger = LogManager.getLogger(Listenerr.class);

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test Started: {}", result.getMethod().getMethodName());
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test Passed: {}", result.getMethod().getMethodName());
        ExtentTestManager.getTest().pass("* Test Passed *");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test Failed: {}", result.getMethod().getMethodName(), result.getThrowable());
        ExtentTestManager.getTest().fail(result.getThrowable());

        String screenshotPath = Screenshots.captureScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName()
        );

        if (screenshotPath != null) {
            logger.info("Screenshot captured at: {}", screenshotPath);
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } else {
            logger.warn("No screenshot captured because driver was null.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test Skipped: {}", result.getMethod().getMethodName());
        ExtentTestManager.getTest().skip("* Test Skipped *");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Flushing Extent Report for suite: {}", context.getName());
        ExtentManager.getInstance().flush();
    }
}