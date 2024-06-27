package org.testing;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.userPages.RegisterPage;

import java.time.Duration;

@Getter
@Setter
public class BaseTest {
    private final WebDriver driver ;
    private final WebDriverWait wait;
    private final String url = "https://parabank.parasoft.com/parabank/index.htm";

    public BaseTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
    }

    public void registerUser(User user) throws InterruptedException {
        // Registrar un nuevo usuario
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl(url);
        registerPage.register(user);
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

}
