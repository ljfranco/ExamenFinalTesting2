package org.testBack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.reports.ReportFactory;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestAccountActivity extends BackBaseTest{
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/AccountActivity-backTest.html");
    static ExtentReports extent;

    List<Integer> accountIds;

    @BeforeAll
    public static void setUpClass() throws InterruptedException {
        // Configuracion para el reporte de la prueba
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");

        // crear un usuario de prueba desde el primer registro del Json, username: DawnNunez, password: 123456
        BackBaseTest baseTest = new BackBaseTest();
        baseTest.registerUser();
    }

    @BeforeEach
    public void dataSetup() {
        accountIds = this.getAccountIds(this.getCustomerId());

    }

    @Test
    @Tag("BACK")
    @Order(4)
    public void testAccountActivity() {
        ExtentTest test = extent.createTest("Test Account Activity");
        System.out.println("Test Account Activity");
        test.log(Status.INFO, "Test Account Activity");

        Integer accountId = accountIds.getFirst();

        given()
                .pathParam("accountId", accountId)
                .when().get("https://parabank.parasoft.com/parabank/services/bank/accounts/{accountId}/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();


        test.log(Status.PASS, "Successful Account Activity Test");
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
}
