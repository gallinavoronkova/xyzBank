package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OpenAccountPage extends BankManagerLoginPage {
    public OpenAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@name = 'userSelect']")
    WebElement customerField;

    @FindBy(xpath = "//*[@name = 'currency']")
    WebElement currencyField;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement processButton;

    public void fillCustomerField(String firstName) {
        customerField.sendKeys(customerField);
    }

    public void fillCurrencyField(String lastName) {
        customerField.sendKeys(currencyField);
    }

    public void clickOnProcessButton() {
        click(processButton);
    }

}
