import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shopping_Cart {

    public static ThreadLocal<String> Shopping_Cart_Item_Name = new ThreadLocal<String>();
    public static ThreadLocal<String> Shopping_Cart_Item_Price = new ThreadLocal<String>();
    public static ThreadLocal<String> Shopping_Cart_Item_Quantity = new ThreadLocal<String>();
    public WebDriver driver = WebDriverManager.getDriver();

    public String Product_Page_Item_Name = Product_Page.get_Product_Page_Item_Name();
    public String Product_Page_Item_Price = Product_Page.get_Product_Page_Item_Name();
    public String Product_Page_Item_Quantity = Product_Page.get_Product_Page_Item_Name();

    public static String get_Shopping_Cart_Item_Name() {
        return Shopping_Cart_Item_Name.get();
    }
    public static void set_Shopping_Cart_Item_Name(String string){
        Shopping_Cart_Item_Name.set(string);
    }
    public static String get_Shopping_Cart_Item_Price() {
        return Shopping_Cart_Item_Price.get();
    }
    public static void set_Shopping_Cart_Item_Price(String string){
        Shopping_Cart_Item_Price.set(string);
    }
    public static String get_Shopping_Cart_Item_Quantity() {
        return Shopping_Cart_Item_Quantity.get();
    }
    public static void set_Shopping_Cart_Item_Quantity(String string){
        Shopping_Cart_Item_Quantity.set(string);
    }

    @Test(priority = 14)
    void Getting_Shopping_Cart_Item_Details(){
        driver = WebDriverManager.getDriver();
        set_Shopping_Cart_Item_Name(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[2]/div[1]/h3/a")).getText());
        String text_to_be_cleaned= driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/div/span/span/span")).getText();
        Pattern pattern = Pattern.compile("[()US\\s]");
        Matcher matcher = pattern.matcher(text_to_be_cleaned);

        // Replace unwanted symbols with an empty string
        set_Shopping_Cart_Item_Price(matcher.replaceAll(""));
        set_Shopping_Cart_Item_Quantity(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[1]/div/div/span/span/span[2]")).getText());
    }
    @Test(priority = 15)
    void Assertion_of_Item_Title(){
        String actualTitle = get_Shopping_Cart_Item_Name();
        String expectedTitle = Product_Page.get_Product_Page_Item_Name();
        Assert.assertEquals(actualTitle,expectedTitle,"Item title is not matched");
    }

    @Test(priority = 16)
    void Assertion_of_Item_Price(){
        String actualPrice = get_Shopping_Cart_Item_Price();
        String expectedPrice = Product_Page.get_Product_Page_Item_Price();
        Assert.assertEquals(actualPrice,expectedPrice,"Item price is not matched");
    }

    @Test(priority = 17)
    void Assertion_of_Item_Quantity(){
        String actualQuantity = get_Shopping_Cart_Item_Quantity();
        String expectedQuantity = Product_Page.get_Product_Page_Item_Quantity();
        Assert.assertEquals(actualQuantity,"Qty "+expectedQuantity,"Item quantity is not matched");
        System.out.println("Shoping Cart Details");
        System.out.println("...............................................................");
        System.out.println("Title:- "+get_Shopping_Cart_Item_Name());
        System.out.println("Price:- "+get_Shopping_Cart_Item_Price());
        System.out.println("Quantity:- "+get_Shopping_Cart_Item_Quantity());
        System.out.println("...............................................................");
    }
    @Test(priority = 18)
    void Go_to_Checkout(){
        WebElement Go_to_Checkout_Button = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div/div[2]/button"));
        Go_to_Checkout_Button.click();
    }

    @Test(priority = 19)
    void Continue_as_guest(){
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(driver,duration);
        WebElement Continue_as_guest_Button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gxo-btn\"]")));
        Continue_as_guest_Button.click();
    }
}
