package org.testing.accountPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.BasePage;

public class CreateAccountPage extends BasePage {
    private final By newAccount = By.xpath("//*[@id='leftPanel']/ul/li[1]/a");
    private final By typeOfAccount = By.id("type");
    private final By optionSavings = By.xpath("//*[@id='type']/option[1]");
    private final By btnOpenNewAccount = By.xpath("//*[@id='openAccountForm']/form/div/input");
    private final By resultContent = By.cssSelector("div[id='openAccountResult'] h1[class='title']");
    private final By accountId = By.id("newAccountId");


    /**
     * Constructor de la clase CreateAccountPage.
     * @param driver El controlador del navegador web.
     * @param wait   El controlador de espera.
     */
    public CreateAccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Hace clic en el enlace "Open New Account".
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickNewAccount() throws InterruptedException {
        this.click(newAccount);
    }

    /**
     * Selecciona el tipo de cuenta "Savings" en el campo "Account Type".
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void selectTypeOfAccount() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(typeOfAccount));
        this.click(typeOfAccount);
        this.click(optionSavings);
    }

    /**
     * Hace clic en el botón "Open New Account".
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickOpenNewAccount() throws InterruptedException {
        this.click(btnOpenNewAccount);
    }

    /**
     * Obtiene el número de cuenta.
     * @return el número de cuenta.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getAccountId() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountId));
        return this.getText(accountId);
    }
/**
     * Obtiene el mensaje de confirmación de apertura de cuenta.
     * @return el mensaje de confirmación.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getConfirmationMessage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultContent));
        return this.getText(resultContent);
    }

}
