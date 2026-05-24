package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class VerificareTitluUrlTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Deschide DemoQA, verifica titlul
    @Test
    public void testDemoqaTitle() {
        driver.get("https://demoqa.com/");
        String title = driver.getTitle();
        System.out.println("Titlu actual: " + title);
        Assert.assertFalse(title.isEmpty(), "Titlul paginii este gol!");
    }

    //Deschide DemoQA, verifica url-ul
    @Test
    public void testDemoqaUrlIsCorrect() {
        driver.get("https://demoqa.com/");
        String url = driver.getCurrentUrl();
        System.out.println("URL actual: " + url);
        Assert.assertTrue(url.contains("demoqa"), "URL-ul nu contine demoqa!");
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