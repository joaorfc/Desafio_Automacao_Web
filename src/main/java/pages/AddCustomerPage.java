package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.DefaultActions;

public class AddCustomerPage extends DefaultActions {

    private WebDriver driver;
    private WebDriverWait wait;

    By campoName = By.id("field-customerName");
    By campoLastName = By.id("field-contactLastName");
    By campoContactFirstName = By.id("field-contactFirstName");
    By campoPhone = By.id("field-phone");
    By campoAddressLine = By.id("field-addressLine1");
    By campoAddressLine2 = By.id("field-addressLine2");
    By campoCity = By.id("field-city");
    By campoState = By.id("field-state");
    By campoPostalCode = By.id("field-postalCode");
    By campoCountry = By.id("field-country");
    By selectFromEmployeer = By.xpath("/html/body/div[2]/div/div/div/div[2]/form/div[11]/div/div");
    By inputSelectFromEmployeer = By.xpath("//*[@id=\"field_salesRepEmployeeNumber_chosen\"]/div/div/input");
    By campoCreditLimit = By.id("field-creditLimit");
    By btnSave = By.id("form-button-save");
    By msgSucesso = By.xpath("/html/body/div[2]/div/div/div/div[2]/form/div[14]/div[2]/p");


    public AddCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public void preencheCamposNome(){
        changeWindowHandle();
        waitForElementVisibled(campoName);
        driver.findElement(campoName).sendKeys("Teste Sicredi");
        driver.findElement(campoLastName).sendKeys("Teste");
        driver.findElement(campoContactFirstName).sendKeys("Joao Ricardo");
    }

    public void preencheCampoPhone(){
        driver.findElement(campoPhone).sendKeys("51 9999-9999");
    }

    public void preencheCamposEndereco(){
        driver.findElement(campoAddressLine).sendKeys("Av Assis Brasil, 3970");
        driver.findElement(campoAddressLine2).sendKeys("Torre D");
        driver.findElement(campoCity).sendKeys("Porto Alegre");
        driver.findElement(campoState).sendKeys("RS");
        driver.findElement(campoPostalCode).sendKeys(" 91000-000");
        driver.findElement(campoCountry).sendKeys("Brasil");
    }

    public void selecionaEmpregador() {
        driver.findElement(selectFromEmployeer).click();
        driver.findElement(inputSelectFromEmployeer).sendKeys("Fixter");
        driver.findElement(inputSelectFromEmployeer).sendKeys(Keys.ENTER);
    }

    public void preencheLimiteCredito(){
        driver.findElement(campoCreditLimit).sendKeys("200");
    }

    public void clicaBotaoSalvar(){
        driver.findElement(btnSave).click();
    }

    public void verificaMensagemSucesso() throws InterruptedException {
        Thread.sleep(3000);
        SoftAssert softAssert = new SoftAssert();
        String mensagem = driver.findElement(msgSucesso).getText();
        softAssert.assertEquals(mensagem, "Your data has been successfully stored into the database. Edit Customer or Go back to list");
    }
}
