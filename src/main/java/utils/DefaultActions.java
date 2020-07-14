package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefaultActions {

    int timeout = 20;
    private WebDriver driver;
    private WebDriverWait wait;

    public DefaultActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
    }

    public void navega(String url) {
        this.driver.get(url);
    }

    private By feedBackPanelInfo = By.xpath("//ul[@class='feedbackPanel']/li[@class='feedbackPanelINFO']");

    private  By feedBackPanelModalInfo = By.xpath("//div/div/div/div/div[2]/div/div");

    public void waitForElementPresent(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElementEnabled(By locator) {
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(locator),
                ExpectedConditions.visibilityOfElementLocated(locator),
                ExpectedConditions.elementToBeClickable(locator)));
    }

    public void waitForElementVisibled(By locator) {
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(locator),
                ExpectedConditions.visibilityOfElementLocated(locator)));
    }

    public void waitForElementsPresents(By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public void waitForElementsVisibles(By locator) {
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfAllElementsLocatedBy(locator),
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
    }

    public void waitAndClick(By elementLocator) {
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(elementLocator),
                ExpectedConditions.visibilityOfElementLocated(elementLocator),
                ExpectedConditions.elementToBeClickable(elementLocator)));
        //driver.findElement(elementLocator).click();
    }

    public void waitElementToBeClickable(By locator) {
        waitForElementsVisibles(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void clickAndWait(By elementToClick, By elementToWait) {
        waitElementToBeClickable(elementToClick);
        driver.findElement(elementToClick).click();
        waitForElementsVisibles(elementToWait);
    }

    public String obterTexto(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public void preencherCampoTexto(By locator, String valor) {
        waitForElementVisibled(locator);
        driver.findElement(locator).sendKeys(valor);
    }

    public void limparCampoTexto(By locator) {
        waitForElementsVisibles(locator);
        driver.findElement(locator).clear();
    }

    public void preencherCampoDropdown(By dropdownListLocator, By dropdownLocator, int index) {
        wait.until(ExpectedConditions.and(ExpectedConditions.invisibilityOfElementLocated(dropdownListLocator),
                ExpectedConditions.presenceOfElementLocated(dropdownLocator),
                ExpectedConditions.visibilityOfElementLocated(dropdownLocator)));

        driver.findElement(dropdownLocator).click();
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(dropdownListLocator),
                ExpectedConditions.visibilityOfElementLocated(dropdownListLocator)));
        driver.findElements(dropdownListLocator).get(index - 1).click();
    }

    public void preencherCampoDropdownSelect(By selectLocator, int index) {
        waitForElementsVisibles(selectLocator);
        new Select(driver.findElement(selectLocator)).selectByIndex(index);
    }

    public void preencherCampoDropdownSelectValue(By selectLocator, String value) {
        waitForElementsVisibles(selectLocator);
        new Select(driver.findElement(selectLocator)).selectByValue(value);
    }

    public void adicionarAnexo(By attachmentLocator, By addButtonLocator, WebElement attachmentElement,
                               WebElement addButtonElement, String filePath) {
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(attachmentLocator),
                ExpectedConditions.visibilityOfElementLocated(attachmentLocator)));
        attachmentElement.sendKeys(filePath);
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(addButtonLocator),
                ExpectedConditions.visibilityOfElementLocated(addButtonLocator),
                ExpectedConditions.elementToBeClickable(addButtonLocator)));
        addButtonElement.click();
    }

    public WebElement getGridConsulta() {
        waitForElementPresent(By.tagName("table"));
        waitForElementsVisibles(By.tagName("table"));
        return driver.findElement(By.tagName("table"));
    }

    public WebElement getLinhaGrid(int rowIndex) {
        return getGridConsulta().findElements(By.xpath("tbody/tr")).get(rowIndex - 1);
    }

    public WebElement getCelulaGrid(int rowIndex, int collumnIndex) {
        return getLinhaGrid(rowIndex).findElements(By.xpath("td")).get(collumnIndex - 1);
    }

    public void changeWindowHandle() {

        System.out.println(driver.getWindowHandle());

        for (String winHandle : driver.getWindowHandles()) {
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);
        }
    }


    public String retornaDataEHora(String pattern) {
        DateFormat dateFormart = new SimpleDateFormat(pattern);
        Date date = new Date();
        return dateFormart.format(date);
    }

    public void clickOpcaoRadioButton(WebElement element, String value) {
        List<WebElement> lista = element.findElements(By.tagName("input"));
        for (WebElement e : lista) {
            if (e.getAttribute("value").equals(value)) {
                new Actions(driver).click(e).perform();
                break;
            }
        }
    }

    public WebElement retornaElementoDaTablePorValorPassado(WebElement table, String valorEsperado) {
        WebElement t = null;
        List<WebElement> listaTagTr = table.findElements(By.tagName("tr"));
        for (WebElement tr : listaTagTr) {
            if (tr.getText().equals(valorEsperado)) {
                t = tr;
                break;
            }

        }
        return t;
    }

    public WebElement retornaElementoDaListaSeContemAlgumaInformacaoNaTr(WebElement table, String valorEsperado) {
        WebElement t = null;
        List<WebElement> listaTagTr = table.findElements(By.tagName("tr"));
        for (WebElement tr : listaTagTr) {
            if (tr.getText().contains(valorEsperado)) {
                t = tr;
                break;
            }

        }
        return t;
    }

    public String retornaMensagemPanelInfo() {
        waitForElementsVisibles(feedBackPanelInfo);
        return driver.findElement(feedBackPanelInfo).getText();
    }

    public String retornaMensagemModalInfo(){
        waitForElementsVisibles(feedBackPanelModalInfo);
        return driver.findElement(feedBackPanelModalInfo).getText();
    }

    public void realizarScroll(int xhorizontal, int xvertical) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + xhorizontal + "," + xvertical + ")");
    }

    public void sleep(Long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
