package test.java;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import test.pages.ManagerTestPageFactory;
import test.utils.ClickUtil;
import test.utils.ExecUtils;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static test.utils.DriverUtil.getDriver;


public class BankManagerTests extends BaseTest {
    Logger Log = Logger.getLogger(BankManagerTests.class);
    WebDriverWait wait;
    Map<String, String> testData;
    @BeforeMethod
    public void setWait() {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void login() throws IOException {
        Log.info("Login Process Started");
        testData = ExecUtils.readExcel().get("login");
        wait.until(ExpectedConditions.elementToBeClickable(new ManagerTestPageFactory(getDriver()).getManagerButton()));
        ClickUtil.clickEle(ManagerTestPageFactory.getManagerButton());
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        Assert.assertTrue(driver.getCurrentUrl().contains(testData.get("TestInfo1")));
    }

    @Test(dependsOnMethods = {"login"})
    public void addCustomer() throws IOException {
        //Add Customer
        testData = ExecUtils.readExcel().get("addCustomer");
        wait.until(ExpectedConditions.elementToBeClickable(ManagerTestPageFactory.getCustomerButton()));
        ClickUtil.clickEle(ManagerTestPageFactory.getCustomerButton());
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        List<WebElement> inputs = ManagerTestPageFactory.getInputs();
        //Arrived to Section
        Assert.assertEquals(String.valueOf(inputs.size()), testData.get("TestInfo2"));
        Assert.assertTrue(ManagerTestPageFactory.getSubmitButton().isDisplayed());

        //Inputs // No assertions available on these inputs individually
        inputs.get(0).sendKeys(testData.get("TestInfo3"));
        inputs.get(1).sendKeys(testData.get("TestInfo4"));
        inputs.get(2).sendKeys(testData.get("TestInfo5"));
        ClickUtil.clickEle(ManagerTestPageFactory.getSubmitButton());
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains(testData.get("TestInfo6")));
        driver.switchTo().alert().dismiss();
        wait.until(ExpectedConditions.elementToBeClickable(ManagerTestPageFactory.getOpenAccountsButton()));
    }

    @Test(dependsOnMethods = {"addCustomer"})
    public void openAccounts() throws IOException {
        //Open Accounts
        testData = ExecUtils.readExcel().get("openAccounts");
        List<WebElement> inputs;
        ClickUtil.clickEle(ManagerTestPageFactory.getOpenAccountsButton());
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        inputs = ManagerTestPageFactory.getSelects();
        Assert.assertEquals(String.valueOf(inputs.size()), testData.get("TestInfo2"));
        Assert.assertTrue(ManagerTestPageFactory.getSubmitButton().isDisplayed());

        //Dropdown 1
        Select customerMenu = new Select(inputs.get(0));
        List<WebElement> text = customerMenu.getOptions();

        for (WebElement ele : text) {
            if (ele.getText().equalsIgnoreCase(testData.get("TestInfo3"))) {
                customerMenu.selectByVisibleText(testData.get("TestInfo3"));
                Assert.assertTrue(ele.isSelected());
            }

        }
        Select moneyType = new Select(inputs.get(1));
        List<WebElement> type = moneyType.getOptions();

        for (WebElement ele : type) {
            if (ele.getText().equalsIgnoreCase(testData.get("TestInfo4"))) {
                moneyType.selectByVisibleText(testData.get("TestInfo4"));
                Assert.assertTrue(ele.isSelected());
            }
        }

        ClickUtil.clickEle(ManagerTestPageFactory.getSubmitButton());
        wait.until(ExpectedConditions.alertIsPresent());
        String info = driver.switchTo().alert().getText();
        Assert.assertTrue(info.contains(testData.get("TestInfo5")));
        driver.switchTo().alert().dismiss();
        wait.until(ExpectedConditions.elementToBeClickable(ManagerTestPageFactory.getOpenCustomersButton()));
    }

    @Test(dependsOnMethods = {"openAccounts"})
    public void searchSection() throws IOException {
        //Customers Search Section
        testData = ExecUtils.readExcel().get("searchSection");
        List<WebElement> inputs;
        ClickUtil.clickEle(ManagerTestPageFactory.getOpenCustomersButton());
        wait.until(ExpectedConditions.urlContains(testData.get("TestInfo1")));
        inputs = ManagerTestPageFactory.getInputs();
        Assert.assertEquals(String.valueOf(inputs.size()), testData.get("TestInfo2"));
        Assert.assertTrue(ManagerTestPageFactory.getTable().isDisplayed());
        List<WebElement> rows = ManagerTestPageFactory.getTableRows();
        List<WebElement> btns = ManagerTestPageFactory.getButtons();
        Assert.assertEquals(rows.size(), btns.size());

        //Customers Search Section
        inputs.get(0).sendKeys(testData.get("TestInfo3"));
        Assert.assertEquals(ManagerTestPageFactory.getNameField().getText(), testData.get("TestInfo3"));
        inputs.get(0).clear();
        int startList = rows.size();
        inputs.get(0).sendKeys(testData.get("TestInfo4"));
        ClickUtil.clickEle(ManagerTestPageFactory.getDeleteButton());
        inputs.get(0).clear();
        rows = ManagerTestPageFactory.getTableRows();
        Assert.assertEquals(startList - 1, rows.size());

        //Customer Search Sort First Name
        ClickUtil.clickEle(ManagerTestPageFactory.getFirstNameSort());
        List<WebElement> firstNames = ManagerTestPageFactory.getFirstNameFields();
        ArrayList<String> firstNamesArr = new ArrayList<>();
        for (WebElement el : firstNames) {
            firstNamesArr.add(el.getText());
        }

        ArrayList<String> orderedFirstNames = new ArrayList<>();
        orderedFirstNames.add(testData.get("TestInfo5"));
        orderedFirstNames.add(testData.get("TestInfo6"));
        orderedFirstNames.add(testData.get("TestInfo7"));
        orderedFirstNames.add(testData.get("TestInfo8"));
        orderedFirstNames.add(testData.get("TestInfo9").trim());
        orderedFirstNames.sort(Comparator.reverseOrder());
        Assert.assertEquals(firstNamesArr, orderedFirstNames);

        ClickUtil.clickEle(ManagerTestPageFactory.getFirstNameSort());
        Assert.assertEquals(firstNamesArr,orderedFirstNames);


        //Customer Search Last Names
        ClickUtil.clickEle(ManagerTestPageFactory.getLastNameSort());
        List<WebElement> lastNames = ManagerTestPageFactory.getLastNameFields();
        ArrayList<String> lastNamesArr = new ArrayList<>();
        for (WebElement el : lastNames) {
            lastNamesArr.add(el.getText());
        }

        ArrayList<String> orderedLastNames = new ArrayList<>();
        orderedLastNames.add(testData.get("TestInfo10"));
        orderedLastNames.add(testData.get("TestInfo11"));
        orderedLastNames.add(testData.get("TestInfo12"));
        orderedLastNames.add(testData.get("TestInfo13"));
        orderedLastNames.add(testData.get("TestInfo14").trim());
        orderedLastNames.sort(Comparator.reverseOrder());
        Assert.assertEquals(lastNamesArr, orderedLastNames);

        ClickUtil.clickEle(ManagerTestPageFactory.getLastNameSort());
        Assert.assertEquals(lastNamesArr, orderedLastNames);

        //Post Code Sort
        ClickUtil.clickEle(ManagerTestPageFactory.getPostSort());
        List<WebElement> postCodes = ManagerTestPageFactory.getPostCodeFields();
        ArrayList<String> postCodesArr = new ArrayList<>();
        for (WebElement el : postCodes) {
            postCodesArr.add(el.getText());
        }

        ArrayList<String> orderedPostCodes = new ArrayList<>();
        orderedPostCodes.add(testData.get("TestInfo15"));
        orderedPostCodes.add(testData.get("TestInfo16"));
        orderedPostCodes.add(testData.get("TestInfo17"));
        orderedPostCodes.add(testData.get("TestInfo18"));
        orderedPostCodes.add(testData.get("TestInfo19"));
        orderedPostCodes.sort(Comparator.reverseOrder());
        Assert.assertEquals(postCodesArr, orderedPostCodes);

        ClickUtil.clickEle(ManagerTestPageFactory.getPostSort());
        Assert.assertEquals(postCodesArr, orderedPostCodes);

    }
}
