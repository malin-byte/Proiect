package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
public class RadioButtonTest {

    WebDriver driver;
    WebDriverWait wait;
//Deschide site
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
//Verifica butonul de YES si rezultatul daca poate apasa pe el
    @Test
    public void testYesRadioButton() {
        driver.get("https://demoqa.com/radio-button");

        // Click pe Yes
        WebElement yesRadio = driver.findElement(By.xpath("//label[@for='yesRadio']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesRadio);

        // Verifica rezultatul
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("mt-3")));
        Assert.assertTrue(result.getText().contains("Yes"), "Radio button Yes nu a fost selectat!");
        System.out.println("Radio button Yes selectat cu succes!");
    }
//La fel ca mai sus doar ca verifica cu butonul de ''Impressive'' in loc de Yes
    @Test
    public void testImpressiveRadioButton() {
        driver.get("https://demoqa.com/radio-button");

        // Click pe Impressive
        WebElement impressiveRadio = driver.findElement(By.xpath("//label[@for='impressiveRadio']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", impressiveRadio);

        // Verifica rezultatul
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("mt-3")));
        Assert.assertTrue(result.getText().contains("Impressive"), "Radio button Impressive nu a fost selectat!");
        System.out.println("Radio button Impressive selectat cu succes!");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.SUCCESS ||
                result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshot = ts.getScreenshotAs(OutputType.FILE);
            String testName = result.getName();
            FileUtils.copyFile(screenshot,
                    new File("screenshots/" + testName + ".png"));
            System.out.println("Screenshot salvat: " + testName + ".png");
        }
        driver.quit();
    }
}