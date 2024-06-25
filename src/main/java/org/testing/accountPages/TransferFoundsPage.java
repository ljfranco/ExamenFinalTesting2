package org.testing.accountPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.BasePage;

public class TransferFoundsPage extends BasePage {

    private final By transferFoundslnk = By.cssSelector("a[href='transfer.htm']");
    private final By contentToValidate = By.xpath("//*[@id='showForm']/h1");
    private final By amount = By.id("amount");
    private final By fromAccount = By.xpath("//*[@id='fromAccountId']/option[1]");
    private final By toAccount = By.xpath("//*[@id='toAccountId']/option[2]");
    private final By transferBtn = By.xpath("//*[@id='transferForm']/div[2]/input");
    private final By resultContent = By.xpath("//*[@id='showResult']/h1");


    /**
     * Constructor de la página base.
     *
     * @param driver El controlador del navegador web.
     * @param wait   El controlador de espera.
     */
    public TransferFoundsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Hace clic en el enlace "Transfer Founds".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickTransferFoundsLnk() throws InterruptedException {
        this.click(transferFoundslnk);
    }

    /**
     * Obtiene el Titulo de la página de transferencia de fondos.
     *
     * @return el contenido de la página de transferencia de fondos.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getTransferFoundsContent() throws InterruptedException {
        return this.getText(contentToValidate);
    }

    /**
     * Ingresa datos de la transferencia
     *
     * @param amount el monto a transferir.
     *               @param fromAccount la cuenta de origen.
     *                                  @param toAccount la cuenta de destino.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String writeTransferData(String amount) throws InterruptedException {
        this.sendText(amount, this.amount);
        this.click(this.fromAccount);
        this.click(this.toAccount);

        return "$50 is to be transferred from account "+ this.getText(fromAccount) + " to account " + this.getText(toAccount);
    }

    /**
     * Hace clic en el botón "Transfer".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickTransfer() throws InterruptedException {
        this.click(transferBtn);
    }

    /**
     * Obtiene el mensaje de confirmación de la transferencia.
     *
     * @return el mensaje de confirmación.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
public String getConfirmationMessage() throws InterruptedException {
    wait.until(ExpectedConditions.visibilityOfElementLocated(resultContent));
    return this.getText(resultContent);
}



}
