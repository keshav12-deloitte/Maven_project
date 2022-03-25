package Mini_assignment_4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class loginPage {
    WebDriver driver;
    By user_name = By.id("user-name");
    By password = By.id("password");
    By button = By.xpath("//*[@id='login-button']");
    By carticon = By.xpath("//*[@class='shopping_cart_link']");
    By continue_shopping = By.xpath("//*[@id='continue-shopping']");
    By checkout=By.xpath("//*[@id='checkout']");
    By addTocart=By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']");
    By remove=By.xpath("//*[@id='remove-sauce-labs-backpack']");

    loginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUser_name(String name) {
        driver.findElement(user_name).sendKeys(name);
    }

    public void setPassword(String password1) {
        driver.findElement(password).sendKeys(password1);
    }

    public void clicklogin() {
        WebElement login = driver.findElement(button);
        login.click();
    }

    public void clickCart() {
        WebElement cart_icon = driver.findElement(By.xpath("//*[@class='shopping_cart_link']"));
        cart_icon.click();
    }
    public void cont_shopping(){
        WebElement continue_icon=driver.findElement(By.xpath("//*[@id='continue-shopping']"));
        continue_icon.click();
    }
    public void click_checkout()
    {
        WebElement checkout_icon=driver.findElement(By.xpath("//*[@id='checkout']"));
        checkout_icon.click();
    }
    public void verifying_enable()
    {
        WebElement addToCart=driver.findElement(By.xpath("//*[@id='add-to-cart-sauce-labs-backpack']"));
        addToCart.isDisplayed();
        addToCart.isEnabled();
    }
    public void check_remove()
    {
        WebElement remove_icon=driver.findElement(By.xpath("//*[@id='remove-sauce-labs-backpack']"));
        remove_icon.click();
        remove_icon.isEnabled();
        remove_icon.click();
    }
}


