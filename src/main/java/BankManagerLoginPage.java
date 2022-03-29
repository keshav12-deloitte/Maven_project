import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;

public class BankManagerLoginPage {
    WebDriver driver;
    By addCustomerButton=By.xpath("//button[contains(text(),'Add Customer')]");
    By firstNameInput=By.xpath("//label[contains(text(),'First Name')]//following-sibling::input");
    By lastNameinput=By.xpath("//*[.='Last Name :']//following-sibling::input");
    By postcodeInput=By.xpath("//label[contains(text(),'Post Code :')]//following-sibling::input");
    By addDetails=By.xpath("//button[@class='btn btn-default']");
    By openAccount=By.xpath("//button[contains(text(),'Open Account')]");
    By customerName=By.xpath("//label[contains(text(),'Customer :')]//following-sibling::select");
    By currencyButton=By.xpath("//label[contains(text(),'Currency :')]//following-sibling::select");
    By processButton=By.xpath("//button[contains(text(),'Process')]");
    By homebtn=By.xpath("//button[contains(text(),'Home')]");
    BankManagerLoginPage(WebDriver driver)
    {
        this.driver=driver;

    }
    public Boolean addCustomerButtonisExist()
    {
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Customer')]")));
        WebElement addcustomerbtn=driver.findElement(addCustomerButton);
        return (addcustomerbtn.isDisplayed());
    }
    public Boolean openAccountButtonisExist()
    {
        WebElement addcustomerbtn=driver.findElement(openAccount);
        return (addcustomerbtn.isDisplayed());
    }
    public Boolean homebtnButtonisExist()
    {
        WebElement home=driver.findElement(homebtn);
        return (home.isDisplayed());
    }

    public void clickAddCustomer() throws InterruptedException {
        Thread.sleep(2000);
        WebElement addCustomer=driver.findElement(addCustomerButton);
        //Assert.assertTrue(addCustomer.isDisplayed());//Checking Whether the Add customer button is displayed on the site or not.
        addCustomer.click();//clicking on add customer
    }
    public void adddetails() throws InterruptedException, IOException {
        String excelFilePath="C:\\Users\\vuchander\\Desktop\\excelsheets\\customerData.xlsx";//path of the excel File
        FileInputStream fis=new FileInputStream(excelFilePath);//reading the file by FileInputStream
        XSSFWorkbook workbook=new XSSFWorkbook(fis);//opening workbook
        XSSFSheet sheet=workbook.getSheet("Sheet1");//opening sheet in workbook
        XSSFRow row= null;//initializing rows
        XSSFCell cell=null;//initializing columns
        String firstName = null;//reading firstname from excel and storing in String variable
        String lastName=null;//reading lastname from excel and storing in String variable

        for(int i=1;i<=sheet.getLastRowNum();i++)//reading from row i
        {
            row=sheet.getRow(i);
            for(int j=0;j<row.getLastCellNum();j++)//reading from column j
            {
                cell=row.getCell(j);
                if(j==0)
                {
                    firstName=cell.getStringCellValue();
                }
                if(j==1)
                {
                    lastName=cell.getStringCellValue();

                }
                if(j==2)
                {
                    int postal=(int)cell.getNumericCellValue();//reading the postalcode from excel sheet and storing it as a int datatype

                }
            }
        }

        try{

            new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'First Name')]//following-sibling::input")));
            Thread.sleep(5000);
            driver.findElement(firstNameInput).sendKeys(firstName);//sending firstname from excel sheet to first name input field
            Thread.sleep(1000);
            driver.findElement(lastNameinput).sendKeys(lastName);//sending lastName from excel sheet to lastName input field
            Thread.sleep(1000);
            driver.findElement(postcodeInput).sendKeys(String.valueOf(50020));////sending postalcode from excel sheet to postalcode input field
            Thread.sleep(1000);
            driver.findElement(addDetails).click();//clicking on add customer
            driver.switchTo().alert().accept();//clicking on alertbox

            Thread.sleep(1000);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("exception");
        }
        Thread.sleep(2000);


    }
    public void openAccount() throws InterruptedException {

        WebElement openAccountbutton=driver.findElement(openAccount);
        openAccountbutton.click();//cliking on open account
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Customer :')]//following-sibling::select")));
        WebElement dropDown = driver.findElement(customerName);//creating a web element for CustomerName
        Select selectObject = new Select(dropDown);
        selectObject.selectByIndex(6);
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(),'Currency :')]//following-sibling::select")));
        WebElement dropDown1 = driver.findElement(currencyButton);//creating a web element for CustomerName
        Select selectObject1 = new Select(dropDown1);
        selectObject1.selectByIndex(3);
        WebElement clickProcess= driver.findElement(processButton);
        clickProcess.click();
        driver.switchTo().alert().accept();
    }
    public void home()
    {
        WebElement homeButton=driver.findElement(homebtn);
        homeButton.click();//clicking home button
    }

}

