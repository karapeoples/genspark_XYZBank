package test.java;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import test.utils.*;

import java.io.File;


public class BaseTest {
    static WebDriver driver;

    @BeforeClass
    public void setup(){
        String dir = System.getProperty("user.dir");
        PropertyConfigurator.configure(dir + File.separator +"src" +File.separator +"test"+File.separator+"resources"+File.separator +"log4j.properties");
        driver = DriverUtil.getDriver();
        driver.manage().window().maximize();
        String url = LoadProperties.nabProperty("URL");
        driver.get(url+"/login");
    }


    @AfterSuite
    public void quitTest() {
        driver.quit();
    }
}
