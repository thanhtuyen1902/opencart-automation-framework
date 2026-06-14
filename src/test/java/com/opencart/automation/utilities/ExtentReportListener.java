package com.opencart.automation.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencart.automation.core.BaseTest;
import org.testng.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentReportListener implements ITestListener {
    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest test;
    // Use ThreadLocal to ensure memory parallel
    private ThreadLocal<ExtentTest> testLogger = new ThreadLocal<>();
    private String reportName;
    private static boolean systemInfoAdded = false;
//    private long suiteStartTime;
    //Activate when start running suite
    public void onStart(ITestContext context) {
//        suiteStartTime = System.currentTimeMillis();
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "TestReport_" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
//        reportName = "TestReport.html";
//        sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
        //Title of the report
        sparkReporter.config().setDocumentTitle("OpenCart Automation Test Report");
        //Name of the report
        sparkReporter.config().setReportName("OpenCart Functional & E2E Testing Results");
        sparkReporter.config().setTheme(Theme.STANDARD);

        //Common for all tests
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // Environment info
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Execution Mode", ConfigReader.getExecutionMode());
//        extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
        extent.setSystemInfo("Tester", "TuyenMT");

    }
    //Activate when start running @Test
    public void onTestStart(ITestResult result) {
//        test = extent.createTest(result.getMethod().getMethodName());
        String testName = "";
        String testDesc = result.getMethod().getDescription();

        if (testDesc != null && !testDesc.isEmpty()) {
            testName = testDesc;
        }else {
            testName = result.getMethod().getMethodName();
        }
        //dùng DDT
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            String targetData1 = String.valueOf(parameters[0]);
            String targetData2 = String.valueOf(parameters[1]);
            testName = targetData1 + " - " + testName + " (" + targetData2 + ")";
        }
        test = extent.createTest(testName);


        String[] groups = result.getMethod().getGroups();
        for (String group : groups) {
            test.assignCategory(group);
        }

        testLogger.set(test);
    }
    public void onTestSuccess(ITestResult result) {
        double duration = (result.getEndMillis() - result.getStartMillis())/1000.0;
        testLogger.get().log(Status.PASS, "Test case PASSED: " + result.getName());
        testLogger.get().log(Status.INFO, "Execution Time: " + duration + " seconds");
    }
    public void onTestFailure(ITestResult result) {
        double duration = (result.getEndMillis() - result.getStartMillis())/1000.0;
        testLogger.get().log(Status.FAIL, "Test case FAILED: " + result.getName());
        testLogger.get().log(Status.INFO, "Execution Time: " + duration + " seconds");
        testLogger.get().log(Status.INFO, "Cause of Failure: " + result.getThrowable());

        // Screenshots
        String screenshotPath = BaseTest.captureScreenshot(result.getName());
        if (screenshotPath != null) {
            try {
                File screenshotFile = new File(screenshotPath);
                String screenshotName = screenshotFile.getName();
                String relativePath = "../screenshots/" + screenshotName;
                testLogger.get().addScreenCaptureFromPath(relativePath);
            }catch(Exception e) {
                testLogger.get().log(Status.WARNING, "Can't attach screenshot on report.");
            }
        }


    }
    public void onTestSkipped(ITestResult result) {
        testLogger.get().log(Status.SKIP, "Test case SKIPPED: " + result.getName());
        testLogger.get().log(Status.SKIP, "Cause of Skipping: " + result.getThrowable());
    }
    //Activate when finish suite
    //ITestContext context
    public void onFinish(ITestContext context) {
//        long suiteEndTime = System.currentTimeMillis();
        long suiteStartTime = context.getStartDate().getTime();
        long suiteEndTime = context.getEndDate().getTime();
        double totalSuiteDuration = (suiteEndTime - suiteStartTime)/1000.0;
        if (!systemInfoAdded) {
            extent.setSystemInfo("Total Tests", String.valueOf(context.getAllTestMethods().length));
//            extent.setSystemInfo("Passed Tests", String.valueOf(context.getPassedTests().size()));
//            extent.setSystemInfo("Failed Tests", String.valueOf(context.getFailedTests().size()));
//            extent.setSystemInfo("Skipped Tests", String.valueOf(context.getSkippedTests().size()));
            extent.setSystemInfo("Total Suite Duration ", totalSuiteDuration + " seconds");
            systemInfoAdded = true;
        }

        if (extent !=null) {
            //Đẩy toàn bộ dữ liệu từ bộ nhớ đệm ra file HTML
            extent.flush();
        }
        //Options: auto open report on browser


    }

}
