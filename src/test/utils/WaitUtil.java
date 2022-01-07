package test.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static test.utils.DriverUtil.getDriver;

public class WaitUtil {

    static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));;

    //Each type of condition needed would need to be scattered back through the test files.....

    public void waitTillVisable(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitTillInvisable(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
