package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.DefaultActions;

public class ListarCustomerPage extends DefaultActions {

    private WebDriver driver;
    private WebDriverWait wait;

    By linkGoToBackList = By.xpath("//*[@id=\"report-success\"]/p/a[2]");
    By inputSearchName = By.xpath("//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[3]/input");
    By clicaCheckBox = By.xpath("//*[@id=\"gcrud-search-form\"]/div[2]/table/tbody/tr[1]/td[1]/input");
    By btnDelete = By.xpath("//*[@id=\"gcrud-search-form\"]/div[2]/table/thead/tr[2]/td[2]/div[1]");
    By msgConfirmacaoDelete = By.xpath("/html/body/div[2]/div[2]/div[3]/div/div/div[2]/p[1]");
    By btnDeletePopUp = By.xpath("/html/body/div[2]/div[2]/div[3]/div/div/div[3]/button[2]");
    By msgSucessoDelete = By.xpath("/html/body/div[3]/span[3]");

    public ListarCustomerPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = wait;
    }

    public void clicaLinkListarCustomer() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(linkGoToBackList).click();
    }

    public void pesquisaNomeCustomer(){
        driver.findElement(inputSearchName).sendKeys("Teste Sicredi");
    }

    public void clicaCheckBox() throws InterruptedException {
        Thread.sleep(2500);
        driver.findElement(clicaCheckBox).click();
        Thread.sleep(2500);
        driver.findElement(clicaCheckBox).click();
    }

    public void clicaBotaoDelete(){
        waitElementToBeClickable(btnDelete);
        driver.findElement(btnDelete).click();
    }

    public void verificaMensagemConfirmacaoDelete() throws InterruptedException {
        //Utilização da Thread.sleep somente para poder verificar a mensagem da pagina
        Thread.sleep(1000);
        SoftAssert softAssert = new SoftAssert();
        String mensagem = driver.findElement(msgConfirmacaoDelete).getText();
        softAssert.assertEquals(mensagem, "Are you sure that you want to delete this 1 item?");
    }

    public void clicaBotaoDeletePopUp(){
        driver.findElement(btnDeletePopUp).click();
    }

    public void verificaMensagemSucessoDelete(){
        SoftAssert softAssert = new SoftAssert();
        String mensagemSucesso = driver.findElement(msgConfirmacaoDelete).getText();
        softAssert.assertEquals(mensagemSucesso, "Your data has been successfully deleted from the database.");
    }



}
