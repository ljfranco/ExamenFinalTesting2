package org.testing.userPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.BasePage;

public class LoginPage extends BasePage {

    private final By username = By.xpath("//*[@id='loginPanel']/form/div[1]/input");
    private final By password = By.xpath("//*[@id='loginPanel']/form/div[2]/input");
    private final By btnLogin = By.xpath("//*[@id='loginPanel']/form/div[3]/input");
    private final By welcomeMessage = By.xpath("//*[@id='leftPanel']/p/b");


    /**
     * Constructor de la clase LoginPage.
     * @param driver El controlador del navegador web.
     * @param wait   El controlador de espera.
     */
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Ingresa el nombre de usuario en el campo "Username".
     * @param user el nombre de usuario a ingresar.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void writeUsername(String user) throws InterruptedException {
        this.sendText(user, username);
    }

    /**
     * Ingresa la contraseña en el campo "Password".
     * @param pass la contraseña a ingresar.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void writePassword(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /**
     * Hace clic en el botón "Login".
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickBtnLogin() throws InterruptedException {
        this.click(btnLogin);
    }

    /**
     * Obtiene el mensaje de bienvenida.
     * @return el mensaje de bienvenida.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getWelcomeMessage() throws InterruptedException {
        return this.getText(welcomeMessage);
    }

    /**
     * Realiza el login en la aplicación.
     * @param user el nombre de usuario.
     * @param pass la contraseña.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void login(String user, String pass) throws InterruptedException {
        this.writeUsername(user);
        this.writePassword(pass);
        this.clickBtnLogin();

    }


}
