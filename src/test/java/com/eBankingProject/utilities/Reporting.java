package com.eBankingProject.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.eBankingProject.testCases.BaseClass;


//Listener class is used to generate the extent report
public class Reporting extends TestListenerAdapter{
	public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest logger;
    public static WebDriver driver; // will be set from BaseClass

    @Override
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timeStamp + ".html";

        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
        spark.config().setDocumentTitle("eBanking Project Test Report");
        spark.config().setReportName("Functional Test Report");
        spark.config().setEncoding("utf-8");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Host name", "LocalHost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester", "Kajal Sahu");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.PASS, "Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.FAIL, "❌ Test Failed: " + result.getName());
        logger.log(Status.FAIL, "Error Message: " + result.getThrowable());

        try {
            // Capture screenshot and get the path
            String screenshotPath = BaseClass.captureScreen(BaseClass.driver, result.getName());
            
            // Attach screenshot to the report properly
            logger.fail("Screenshot of failure:",
                    com.aventstack.extentreports.MediaEntityBuilder
                            .createScreenCaptureFromPath(screenshotPath)
                            .build());
            
            // Also log clickable link (for extra clarity)
            logger.info("📷 Screenshot saved at: " + screenshotPath);

        } catch (IOException e) {
            logger.warning("⚠️ Failed to capture screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        logger = extent.createTest(result.getName());
        logger.log(Status.SKIP, "Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush(); // generate report
    }

    
}

 