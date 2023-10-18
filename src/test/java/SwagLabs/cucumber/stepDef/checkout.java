package SwagLabs.cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class checkout {

    WebDriver driver;
    String baseUrl = "https://www.saucedemo.com/";
    @Given("user login with \\(standart_user) valid credentials")
    public void userLoginWithStandart_userValidCredentials() {
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
    @Then("user choose the product in the Inventory page")
    public void userChooseTheProductInTheInventoryPage() {
        driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();
        //Assertion
        driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[1]/img")).isDisplayed();
    }

    @And("user click \\(add to cart) button in the product page")
    public void userClickAddToCartButtonInTheProductPage() {
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-backpack\"]")).click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        //Assertion
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span")).isDisplayed();
    }

    @Then("user click cart icon in the top right of the Product page")
    public void userClickCartIconInTheTopRightOfTheProductPage() {
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

        //Assertion
        String checkoutPage_title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        Assert.assertEquals(checkoutPage_title, "Your Cart");
    }

    @And("user click checkout  button")
    public void userClickCheckoutButton() {
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
    }

    @Then("user redirected to checkout form page")
    public void userRedirectedToCheckoutFormPage() {
        //Assertion
        String checkoutForm_title = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();

        Assert.assertEquals(checkoutForm_title,"Checkout: Your Information");

        driver.close();
    }

    @Then("user get error message")
    public void userGetErrorMessage() {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        try {
//            // Mencoba untuk mencari elemen
//            String elementText = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]")).getText();
//            // Jika elemen ditemukan, lanjutkan dengan operasi yang diperlukan
//            // ...
//        } catch (NoSuchElementException e) {
//            // Penanganan jika elemen tidak ditemukan
//            System.out.println("Elemen tidak ditemukan: " + e.getMessage());
//        } finally {
//            // Menutup browser, terlepas dari apakah elemen ditemukan atau tidak
//            if (driver != null) {
//                driver.close(); // Pastikan untuk memanggil driver.quit()
//            }
//        }

        //Assertion
        String errorLogin = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]")).getText();
        String message = "";
        Assert.assertEquals(message, errorLogin,"Epic sadface: There is no product to be checkout");
        System.out.println(message);
        driver.close();

    }

}
