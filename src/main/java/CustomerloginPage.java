import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerloginPage {
    WebDriver driver;
    By customerbtn=By.xpath("//button[contains(text(),'Customer Login')]");
    By customerselectbtn=By.xpath("//*[@id='userSelect']");
    By customerloginbtn=By.xpath("//button[contains(text(),'Login')]");
    CustomerloginPage(WebDriver driver)
    {
        this.driver=driver;

    }
    public Boolean customerloginbtnisExist()
    {
        WebElement customerloginbutton=driver.findElement(customerloginbtn);
        return (customerloginbutton.isDisplayed());
    }
    public void customerLogin()
    {
        WebElement customerLoginButton=driver.findElement(customerbtn);
        //Assert.assertTrue(customerLoginButton.isDisplayed());
        customerLoginButton.click();
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='userSelect']")));
        WebElement dropDown2 = driver.findElement(customerselectbtn);//creating a web element for CustomerName
        Select selectObject2 = new Select(dropDown2);
        selectObject2.selectByIndex(6);
        WebElement loginButton=driver.findElement(customerloginbtn);
        loginButton.click();
    }
}

