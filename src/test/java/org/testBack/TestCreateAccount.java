package org.testBack;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.reports.ReportFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCreateAccount extends BackBaseTest {
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/CreateAccount-backTest.html");
    static ExtentReports extent;

    Integer customerId;
    Integer newAccountType;
    Integer fromAccountId;


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

        customerId = this.getCustomerId();
        System.out.println("customerId: " + customerId);
        newAccountType = 1;
        fromAccountId = this.getAccountIds(customerId).getFirst();
        System.out.println("fromAccountId: " + fromAccountId);


    }

    @Test
    @Tag("BACK")
    @Order(2)
    public void testCreateAccount() {
        ExtentTest test = extent.createTest("Test Create Account");
        System.out.println("Test Create Account");
        test.log(Status.INFO, "Test Create Account");

        Map<String, Object> request = new HashMap<>();
        request.put("customerId", customerId);
        request.put("newAccountType", newAccountType);
        request.put("fromAccountId", fromAccountId);

        given()
                .queryParams(request)
                .when().post("https://parabank.parasoft.com/parabank/services/bank/createAccount")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Test Create Account finalizado");
        test.log(Status.PASS, "Test Create Account finalizado");

    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }


}
