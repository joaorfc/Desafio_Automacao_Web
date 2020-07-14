package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.DefaultActions;

public class InicialPage extends DefaultActions {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
    By comboSelecionaVersaoTheme = By.id("switch-version-select");
	By btnAddCustomer = By.xpath("//*[@id=\"gcrud-search-form\"]/div[1]/div[1]/a");


	public InicialPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
        this.wait = wait;
	}

	public void selecionaVersaoTheme() {
		waitForElementVisibled(comboSelecionaVersaoTheme);
		Select option = new Select(driver.findElement(comboSelecionaVersaoTheme));
		option.selectByIndex(1);		
	}

	public void clicaBotaoAddCustomer(){
		driver.findElement(btnAddCustomer).click();
	}
	
	

}
