package org.testBack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.reports.ReportFactory;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class TestTransferFunds extends BackBaseTest{
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/TransferFunds-backTest.html");
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
        this.createAccount(this.getCustomerId(), 1, this.getAccountIds(this.getCustomerId()).getFirst());
        accountIds = this.getAccountIds(this.getCustomerId());

    }
    @Test
    @Tag("BACK")
    @Order(3)
    public void testTransferFunds() {
        ExtentTest test = extent.createTest("Test Transfer Funds");
        System.out.println("Test Transfer Funds");
        test.log(Status.INFO, "Test Transfer Funds");

        Integer fromAccountId = accountIds.get(0);
        Integer toAccountId = accountIds.get(1);
        Double amount = 50.0;

        given()
                .contentType(ContentType.TEXT)
                .queryParam("fromAccountId", fromAccountId)
                .queryParam("toAccountId", toAccountId)
                .queryParam("amount", amount)
                .when().post("https://parabank.parasoft.com/parabank/services/bank/transfer")
                .then()
                .statusCode(200)
                .body(containsString("Successfully transferred"))
                .log().status()
                .log().body();

        test.log(Status.PASS, "Successfully transferred");

    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }

}
