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
import org.testing.userPages.RegisterPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reports/UserRegistration-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZA EL TEST >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    //test de Registro Exitoso

    @Test
    @Tag("REGISTRO")
    public void successfulRegistration() throws InterruptedException {
        ExtentTest test = extent.createTest("successful user registration");
        test.log(Status.INFO, "Start test");
        test.log(Status.PASS, "Registering a new user");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        User randomUser = User.randomUser();

        try {
            registerPage.clickLnkRegister();
            registerPage.writeFirstName(randomUser.getFirstName());
            registerPage.writeLastName(randomUser.getLastName());
            registerPage.writeAddress(randomUser.getAddress());
            registerPage.writeCity(randomUser.getCity());
            registerPage.writeState(randomUser.getState());
            registerPage.writeZipCode(randomUser.getZipCode());
            registerPage.writeTelephone(randomUser.getPhoneNumber());
            registerPage.writeSsn(randomUser.getSsn());
            registerPage.writeUsername(randomUser.getFirstName() + randomUser.getLastName());
            registerPage.writePassword("123456");
            registerPage.writeConfirmPassword("123456");
            test.log(Status.PASS, "User data entered successfully");
            ReportFactory.captureScreenshot(test, "SUCCESSFUL_USER_DATA", driver);
            registerPage.clickRegisterSubmit();
            test.log(Status.INFO, "Checking registration");

            //assertion para chequear que la cuenta se creo correctamente, si no se crea correctamente el test falla.
            String expectedMessage = "Your account was created successfully. You are now logged in.";
            String actualMessage = registerPage.getResultContent();
            assertEquals(actualMessage, expectedMessage, "Expected message: " + expectedMessage + ", but got: " + actualMessage);
            test.log(Status.PASS, "User registration successful");
            ReportFactory.captureScreenshot(test, "SUCCESSFUL_USER_REGISTRATION", driver);
            System.out.println("Registro exitoso");
            test.log(Status.INFO, registerPage.getResultContent());

        } catch (Exception error) {
            test.log(Status.FAIL, "User registration failed " + error.getMessage());
            System.out.println("Registro fallido");
            ReportFactory.captureScreenshot(test, "FAILED_USER_REGISTRATION", driver);
            throw new RuntimeException("User registration failed", error);
        }
        test.log(Status.INFO, "End test");

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
