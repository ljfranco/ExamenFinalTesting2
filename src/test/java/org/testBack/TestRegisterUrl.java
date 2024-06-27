package org.testBack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.reports.ReportFactory;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRegisterUrl {
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/RegisterUrl-backTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");
    }

    @Test
    @Tag("BACK")
    @Order(1)
    public void testRegisterUrl() throws InterruptedException{
        ExtentTest test = extent.createTest("Test Register Url");
        System.out.println("Test Register Url");
        test.log(Status.INFO, "Test Register Url");
        try{
            RestAssured.baseURI = "https://parabank.parasoft.com/parabank/register.htm";
            Response response = RestAssured.get();
            int statusCode = response.getStatusCode();
            Assertions.assertEquals(200, statusCode, "Error: Status Code is not 200");
            test.log(Status.PASS, "Status Code: " + statusCode);
            System.out.println("Status Code: " + statusCode);
        } catch (AssertionError e) {
            System.out.println("Error: " + e.getMessage());
            test.log(Status.FAIL, "Error: " + e.getMessage());
            throw new InterruptedException();
        }

    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
}
