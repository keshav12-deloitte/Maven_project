import com.sun.jdi.connect.Connector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Transactionpage {
    static WebDriver driver;
    Transactionpage(WebDriver driver)
    {
        this.driver=driver;

    }

    public List getaccountstatus()
    {
        List<WebElement> amount=driver.findElements(By.xpath("//table[@class='table table-bordered table-striped']//tr//td[2]"));
        return amount;
    }
    public List getTransactionType()
    {
        List<WebElement> transactiontype=driver.findElements(By.xpath(" //table[@class='table table-bordered table-striped']//tr//td[3]"));
        return transactiontype;
    }


}
