package org.testing.accountPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testing.BasePage;

public class AccountActivityPage extends BasePage {

    private final By accountOverviewLnk = By.cssSelector("a[href='overview.htm']");
    private final By validateTextLegend = By.xpath("//*[@id='accountTable']/tfoot/tr/td");
    private final By account = By.xpath("//*[@id='accountTable']/tbody/tr[1]/td[1]/a");
    private final By validateTextTitle = By.xpath("//*[@id='accountDetails']/h1");
    private final By activityperiod = By.xpath("//*[@id='month']/option[1]");
    private final By type = By.xpath("//*[@id='transactionType']/option[1]");
    private final By goBtn = By.xpath("//*[@id='activityForm']/table/tbody/tr[3]/td[2]/input");


    /**
     * Constructor de la página base.
     *
     * @param driver El controlador del navegador web.
     * @param wait   El controlador de espera.
     */
    public AccountActivityPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /**
     * Hace clic en el enlace "Account Overview".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickAccountOverview() throws InterruptedException {
        this.click(accountOverviewLnk);
    }

    /**
     * Obtiene el contenido de la leyenda de cuentas.
     *
     * @return el contenido de la tabla de cuentas.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getAccountlegendContent() throws InterruptedException {
        return this.getText(validateTextLegend);
    }

    /**
     * Hace clic en la cuenta.
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickAccount() throws InterruptedException {
        this.click(account);
    }

    /**
     * Obtiene el título de la página de detalles de la cuenta.
     *
     * @return el contenido de la página de detalles de la cuenta.
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public String getAccountDetailsTitle() throws InterruptedException {
        return this.getText(validateTextTitle);
    }

    /**
     * Selecciona el periodo de actividad y el typo de cuenta.
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void selectActivityPeriod() throws InterruptedException {
        this.click(activityperiod);
        this.click(type);
    }

    /**
     * Hace clic en el botón "Go".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera.
     */
    public void clickGo() throws InterruptedException {
        this.click(goBtn);
    }

}
