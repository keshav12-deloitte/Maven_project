import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class Parallelexecution2 {
    static WebDriver driver;
    XYZHomePage home_page;
    BankManagerLoginPage manager_page;
    CustomerloginPage customer_login;
    CustomersAccountPage customer_account;
    static ExtentReports extent = new ExtentReports();
    static ExtentSparkReporter spark = new ExtentSparkReporter("ParallelExecution2.html");
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        File DestFile = new File(fileWithPath);

        FileUtils.copyFile(SrcFile, DestFile);
    }
    @BeforeTest
    public static void setup() throws IOException, InterruptedException {
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest("Launch Browser and Website");
        test.log(Status.PASS, "Successfully launched website");
        test.pass("Successfully launched website");
        System.setProperty("webdriver.chrome.driver", "C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
        driver = new ChromeDriver(); //launch browser
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");//open Url
        Thread.sleep(2000);
        driver.manage().window().maximize();// maximizing Window
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File ds = new File(System.getProperty("user.dir") + "/TestFailureScreenhot" + "/TestingUrl.jpg");
        FileUtils.copyFile(src, ds);
    }
    @Test(priority = 1)
    public void verifyHomepage() throws Exception {
        home_page = new XYZHomePage(driver);
        Assert.assertTrue(home_page.bankManagerButtonIsExist());
        Assert.assertTrue(home_page.homebtnIsExist());
        Assert.assertTrue(home_page.customerbtnIsExist());
        ExtentTest test = extent.createTest("Verify Home Page");
        test.pass("All the web Elements are available on home page");
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\HomePageverify.jpg") ;
    }


    @Test(priority = 2)
    public void verifyBankManagerloginPage() throws Exception {
        home_page.clickBankManagerLogin();
        ExtentTest test = extent.createTest("Verify Bank Manager Login");
        test.pass("Bank manager login Successfully");
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\bankmanagerlogin.jpg") ;
        manager_page = new BankManagerLoginPage(driver);
        Assert.assertTrue(manager_page.addCustomerButtonisExist());
        Assert.assertTrue(manager_page.openAccountButtonisExist());
        Assert.assertTrue(manager_page.homebtnButtonisExist());
    }
    @Test(priority = 3)
    public void BankManagerAddingCustomer() throws Exception{
        manager_page.clickAddCustomer();
        manager_page.adddetails();
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\addingCustomer.jpg");
        Thread.sleep(3000);
        manager_page.home();
        ExtentTest test = extent.createTest("Verifying Bank Manager can Add Customers");
        test.pass("Customers Added successfully");
    }
    @Test(priority = 4)
    public void BankManageropeningAccount() throws Exception {
        ExtentTest test = extent.createTest("Verifying Bank Manager Can Open Account for Existing Customer");
        test.pass("Successfully created account for existing user");
        manager_page = new BankManagerLoginPage(driver);
        Thread.sleep(2000);
        home_page.clickBankManagerLogin();
        Thread.sleep(2000);
        manager_page.openAccount();
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\openAccount.jpg");
        Thread.sleep(1000);
        manager_page.home();
        Thread.sleep(1000);
    }
    @Test(priority = 5)
    public void verifyCustomerLoginPage()
    {
        customer_login = new CustomerloginPage(driver);
        Assert.assertTrue(home_page.customerbtnIsExist());
        Assert.assertTrue(customer_login.customerloginbtnisExist());
        customer_login.customerLogin();
    }
    @Test(priority = 6)
    public void verifyCustomerAccountPage()
    {
        customer_account=new CustomersAccountPage(driver);
        Assert.assertTrue(customer_account.depositbtnisExist());
        Assert.assertTrue(customer_account.withdrawlbtnExist());
        Assert.assertTrue(customer_account. transactionbtnisExist());
        //
        //Assert.assertTrue(customer_account.moneysubmitbtnisExist());
        //Assert.assertTrue(customer_account.withdrawSubmitbtnisExist());

        //Assert.assertTrue(customer_account.moneywithdrawbtn());
    }
    @Test(priority = 7)
    public void CustomeraddingMoneyToaccount() throws Exception {
        customer_account=new CustomersAccountPage(driver);
        customer_account.customerDepositMoney();
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\customerDepositMoney.jpg");
        ExtentTest test = extent.createTest("verifying the cutomers can add money into their account");
        test.info("Customer deposited money Successfully");
        Thread.sleep(2000);
        String expectedvalue="Deposit Successful";
        String actualvalue=customer_account.gettingactualvalue();
        Assert.assertEquals(actualvalue,expectedvalue);
    }
    @Test(priority = 8)
    public void withDrawMoney() throws Exception {
        ExtentTest test = extent.createTest("verifying the cutomers can withdrawmoney from their account");
        customer_account.customerWithdrawMoney(15000);
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\customerWithdrawMoney.jpg");
        test.info("customer withdrawed money successfully");
        Thread.sleep(2000);
    }
    @Test(priority = 9)
    public void verifyTransaction() throws Exception{
        ExtentTest test = extent.createTest("verifying the cutomers transation history");
        customer_account.checkingTransaction();
        takeSnapShot(driver, "C:\\Users\\vuchander\\Maven_project\\TestFailureScreenhot\\checkingTransaction.jpg");
        test.info("customer Transactions are stored  successfully");
        Thread.sleep(2000);
        test.pass("transactions are updated Successfully");
    }

    @AfterTest
    public void afterTest() {
        extent.flush();
    }

}

