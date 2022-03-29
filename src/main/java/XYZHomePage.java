import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XYZHomePage {
    WebDriver driver;
    By homebtn=By.xpath("//button[contains(text(),'Home')]");
    By bankManagerButton=By.xpath("//button[contains(text(),'Bank Manager Login')]");
    By customerbtn=By.xpath("//button[contains(text(),'Customer Login')]");

    XYZHomePage(WebDriver driver)
    {
        this.driver=driver;

    }
    public Boolean bankManagerButtonIsExist()
    {
        WebElement managerButton=driver.findElement(bankManagerButton);
        return (managerButton.isDisplayed());
    }
    public Boolean homebtnIsExist()
    {
        WebElement homeButton=driver.findElement(homebtn);
        return (homeButton.isDisplayed());
    }
    public Boolean customerbtnIsExist()
    {
        WebElement customerButton=driver.findElement(customerbtn);
        return (customerButton.isDisplayed());
    }
    public void clickBankManagerLogin() throws InterruptedException {

        WebElement managerButton=driver.findElement(bankManagerButton);
        managerButton.click();//clicking Bank Manager Button
    }

}
