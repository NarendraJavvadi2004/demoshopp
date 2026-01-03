package com.demoshopp.pageutilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    // Return existing instance or create new one
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    // Create and configure ExtentReports instance
    private static ExtentReports createInstance() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // ✅ Step 1: Make sure reports folder exists
        String reportFolder = System.getProperty("user.dir") + "/reports";
        new File(reportFolder).mkdirs();  // creates folder if missing

        // ✅ Step 2: Save report inside that folder
        String reportPath = reportFolder + "/ExtentReport_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setDocumentTitle("DemoShop Automation Report");
        spark.config().setReportName("Order-Flow Report");
        spark.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // Add system/environment info
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("OS Version", System.getProperty("os.version"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Url", ConfigReader.getProperty("url"));

        return extent;
    }
}