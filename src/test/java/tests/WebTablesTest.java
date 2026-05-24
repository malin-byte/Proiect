package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.WebTablesPage;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
//Deschide webpage si adauga detalile pentru inregistrarea unui utilizator
//Verifica daca se-a putut adauga cu success
public class WebTablesTest {

    WebDriver driver;
    WebTablesPage webTablesPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webTablesPage = new WebTablesPage(driver);
    }

    @Test
    public void testAddRecord() {
        webTablesPage.openPage();
        webTablesPage.addRecord("Maria", "Ionescu", "maria@test.com", "30", "5000", "QA");
        Assert.assertTrue(webTablesPage.isRecordPresent("Maria"), "Inregistrarea nu apare in tabel!");
        System.out.println("Inregistrare adaugata cu succes!");
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