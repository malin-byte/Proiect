package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AlertsPage extends BasePage {

    private final By alertButton = By.id("alertButton");
    private final By confirmButton = By.id("confirmButton");
    private final By promptButton = By.id("promtButton");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public void openPage() {
        driver.get("https://demoqa.com/alerts");
    }

    public void triggerAndAcceptAlert() {
        click(alertButton);
        driver.switchTo().alert().accept();
    }

    public void triggerAndAcceptConfirm() {
        click(confirmButton);
        driver.switchTo().alert().accept();
    }

    public String getConfirmResult() {
        return getText(confirmResult);
    }

    public void triggerPromptAndType(String text) {
        click(promptButton);
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
    }

    public String getPromptResult() {
        return getText(promptResult);
    }
}