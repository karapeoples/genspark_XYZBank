package test.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverUtil {
    static WebDriver driver;
    private DriverUtil(){

    }

    public static WebDriver getDriver() {
        String browser = LoadProperties.nabProperty("BROWSER");
        try{
        if (driver == null) {
            if (browser.contains("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
                chromeOptions.addArguments("disable-infobars");
                chromeOptions.addArguments("-disable-setuid-sandbox");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--no-sandbox");
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.default_directory", System.getProperty("user.dir") + File.separator + "externalFiles" + File.separator + "downloadFiles");
                chromeOptions.setExperimentalOption("prefs", prefs);
                URL url = new URL("http://localhost:4444/wd/hub");
                driver = new RemoteWebDriver(url, chromeOptions);
            }
            if (browser.contains("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return driver;
    }

    public static void main(String[] args) {
        getDriver();
    }
}
