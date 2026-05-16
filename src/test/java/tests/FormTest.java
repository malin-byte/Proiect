package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormPage;
//Deschide Demoqa forms, pune nume, email, telefon si da submit
//Verifica si daca a fost submis cu success
public class FormTest {

    WebDriver driver;
    FormPage formPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        formPage = new FormPage(driver);
    }

    @Test
    public void testFormSubmission() {
        formPage.openPage();
        formPage.fillForm("John", "Doe", "john@test.com", "0712345678");
        formPage.submitForm();
        String message = formPage.getSuccessMessage();
        Assert.assertEquals(message, "Thanks for submitting the form", "Formularul nu s-a trimis corect!");
        System.out.println("Formular a fost trimis cu succes!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}