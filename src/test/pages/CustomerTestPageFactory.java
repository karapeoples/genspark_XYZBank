package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CustomerTestPageFactory {

    WebDriver driver;

    public CustomerTestPageFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//button[text()='Customer Login']")
    private static WebElement customerButton;

    @FindBy(tagName = "select")
    private static  WebElement customerDD;

    @FindBy(xpath = "//button[@type = 'submit']")
    private static WebElement loginButton;

    @FindBy(xpath = "//div[@ng-hide = 'noAccount']//button")
    private static List<WebElement> verifyButtons;

    @FindBy(xpath = "//form[@ng-submit = 'deposit()']//input")
    private static WebElement amountInput;

    @FindBy(xpath = "//span[@class = 'error ng-binding']")
    private static WebElement successMessage;

    @FindBy(xpath = "//input[@type='number']")
    private static WebElement withdrawInput;

    @FindBy(xpath = "//button[@ng-click='back()']")
    private static WebElement backButton;

    @FindBy(xpath ="//tbody//tr")
    private static List<WebElement> transactionRows;

    @FindBy(xpath = "(//tbody//tr//td[1])")
    private static List<WebElement> dateTimeInfo;

    @FindBy(xpath = "(//tbody//tr//td[2])")
    private static List<WebElement> amounts;

    @FindBy(xpath = "//a[@ng-click=\"sortType = 'date'; sortReverse = !sortReverse\"]")
    private static WebElement sortLink;

    @FindBy(id = "start")
    private static WebElement searchInputStart;
    @FindBy(id = "end")
    private static WebElement searchInputEnd;

    @FindBy(xpath = "//button[@ng-show='showDate']")
    private static WebElement resetButton;


    @FindBy(xpath = "//button[@class = 'btn home']")
    private static WebElement homeButton;

    @FindBy(xpath = "//button[@ng-show ='logout']")
    private static WebElement logoutButton;

    public static WebElement getCustomerButton() {
        return customerButton;
    }

    public static WebElement getCustomerDD() {
        return customerDD;
    }

    public static WebElement getLoginButton() {
        return loginButton;
    }

    public static List<WebElement> getVerifyButtons() {
        return verifyButtons;
    }

    public static WebElement getAmountInput() {
        return amountInput;
    }

    public static WebElement getSuccessMessage() {
        return successMessage;
    }

    public static WebElement getWithdrawInput() {
        return withdrawInput;
    }

    public static WebElement getBackButton() {
        return backButton;
    }

    public static List<WebElement> getTransactionRows() {
        return transactionRows;
    }

    public static List<WebElement> getDateTimeInfo() {
        return dateTimeInfo;
    }

    public static List<WebElement> getAmounts() {
        return amounts;
    }

    public static WebElement getSortLink() {
        return sortLink;
    }

    public static WebElement getSearchInputStart() {
        return searchInputStart;
    }

    public static WebElement getSearchInputEnd() {
        return searchInputEnd;
    }

    public static WebElement getResetButton() {
        return resetButton;
    }

    public static WebElement getHomeButton() {
        return homeButton;
    }

    public static WebElement getLogoutButton() {
        return logoutButton;
    }
}
