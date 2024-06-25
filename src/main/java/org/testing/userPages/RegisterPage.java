package org.testing.userPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.BasePage;
import org.testing.User;

public class RegisterPage extends BasePage {
private final By lnkRegister = By.xpath("//*[@id='loginPanel']/p[2]/a");
private final By firstName = By.id("customer.firstName");
private final By lastName = By.id("customer.lastName");
private final By address = By.id("customer.address.street");
private final By city = By.id("customer.address.city");
private final By state = By.id("customer.address.state");
private final By zipCode = By.id("customer.address.zipCode");
private final By phoneNumber = By.id("customer.phoneNumber");
private final By ssn = By.id("customer.ssn");
private final By username = By.id("customer.username");
private final By password = By.id("customer.password");
private final By confirmPassword = By.id("repeatedPassword");
private final By btnRegister = By.xpath("//*[@id='customerForm']/table/tbody/tr[13]/td[2]/input");
private final By resultContent = By.xpath("//*[@id='rightPanel']/p");

    /**Constructor de la clase RegisterPage
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    /** Hace click en el botón "Register".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickLnkRegister() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(lnkRegister));
        this.click(lnkRegister);
    }

    /** Ingresa el nombre proporcionado en el campo First Name.
     * @param name el nombre a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeFirstName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }

    /** Ingresa el apellido proporcionado en el campo Last Name.
     * @param name el apellido a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeLastName(String name) throws InterruptedException {
        this.sendText(name, lastName);
    }

    /** Ingresa la direccion proporcionada en el campo Address.
     * @param addressStreet la direccion a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeAddress(String addressStreet) throws InterruptedException {
        this.sendText(addressStreet, address);
    }

    /** Ingresa la Ciudad proporcionada en el campo City.
     * @param addressCity la ciudad a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeCity(String addressCity) throws InterruptedException {
        this.sendText(addressCity, city);
    }

    /** Ingresa el Estado proporcionada en el campo State.
     * @param addressState el estado a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeState(String addressState) throws InterruptedException {
        this.sendText(addressState, state);
    }

    /** Ingresa el Codigo Postal proporcionada en el campo Zip Code.
     * @param addressZipCode el codigo postal a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeZipCode(String addressZipCode) throws InterruptedException {
        this.sendText(addressZipCode, zipCode);
    }

    /** Ingresa el teléfono proporcionado en el campo de teléfono.
     * @param phone el teléfono a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeTelephone(String phone) throws InterruptedException {
        this.sendText(phone, phoneNumber);
    }

    /** Ingresa el SSN proporcionado en el campo SSN.
     * @param numSsn el SSN a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeSsn(String numSsn) throws InterruptedException {
        this.sendText(numSsn, ssn);
    }

    /** Ingresa el nombre de usuario proporcionado en el campo de username.
     * @param user el nombre de usuario a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeUsername(String user) throws InterruptedException {
        this.sendText(user, username);
    }

    /** Ingresa la contraseña proporcionada en el campo de contraseña.
     * @param pass la contraseña a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writePassword(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /** Reingresa la contraseña proporcionada en el campo de confirmación de contraseña.
     * @param pass la contraseña a reingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void writeConfirmPassword(String pass) throws InterruptedException {
        this.sendText(pass, confirmPassword);
    }

    /** Hace click en el botón "REGISTER".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickRegisterSubmit() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(btnRegister));
        this.click(btnRegister);
    }

    /** Obtiene el mensaje de resultado de la operación.
     * @return el texto del mensaje de resultado
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String getResultContent() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultContent));
        return this.getText(resultContent);
    }

    /** Realiza el registro de un nuevo usuario.
     * @param user el usuario a registrar
     */
    public void register(User user) {
        try {
            this.clickLnkRegister();
            this.writeFirstName(user.getFirstName());
            this.writeLastName(user.getLastName());
            this.writeAddress(user.getAddress());
            this.writeCity(user.getCity());
            this.writeState(user.getState());
            this.writeZipCode(user.getZipCode());
            this.writeTelephone(user.getPhoneNumber());
            this.writeSsn(user.getSsn());
            this.writeUsername(user.getFirstName() + user.getLastName());
            this.writePassword("123456");
            this.writeConfirmPassword("123456");
            this.clickRegisterSubmit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
