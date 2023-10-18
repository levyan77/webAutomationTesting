import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {
    @Test //Tag untuk running script dibawah ini
    public void  open_browser(){
        WebDriver driver;
        String baseUrl = "http://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);

        driver.close();
    }

    @Test
    public void  get_title(){
        WebDriver driver;
        String baseUrl = "http://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();

        //apply chrome driver setup
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        String title = driver.getTitle();
        System.out.println(title);

        WebElement element = driver.findElement(By.id("user-name"));
//        WebElement element2 = driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        element.click();
        element.sendKeys("standart_user");
        element.getText();

//        element2.isDisplayed();
//        element2.click();

//        driver.findElement(By.xpath()).isDisplayed();
//        driver.findElement(By.cssSelector()).isDisplayed();

        driver.close();
    }
}
