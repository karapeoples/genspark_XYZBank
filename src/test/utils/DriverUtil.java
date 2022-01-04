package test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverUtil {
    static WebDriver driver;
    private DriverUtil(){

    }

    public static WebDriver getDriver() {
        String browser = LoadProperties.nabProperty("BROWSER");
        if (driver == null){
            if(browser.contains("chrome")){
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                chromeOptions.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(chromeOptions);
            }
            if(browser.contains("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void main(String[] args) {
        getDriver();
    }
}
