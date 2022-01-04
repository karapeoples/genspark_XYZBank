package test.java;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.pages.CustomerTestPageFactory;
import test.utils.ExecUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static test.utils.DriverUtil.getDriver;


public class CustomerTests extends BaseTest {
    Logger Log = Logger.getLogger(CustomerTests.class);
    WebDriverWait wait;
    Map<String, String> testData;

    @BeforeMethod
    public void setWait() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test(priority = 1)
    public void customerLogin() throws IOException {
        //Working with Customer Name Harry Potter
        testData = ExecUtils.readExcel().get("customerLogin");
        Select customerSelect = new Select(CustomerTestPageFactory.getCustomerDD());
        List<WebElement> chooseCustomer = customerSelect.getOptions();
        for (WebElement ele : chooseCustomer) {
            if (ele.getText().equalsIgnoreCase(testData.get("TestInfo1"))) {
                ele.click();
                Assert.assertTrue(ele.isSelected());
            }
        }

        //Arrive at Customer Transactions Menu
        wait.until(ExpectedConditions.elementToBeClickable(CustomerTestPageFactory.getLoginButton()));
        CustomerTestPageFactory.getLoginButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo2")));
        Assert.assertEquals(String.valueOf(CustomerTestPageFactory.getVerifyButtons().size()), testData.get("TestInfo3"));
    }

    @Test(dependsOnMethods = {"customerLogin"})
    public void depositSection() throws IOException {
        //Deposits
        testData = ExecUtils.readExcel().get("depositSection");
        CustomerTestPageFactory.getVerifyButtons().get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(CustomerTestPageFactory.getAmountInput()));
        CustomerTestPageFactory.getAmountInput().sendKeys(testData.get("TestInfo1"));
        WebElement depositButton = CustomerTestPageFactory.getLoginButton();
        depositButton.click();
        wait.until(ExpectedConditions.visibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        String successMessage = CustomerTestPageFactory.getSuccessMessage().getText();
        Assert.assertEquals(successMessage, testData.get("TestInfo2"));
    }

    @Test(dependsOnMethods = {"customerLogin"})
    public void withdrawalSection() throws IOException {
        //Withdrawal No Deposits
        testData = ExecUtils.readExcel().get("withdrawalSection");
        CustomerTestPageFactory.getVerifyButtons().get(2).click();
        wait.until(ExpectedConditions.invisibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        wait.until(ExpectedConditions.elementToBeClickable(CustomerTestPageFactory.getWithdrawInput()));
        CustomerTestPageFactory.getWithdrawInput().sendKeys(testData.get("TestInfo1"));
        WebElement withdrawButton = CustomerTestPageFactory.getLoginButton();
        withdrawButton.click();
        wait.until(ExpectedConditions.visibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        String successMessage = CustomerTestPageFactory.getSuccessMessage().getText();
        Assert.assertEquals(successMessage, testData.get("TestInfo2"));

        //Withdrawals with Deposits
        CustomerTestPageFactory.getVerifyButtons().get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(CustomerTestPageFactory.getAmountInput()));
        CustomerTestPageFactory.getAmountInput().sendKeys(testData.get("TestInfo3"));
        WebElement depositButton = CustomerTestPageFactory.getLoginButton();
        depositButton.click();
        CustomerTestPageFactory.getAmountInput().sendKeys(testData.get("TestInfo4"));
        depositButton.click();
        CustomerTestPageFactory.getAmountInput().sendKeys(testData.get("TestInfo5"));
        depositButton.click();
        wait.until(ExpectedConditions.visibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        CustomerTestPageFactory.getVerifyButtons().get(2).click();
        wait.until(ExpectedConditions.invisibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        wait.until(ExpectedConditions.elementToBeClickable(CustomerTestPageFactory.getWithdrawInput()));
        CustomerTestPageFactory.getWithdrawInput().sendKeys(testData.get("TestInfo6"));
        withdrawButton = CustomerTestPageFactory.getLoginButton();
        withdrawButton.click();
        wait.until(ExpectedConditions.visibilityOf(CustomerTestPageFactory.getSuccessMessage()));
        successMessage = CustomerTestPageFactory.getSuccessMessage().getText();
        Assert.assertEquals(successMessage, testData.get("TestInfo7"));
    }


    @Test(dependsOnMethods = {"withdrawalSection"})
    public void transactionSection() throws IOException {
        //Transactions
        testData = ExecUtils.readExcel().get("transactionSection");
        CustomerTestPageFactory.getVerifyButtons().get(0).click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        CustomerTestPageFactory.getBackButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo2")));
        Assert.assertFalse(driver.getCurrentUrl().contains(testData.get("TestInfo1")));
        CustomerTestPageFactory.getVerifyButtons().get(0).click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        Assert.assertFalse(driver.getCurrentUrl().contains(testData.get("TestInfo2")));


        ArrayList<String> staticAmounts = new ArrayList<>();
        staticAmounts.add(testData.get("TestInfo3"));
        staticAmounts.add(testData.get("TestInfo4"));
        staticAmounts.add(testData.get("TestInfo5"));
        staticAmounts.add(testData.get("TestInfo6"));
        staticAmounts.add(testData.get("TestInfo7"));


        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("(//tbody//tr//td[2])"), Integer.valueOf(testData.get("TestInfo8"))));
        ArrayList<String> tableAmounts = new ArrayList<>();
        for (WebElement ele : CustomerTestPageFactory.getAmounts()) {
            tableAmounts.add(ele.getText());
        }

        Assert.assertEquals(staticAmounts, tableAmounts);

        ArrayList<String> dates = new ArrayList<>();


        for (WebElement ele : CustomerTestPageFactory.getDateTimeInfo()) {
            dates.add(ele.getText());
        }
        CustomerTestPageFactory.getSortLink().click();
        ArrayList<String> reverseOrder = new ArrayList<>();
        for (WebElement ele : CustomerTestPageFactory.getDateTimeInfo()) {
            reverseOrder.add(ele.getText());
        }
        dates.sort(Comparator.reverseOrder());
        Assert.assertEquals(reverseOrder, dates);


        CustomerTestPageFactory.getResetButton().click();
        Assert.assertEquals(String.valueOf(CustomerTestPageFactory.getTransactionRows().size()), testData.get("TestInfo9"));

        //Search Inputs are Bugged
    }


    @Test
    public void navBar() throws IOException {
        //NavBar
        testData = ExecUtils.readExcel().get("navBar");
        wait.until(ExpectedConditions.elementToBeClickable( new CustomerTestPageFactory(getDriver()).getCustomerButton()));
        CustomerTestPageFactory.getCustomerButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        Select customerSelect = new Select(CustomerTestPageFactory.getCustomerDD());
        List<WebElement> chooseCustomer = customerSelect.getOptions();
        for (WebElement ele : chooseCustomer) {
            if (ele.getText().equalsIgnoreCase(testData.get("TestInfo2"))) {
                ele.click();
            }
        }
        CustomerTestPageFactory.getLoginButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo3")));
        CustomerTestPageFactory.getHomeButton().click();
        Assert.assertTrue(driver.getCurrentUrl().contains(testData.get("TestInfo4")));
        CustomerTestPageFactory.getCustomerButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo5")));
        chooseCustomer = customerSelect.getOptions();
        for (WebElement ele : chooseCustomer) {
            if (ele.getText().equalsIgnoreCase(testData.get("TestInfo6"))) {
                ele.click();
            }
        }
        CustomerTestPageFactory.getLoginButton().click();
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo7")));
        CustomerTestPageFactory.getLogoutButton().click();
        Assert.assertTrue(driver.getCurrentUrl().contains(testData.get("TestInfo8")));

    }

}
