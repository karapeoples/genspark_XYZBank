package test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ManagerTestPageFactory {

    WebDriver driver;


    public ManagerTestPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    private static WebElement managerButton;

    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    private static WebElement customerButton;

    @FindBy(tagName = "input")
    private static List<WebElement> inputs;

    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement submitButton;

    @FindBy(xpath = "//button[@ng-class ='btnClass2']")
    private static WebElement openAccountsButton;

    @FindBy(xpath = "//button[@ng-class ='btnClass3']")
    private static WebElement openCustomersButton;

    @FindBy(tagName = "tbody")
    private static WebElement table;

    @FindBy(xpath = "//tbody//tr[@class = 'ng-scope']")
    private static List<WebElement> tableRows;

    @FindBy(tagName = "select")
    private static List<WebElement> selects;

    @FindBy(xpath = "//button[@ng-click = 'deleteCust(cust)']")
    private static List<WebElement> buttons;

    @FindBy(xpath = "(//tbody//tr[@class = 'ng-scope'][1])//td[1]")
    private static WebElement nameField;

    @FindBy(xpath = "(//tbody//tr[@class = 'ng-scope'][1])//td[5]//button")
    private static WebElement deleteButton;

    @FindBy(xpath = "(//tbody//tr[@class = 'ng-scope']//td[1])")
    private static List<WebElement> firstNameFields;

    @FindBy(xpath = "(//thead//tr//td[1])//a")
    private static WebElement firstNameSort;

    @FindBy(xpath = "(//tbody//tr[@class = 'ng-scope']//td[2])")
    private static List<WebElement> lastNameFields;

    @FindBy(xpath = "(//thead//tr//td[2])//a")
    private static WebElement lastNameSort;

    @FindBy(xpath = "(//tbody//tr[@class = 'ng-scope']//td[3])")
    private static List<WebElement> postCodeFields;

    @FindBy(xpath = "(//thead//tr//td[3])//a")
    private static WebElement postSort;

    public static WebElement getManagerButton() {
        return managerButton;
    }

    public static WebElement getCustomerButton() {
        return customerButton;
    }

    public static List<WebElement> getInputs() {
        return inputs;
    }

    public static WebElement getSubmitButton() {
        return submitButton;
    }

    public static WebElement getOpenAccountsButton() {
        return openAccountsButton;
    }

    public static WebElement getOpenCustomersButton() {
        return openCustomersButton;
    }

    public static WebElement getTable() {
        return table;
    }

    public static List<WebElement> getTableRows() {
        return tableRows;
    }

    public static List<WebElement> getSelects() {
        return selects;
    }

    public static List<WebElement> getButtons() {
        return buttons;
    }

    public static WebElement getNameField() {
        return nameField;
    }

    public static WebElement getDeleteButton() {
        return deleteButton;
    }

    public static List<WebElement> getFirstNameFields() {
        return firstNameFields;
    }

    public static WebElement getFirstNameSort() {
        return firstNameSort;
    }

    public static List<WebElement> getLastNameFields() {
        return lastNameFields;
    }

    public static WebElement getLastNameSort() {
        return lastNameSort;
    }

    public static List<WebElement> getPostCodeFields() {
        return postCodeFields;
    }

    public static WebElement getPostSort() {
        return postSort;
    }

}

