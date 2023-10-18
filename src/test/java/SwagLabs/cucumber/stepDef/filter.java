package SwagLabs.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class filter {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("the user logs in with the credentials \\(standard_user)")
    public void theUserLogsInWithTheCredentialsStandard_user() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        String namaWebsite = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[1]/div[2]/div")).getText();
        Assert.assertEquals(namaWebsite,"Swag Labs");
    }

    @When("the user clicks on the filter icon in the top right corner")
    public void theUserClicksOnTheFilterIconInTheTopRightCorner() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select")).click();
    }

    @And("the user selects the Price \\(low to high) filter option")
    public void theUserSelectsThePriceLowToHighFilterOption() {
        driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div/span/select/option[3]")).click();
    }

    @Then("all products are filtered correctly")
    public void allProductsAreFilteredCorrectly() {
        //Assertion

        String HargaTerendah = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();

        Assert.assertEquals(HargaTerendah, "$7.99");
        driver.close();
    }

    @Given("the user logs in with the credentials \\(problem_user)")
    public void theUserLogsInWithTheCredentialsProblem_user() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseUrl);

        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//*[@id=\'login-button\']")).click();

        String namaWebsite = driver.findElement(By.xpath("//*[@id=\'header_container\']/div[1]/div[2]/div")).getText();
        Assert.assertEquals(namaWebsite,"Swag Labs");
    }

    @Then("all products are not filtered correctly")
    public void allProductsAreNotFilteredCorrectly() {
        //Assertion

        String BukanHargaTerendah = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();

        Assert.assertEquals(BukanHargaTerendah, "$29.99");
        driver.close();
    }

}
