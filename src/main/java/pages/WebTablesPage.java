package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebTablesPage extends BasePage {

    private final By addButton = By.id("addNewRecordButton");
    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By emailField = By.id("userEmail");
    private final By ageField = By.id("age");
    private final By salaryField = By.id("salary");
    private final By departmentField = By.id("department");
    private final By submitButton = By.id("submit");
    private final By tableRows = By.className("rt-tr-group");

    public WebTablesPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://demoqa.com/webtables");
    }

    public void addRecord(String firstName, String lastName, String email,
                          String age, String salary, String department) {
        click(addButton);
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        type(ageField, age);
        type(salaryField, salary);
        type(departmentField, department);
        click(submitButton);
    }

    public boolean isRecordPresent(String firstName) {
        return driver.getPageSource().contains(firstName);
    }
}