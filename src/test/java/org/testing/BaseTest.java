package org.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.userPages.LoginPage;
import org.testing.userPages.RegisterPage;

import java.time.Duration;

public class BaseTest {
    private final WebDriver driver ;
    private final WebDriverWait wait;

    public BaseTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }



    public boolean isLoggedIn() {
        // Verificar si el usuario está logueado
        return !driver.findElements(By.xpath("//*[@id='leftPanel']/ul/li[8]/a")).isEmpty();
    }

    public void login(String username, String password) throws InterruptedException {
        // Realizar login
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.login(username, password);
    }

    public void registerUser(User user) throws InterruptedException {
        // Registrar un nuevo usuario
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        registerPage.register(user);
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}
