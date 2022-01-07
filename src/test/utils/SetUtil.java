package test.utils;

import org.openqa.selenium.WebElement;

public class SetUtil {

    //Other methods to build to send keys

    public static void setEle(WebElement element, String str){
        element.clear();
        element.sendKeys(str);
    }
}
