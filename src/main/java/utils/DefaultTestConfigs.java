package utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DefaultTestConfigs {

    public static String url = "https://www.grocerycrud.com/demo/bootstrap_theme";


    public static final String URLBASE = url;
   
    protected WebDriver driver;
    
    protected String chromerdriverPath = "./chromedriver.exe";

    public void inicializaDriver() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private String generateUniqueFileName(){
        String filename = "";
        long millis = System.currentTimeMillis();
        String datetime = new Date().toString();
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");
        filename = "_" + datetime + "_" + millis;
        return filename;
    }

   



}