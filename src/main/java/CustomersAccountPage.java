import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomersAccountPage {
    WebDriver driver;
    By depositbtn=By.xpath("//button[contains(text(),'Deposit')]");
    By addmoneybtn=By.xpath("//label[contains(text(),'Amount to be Deposited :')]//following-sibling::input");
    By moneysubmitbtn=By.xpath("//button[@type='submit']");
    By withdrawlbtn=By.xpath("//button[contains(text(),'Withdrawl')]");
    By moneywithdrawbtn=By.xpath("//label[contains(text(),'Amount to be Withdrawn :')]//following-sibling::input");
    By withdrawSubmitbtn=By.xpath("//button[@type='submit']");
    By transactionbtn=By.xpath("//button[contains(text(),'Transactions')]");
    int moneyTodeposit;
    int moneyToWithdrawwl;
    CustomersAccountPage(WebDriver driver)
    {
        this.driver=driver;

    }
    public Boolean depositbtnisExist()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Deposit')]")));
        WebElement depositbutton=driver.findElement(depositbtn);
        return (depositbutton.isDisplayed());
    }
    public Boolean addmoneybtnisExist()
    {
        WebElement addmoneybutton=driver.findElement(addmoneybtn);
        return (addmoneybutton.isDisplayed());
    }
    public Boolean moneysubmitbtnisExist()
    {
        WebElement moneysubmitbutton=driver.findElement(moneysubmitbtn);
        return (moneysubmitbutton.isDisplayed());
    }
    public Boolean withdrawlbtnExist()
    {
        WebElement withdrawlbutton=driver.findElement(withdrawlbtn);
        return (withdrawlbutton.isDisplayed());
    }
    public Boolean moneywithdrawbtn()
    {
        WebElement moneywithdrawbutton=driver.findElement(moneywithdrawbtn);
        return (moneywithdrawbutton.isDisplayed());
    }
    public Boolean withdrawSubmitbtnisExist()
    {
        WebElement withdrawSubmitbutton=driver.findElement(withdrawSubmitbtn);
        return (withdrawSubmitbutton.isDisplayed());
    }
    public Boolean transactionbtnisExist()
    {
        WebElement transactionbutton=driver.findElement(transactionbtn);
        return (transactionbutton.isDisplayed());
    }


    public void customerDepositMoney() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Deposit')]")));
        WebElement depositButton = driver.findElement(depositbtn);
        depositButton.click();//clicking home button
        moneyTodeposit = 10000;
        int prevBalance = 0;
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Amount to be Deposited :')]//following-sibling::input")));
        driver.findElement(addmoneybtn).sendKeys(String.valueOf(moneyTodeposit));//finding the element and sending money to deposit
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(moneysubmitbtn).click();//depositing money in bank by clicking the button
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")));
        String balance = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
        if ((Integer.parseInt(balance)) == moneyTodeposit + prevBalance) {
            System.out.println("money has been successfully added");
        }
        prevBalance= Integer.parseInt(balance);//added extra case for maintaining the coorect balance
    }
    public String gettingactualvalue()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Deposit Successful')]")));
        String actualvalue=driver.findElement(By.xpath("//span[contains(text(),'Deposit Successful')]")).getText();
        return actualvalue;
    }
    public int gettingdepositmoney()
    {
        return moneyTodeposit;
    }
    //String expectedvalue="Deposit Successful";
    //Assert.assertEquals(actualvalue,expectedvalue);//using hard assert for checking whether the both actual and expected are same


    public void customerWithdrawMoney(int money) throws InterruptedException {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Withdrawl')]")));//waiting till the element is found
        WebElement withdrawlButton=driver.findElement(withdrawlbtn);//xpath of withdrawl button is stored in web element
        //Assert.assertTrue(withdrawlButton.isDisplayed());
        withdrawlButton.click();//clicking withdrawbutton
        moneyToWithdrawwl=money;
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Amount to be Withdrawn :')]//following-sibling::input")));
        driver.findElement(moneywithdrawbtn).sendKeys(String.valueOf(moneyToWithdrawwl));
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='submit']")));
        driver.findElement(withdrawSubmitbtn).click();
        String availableBalance=driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
        if((Integer.parseInt(availableBalance)) >= moneyToWithdrawwl)//validating the balance with withdrawl
        {
            System.out.println("successfully withdraw money");
        }
        else
            System.out.println("Transaction Failed. You can not withdraw amount more than the balance.");


    }
    public int gettingwithdrawmoney()
    {
        return moneyToWithdrawwl;
    }
    public int gettingactualwithdrawmoney()
    {
        String  actualwithdrawmoney=driver.findElement(By.xpath("//td[contains(text(),'10000')]")).getText();
        return ((Integer.parseInt(actualwithdrawmoney)));
    }
    //public String gettingStatusofmoney()
    {

    }//


    public void checkingTransaction() throws InterruptedException {
        Thread.sleep(3000);
        WebElement transactionButton=driver.findElement(transactionbtn);
        //Assert.assertTrue(transactionButton.isDisplayed());//Checking Whether the transactionButton is displayed on the site or not.
        transactionButton.click();//clicking transactionButton
        Thread.sleep(2000);
    }

}
