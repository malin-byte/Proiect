package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AlertsPage;
import org.testng.ITestResult;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
//Deschide webpage, accepta alertele si verifica rezultatul daca a putut sa fie acceptata sau nu
public class AlertsTest {

    WebDriver driver;
    AlertsPage alertsPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        alertsPage = new AlertsPage(driver);
    }

    @Test
    public void testAlert() {
        alertsPage.openPage();
        alertsPage.triggerAndAcceptAlert();
        System.out.println("Alerta acceptata cu succes!");
    }

    @Test
    public void testConfirm() {
        alertsPage.openPage();
        alertsPage.triggerAndAcceptConfirm();
        Assert.assertTrue(alertsPage.getConfirmResult().contains("Ok"), "Confirmarea ca nu a functionat!");
        System.out.println("Confirm ca a fost acceptat cu succes!");
    }

    @Test
    public void testPrompt() {
        alertsPage.openPage();
        alertsPage.triggerPromptAndType("TestUser");
        Assert.assertTrue(alertsPage.getPromptResult().contains("TestUser"), "Promptul nu functioneaza!");
        System.out.println("Promptul a fost completat cu succes!");
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