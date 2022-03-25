package Mini_assignment_4;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

    public class Mymain {
        WebDriver driver;

        @BeforeClass
        public void setup()
        {
            System.setProperty("webdriver.chrome.driver","C:\\selenium jars and drivers\\drivers\\chrome drivers\\chromedriver.exe");
            driver=new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

        }
        @Test(priority = 1)
        public  void login() throws FileNotFoundException, InterruptedException {
            loginPage obj = new loginPage(driver);
            FileInputStream file = new FileInputStream("C:\\Users\\vuchander\\Desktop\\testingdata.xlsx");
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
            public void checkCont() throws InterruptedException {
            loginPage obj2 = new loginPage(driver);
            obj2.cont_shopping();
            Thread.sleep(3000);
            obj2.clickCart();
            Thread.sleep(3000);
            obj2.click_checkout();
            Thread.sleep(3000);

        }
        @Test (priority = 4)
                public void checkingEnable()
        {
            loginPage obj4=new loginPage(driver);

            obj4.verifying_enable();
            driver.close();
        }

        @Test (priority = 5)
        public void verifyingremove()
        {
            loginPage obj5=new loginPage(driver);
            obj5.check_remove();
        }






}
