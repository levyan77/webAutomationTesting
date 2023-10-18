package SwagLabs.cucumber.stepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class productPage {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("the user logs in with credentials \\(standard_user)")
    public void theUserLogsInWithCredentialsStandard_user() {
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

    @Given("the user logs in with credentials \\(problem_user)")
    public void theUserLogsInWithCredentialsProblem_user() {
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

    @Then("the user clicks on the image or text of the product \\(Sauce Labs Backpack) on the main page")
    public void theUserClicksOnTheImageOrTextOfTheProductSauceLabsBackpackOnTheMainPage() {
        Random rand = new Random();
        int randomAction = rand.nextInt(2);  // Generates 0 or 1

        if (randomAction == 0) {
            // Perform the action of clicking on the image
            driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]")).click();
        } else {
            // Perform the action of clicking on the text
            driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]")).click();
        }
    }

    @Then("the user lands on the product page \\(Sauce Labs Backpack)")
    public void theUserLandsOnTheProductPageSauceLabsBackpack() {
        //Assertion
        String ProductPageTitle = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        Assert.assertEquals(ProductPageTitle, "Sauce Labs Backpack");
        driver.close();

    }

    @Then("the user lands on a product page other than \\(Sauce Labs Backpack)")
    public void theUserLandsOnAProductPageOtherThanSauceLabsBackpack() {
        //Assertion
        String ProductPageTitle = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        Assert.assertEquals(ProductPageTitle, "Sauce Labs Fleece Jacket");
        driver.close();
    }


}
