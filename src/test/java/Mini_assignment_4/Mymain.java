package Mini_assignment_4;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

    public class Mymain {
        WebDriver driver;
        static ExtentReports extent = new ExtentReports();
        static ExtentSparkReporter spark = new ExtentSparkReporter("Miniassignment5extent report.html");
        public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

            TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            File DestFile = new File(fileWithPath);

            FileUtils.copyFile(SrcFile, DestFile);
        }
        @BeforeClass
        public void setup() throws IOException {
            System.setProperty("webdriver.chrome.driver","C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            File ds = new File(System.getProperty("user.dir") + "/miniAssignment5" + "/TestingUrl.jpg");
            FileUtils.copyFile(src, ds);

        }
        @Test(priority = 1)
        public  void login() throws FileNotFoundException, InterruptedException {
            loginPage obj = new loginPage(driver);
            FileInputStream file = new FileInputStream("C:\\Users\\vuchander\\Desktop\\excelsheets\\testingdata.xlsx");
            XSSFWorkbook workbook = null;
            try {
                workbook = new XSSFWorkbook(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int noOfRows = sheet.getLastRowNum();
            System.out.println("No of recordds are " + noOfRows);
            for (int row = 1; row <= noOfRows - 1; row++) {
                XSSFRow current_row = sheet.getRow(row);
                String username = current_row.getCell(0).getStringCellValue();
                String password = current_row.getCell(1).getStringCellValue();
                driver.findElement(By.id("user-name")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);

            }
            Thread.sleep(2000);
            obj.clicklogin();
        }
        @Test (priority = 2)
            public void checkClickCart() throws InterruptedException {
                loginPage obj1 = new loginPage(driver);
                obj1.clickCart();
                Thread.sleep(3000);
            }

        @Test(priority = 3)
        public void checkCont() throws InterruptedException, IOException {
            loginPage obj2 = new loginPage(driver);
            obj2.cont_shopping();
            Thread.sleep(3000);
            obj2.clickCart();
            Thread.sleep(3000);
            obj2.click_checkout();
            Thread.sleep(3000);
            String excelFilePath="C:\\Users\\vuchander\\Desktop\\excelsheets\\checkout.xlsx";//path of the excel File
            FileInputStream fis=new FileInputStream(excelFilePath);//reading the file by FileInputStream
            XSSFWorkbook workbook=new XSSFWorkbook(fis);//opening workbook
            XSSFSheet sheet=workbook.getSheet("Sheet1");//opening sheet in workbook
            XSSFRow row= null;//initializing rows
            XSSFCell cell=null;//initializing columns
            String firstName = null;//reading firstname from excel and storing in variable
            String lastName=null;//reading lastname from excel and storing in variable


            for(int i=1;i<=sheet.getLastRowNum();i++)//reading from row i
            {
                row=sheet.getRow(i);
                for(int j=0;j<row.getLastCellNum();j++)//reading from column j
                {
                    cell=row.getCell(j);
                    if(j==0)
                    {
                        firstName=cell.getStringCellValue();
                        System.out.println(firstName);
                    }
                    if(j==1)
                    {
                        lastName=cell.getStringCellValue();
                        System.out.println(lastName);
                    }
                    if(j==2)
                    {
                        int postal=(int)cell.getNumericCellValue();
                        System.out.println(postal);
                    }
                }
            }

            try{

                new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
                Thread.sleep(5000);
                driver.findElement(By.id("first-name")).sendKeys(firstName);//sending firstname from excel sheet to first name input field
                Thread.sleep(1000);
                driver.findElement(By.id("last-name")).sendKeys(lastName);//sending lastName from excel sheet to lastName input field
                Thread.sleep(1000);
                driver.findElement(By.id("postal-code")).sendKeys(String.valueOf(50020));////sending postalcode from excel sheet to postalcode input field
                Thread.sleep(1000);

                Thread.sleep(1000);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("exception");
            }
            Thread.sleep(2000);





        }


        @Test (priority = 5)
        public void continuebtn()
        {
            loginPage obj4=new loginPage(driver);
            obj4.clickContinue();
        }
        @AfterTest
        public void afterTest() {
            extent.flush();
        }






}
