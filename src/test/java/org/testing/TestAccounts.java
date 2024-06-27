package org.testing;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.reports.ReportFactory;
import org.testing.accountPages.AccountActivityPage;
import org.testing.accountPages.CreateAccountPage;
import org.testing.accountPages.TransferFoundsPage;
import org.testing.userPages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAccounts extends BaseTest{
    private static WebDriver driver;
    private static WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/Account-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public void setUpClass() throws InterruptedException {
        // Configuracion para el reporte de la prueba
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");

        // crear un usuario de prueba desde el primer registro del Json, username: DawnNunez, password: 123456
        BaseTest baseTest = new BaseTest();
        baseTest.registerUser(User.firstUser());
        baseTest.close();

        //Configuracion para la prueba de crear una nueva cuenta
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, wait);
        createAccountPage.setup();
        createAccountPage.getUrl(this.getUrl());
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login("DawnNunez", "123456");

    }

    @Test
    @Tag("ACCOUNT")
    @Tag("FRONTEND")
    @Order(1)
    public void createNewAccount() throws InterruptedException {
        ExtentTest test = extent.createTest("Create a new account");
        test.log(Status.INFO, "Start test");
        test.log(Status.INFO, "Creating a new account");
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, wait);

        createAccountPage.clickNewAccount();
        createAccountPage.selectTypeOfAccount();
        createAccountPage.clickOpenNewAccount();
        String confirmationMessage = createAccountPage.getConfirmationMessage();
        String expectedMessage = "Account Opened!";
        String accountId = createAccountPage.getAccountId();
        assertEquals(confirmationMessage, expectedMessage, "Expected message: " + expectedMessage + ", but got: " + confirmationMessage);
        test.log(Status.PASS, "Account N°"+accountId+" created successfully");
        System.out.println("Cuenta Creada");
    }

    @Test
    @Tag("ACCOUNT")
    @Tag("FRONTEND")
    @Order(2)
    public void transferFunds() throws InterruptedException {
        ExtentTest test = extent.createTest("Transfer funds");
        test.log(Status.INFO, "Start test");
        test.log(Status.INFO, "making transfer");
        TransferFoundsPage transferFoundsPage = new TransferFoundsPage(driver, wait);

        transferFoundsPage.clickTransferFoundsLnk();
        String content = transferFoundsPage.getTransferFoundsContent();
        assertEquals("Transfer Funds", content, "Expected message: Transfer Funds, but got: " + content);
        test.log(Status.PASS, "Transfer funds page loaded successfully");
        String action = transferFoundsPage.writeTransferData("50");
        test.log(Status.INFO, "uploaded data");
        System.out.println("Datos de transferencia ingresados");
        test.log(Status.PASS, action);
        System.out.println(action);
        transferFoundsPage.clickTransfer();
        String confirmationMessage = transferFoundsPage.getConfirmationMessage();
        String expectedMessage = "Transfer Complete!";
        assertEquals(confirmationMessage, expectedMessage, "Expected message: " + expectedMessage + ", but got: " + confirmationMessage);
        test.log(Status.PASS, "Transfer funds successfully");
        System.out.println("Fondos transferidos");
    }

    @Test
    @Tag("ACCOUNT")
    @Tag("FRONTEND")
    @Order(3)
    public void accountSummary() throws InterruptedException {
        ExtentTest test = extent.createTest("Account activity");
        test.log(Status.INFO, "Start test");
        test.log(Status.INFO, "Checking account activity");
        AccountActivityPage accountActivityPage = new AccountActivityPage(driver, wait);

        accountActivityPage.clickAccountOverview();
        String legend = accountActivityPage.getAccountlegendContent();
        String expectedMessage = "*Balance includes deposits that may be subject to holds";
        assertEquals(expectedMessage, legend, "Expected message: "+expectedMessage+" but got: " + legend);
        test.log(Status.PASS, "The text \""+ expectedMessage +"\" is in place");
    }

    @Test
    @Tag("ACCOUNT")
    @Tag("FRONTEND")
    @Order(4)
    public void accountActivity() throws InterruptedException {
        ExtentTest test = extent.createTest("Account activity");
        test.log(Status.INFO, "Start test");
        test.log(Status.INFO, "Checking account activity");
        AccountActivityPage accountActivityPage = new AccountActivityPage(driver, wait);

        accountActivityPage.clickAccountOverview();
        String legend = accountActivityPage.getAccountlegendContent();
        String expectedMessage = "*Balance includes deposits that may be subject to holds";
        assertEquals(expectedMessage, legend, "Expected message: "+expectedMessage+" but got: " + legend);
        test.log(Status.PASS, "The text \""+ expectedMessage +"\" is in place");
        accountActivityPage.clickAccount();
        String title = accountActivityPage.getAccountDetailsTitle();
        assertEquals("Account Details", title, "Expected message: Account Details, but got: " + title);
        test.log(Status.PASS, "Account details page loaded successfully");
        accountActivityPage.selectActivityPeriod();
        accountActivityPage.clickGo();
    }


    @AfterAll
    public void endTest() {
        //cerrar el navegador
        CreateAccountPage createAccountPage = new CreateAccountPage(driver, wait);
        createAccountPage.close();
        // Guardar el reporte
        extent.flush();
        System.out.println("<<< FINALIZA EL TEST >>>");
    }

}
