package SwagLabs.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;


import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("Halaman Login Swag Labs")
    public void Halaman_Login_Swag_Labs(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        //assertion
        String loginPageAssert = driver.findElement(By.xpath("//*[@id=\'root\']/div/div[1]")).getText();
        Assert.assertEquals(loginPageAssert, "Swag Labs");


    }

    @When("Input Username")
    public void inputUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input Password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @And("Click login button")
    public void clickLoginButton() {
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();
    }

    @Then("User in on dashboard page")
    public void userInOnDashboardPage() {
        String namaWebsite = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[1]/div[2]/div")).getText();
        Assert.assertEquals(namaWebsite,"Swag Labs");
        driver.close();
    }

    @And("Input Invalid Password")
    public void inputInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauces");
    }

    @Then("User get error message")
    public void userGetErrorMessage() {
        String errorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
        driver.close();
    }

    @When("I input (.*) as username$")
    public void I_input_tdd_standart_user_as_username(String username){
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("I input (.*) as password$")
    public void iInputPasswordAsPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("I verify (.*) login result$")
    public void iVerifyStatusLoginResult(String status) {
        if (status.equals("success")){
            String namaWebsite = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[1]/div[2]/div")).getText();
            Assert.assertEquals(namaWebsite,"Swag Labs");
        } else {
            String errorLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
            Assert.assertEquals(errorLogin,"Epic sadface: Username and password do not match any user in this service");
        }
        driver.close();
    }
}
