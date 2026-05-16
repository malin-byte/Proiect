package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormPage extends BasePage {

    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By emailField = By.id("userEmail");
    private final By mobileField = By.id("userNumber");
    private final By genderMale = By.xpath("//label[@for='gender-radio-1']");
    private final By submitButton = By.id("submit");
    private final By successModal = By.id("example-modal-sizes-title-lg");

    public FormPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String firstName, String lastName, String email, String mobile) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        scrollToElement(genderMale);
        jsClick(genderMale);
        type(mobileField, mobile);
    }

    public void submitForm() {
        scrollToElement(submitButton);
        jsClick(submitButton);
    }

    public String getSuccessMessage() {
        return getText(successModal);
    }
}