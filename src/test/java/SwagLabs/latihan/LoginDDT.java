package SwagLabs;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    //login menggunakan fitur Data Driven Test (DDT)
    @Test
    public void login_ddt(){
        WebDriver driver;
        String baseUrl = "http://www.saucedemo.com/";

        WebDriverManager.chromedriver().setup();
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        String csvDir = System.getProperty("user.dir")+"/src/test/data/test-data.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))){
            String[] nextLine;
            while((nextLine = reader.readNext()) != null){
                String username = nextLine[0];
                String password = nextLine[1];
                String status = nextLine[2];

                driver = new ChromeDriver(opt);
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.get(baseUrl);

                //pengisian form
                driver.findElement(By.id("user-name")).sendKeys(username);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

                //assertion
                if (status.equals("success")){
                    String namaWebsite = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[1]/div[2]/div")).getText();
                    Assert.assertEquals(namaWebsite,"Swag Labs");
                } else {
                    String errorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
                    Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
                }
               driver.close();
            }
        } catch (IOException e){
            throw new RuntimeException();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
