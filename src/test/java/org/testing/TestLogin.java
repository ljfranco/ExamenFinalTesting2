package org.testing;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.reports.ReportFactory;
import org.testing.userPages.LoginPage;
import org.testing.userPages.RegisterPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLogin extends BaseTest{
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/UserLogin-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        // crear un usuario de prueba desde el primer registro del Json, username: DawnNunez, password: 123456
        BaseTest baseTest = new BaseTest();
        baseTest.registerUser(User.firstUser());
        baseTest.close();
        //Configuracion para la prueba de Login
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl(this.getUrl());
    }

    @Test
    @Tag("LOGIN")
    @Tag("FRONTEND")
    public void successfullLogin() throws InterruptedException {
        ExtentTest test = extent.createTest("successful user Login");
        test.log(Status.INFO, "Start test");
        test.log(Status.PASS, "Login a user ");
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.writeUsername("DawnNunez");
        loginPage.writePassword("123456");
        test.log(Status.INFO,"Loaded User Data");
        loginPage.clickBtnLogin();
        String welcomeMessage = loginPage.getWelcomeMessage();
        String expectedMessage = "Welcome";
        assertEquals(expectedMessage, welcomeMessage, "Expected message: " + expectedMessage + ", but got: " + welcomeMessage);
        test.log(Status.PASS, "User logged in successfully");

    }

    @AfterEach
    public void close() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZA EL TEST >>>");
    }


}
