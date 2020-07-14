package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.InicialPage;
import pages.ListarCustomerPage;
import utils.DefaultTestConfigs;

public class Desafio2Tests extends DefaultTestConfigs {

    private WebDriver driver;
    private InicialPage inicialPage;
    private AddCustomerPage addCustomerPage;
    private ListarCustomerPage listarCustomerPage;

    @BeforeMethod
    public void setUp() throws Exception {
        this.driver = new ChromeDriver();
        this.inicialPage = new InicialPage(driver);
        this.addCustomerPage = new AddCustomerPage(driver);
        this.listarCustomerPage = new ListarCustomerPage(driver);
        this.inicialPage.navega(URLBASE);;
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception{
        Thread.sleep(2000);
        driver.quit();
    }

    @Test
    public void Desafio2Teste() throws Exception {
        inicialPage.selecionaVersaoTheme();
        inicialPage.clicaBotaoAddCustomer();
        addCustomerPage.preencheCamposNome();
        addCustomerPage.preencheCampoPhone();
        addCustomerPage.preencheCamposEndereco();
        addCustomerPage.selecionaEmpregador();
        addCustomerPage.preencheLimiteCredito();
        addCustomerPage.clicaBotaoSalvar();
        addCustomerPage.verificaMensagemSucesso();
        listarCustomerPage.clicaLinkListarCustomer();
        listarCustomerPage.pesquisaNomeCustomer();
        listarCustomerPage.clicaCheckBox();
        listarCustomerPage.clicaBotaoDelete();
        listarCustomerPage.verificaMensagemConfirmacaoDelete();
        listarCustomerPage.clicaBotaoDeletePopUp();
        listarCustomerPage.verificaMensagemConfirmacaoDelete();
    }

}
