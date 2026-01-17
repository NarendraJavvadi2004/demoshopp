package com.demoshopp.pageutilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.demoshopp.base.DriverManager;

public class Listenerr implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("test Started...");
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("test Successfull...");
        ExtentTestManager.getTest().pass("* Test Passed *");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("test Failed...");
        ExtentTestManager.getTest().fail(result.getThrowable());

        String screenshotPath = Screenshots.captureScreenshot(
                DriverManager.getDriver(),
                result.getMethod().getMethodName()
        );

        if (screenshotPath != null) {
            ExtentTestManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } else {
            System.out.println("No screenshot captured because driver was null.");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("test Skipped...");
        ExtentTestManager.getTest().skip("* Test Skipped *");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Flushing Extent Report...");
        ExtentManager.getInstance().flush();
    }
}