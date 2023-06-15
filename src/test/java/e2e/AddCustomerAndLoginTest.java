package e2e;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.AddCustomerPage;
import pages.CustomerLoginPage;
import pages.HomePage;

public class AddCustomerAndLoginTest extends TestBase {

    //Select select; //типизированная переменная, если мы ее будем исплользовать несколько раз или обьявили ее в 69 строке

    Faker faker = new Faker(); // инициализируем новый экземпляр класса Faker из которого потом будем вытаскивать методы

    HomePage homePage;
    AddCustomerPage addCustomerPage; //типизация класса. Делаем это сверху, чтобы потом в кде не нужно было повторять
    CustomerLoginPage customerLoginPage;

    AccountPage accountPage;

    @Test //анотация Test
    public void addCustomerAndLogin() {
        String firstName = faker.internet().uuid(); //рандом берем из интернета
        String lastName = faker.internet().uuid(); //рандом берем из интернета
        String postCode = faker.address().zipCode(); //рандом берем из интернета
        String expectedFirstAndLastName = firstName + " " + lastName; //создали переменную, склеенную из двух, потому что будев в поле вводить имя и фамилию и они у нас через пробел должны быть
        homePage = new HomePage(app.driver);


        //Click on Bank Manager Login Button
        homePage.clickOnBankManagerLoginButton(); // вызвали переменную и вытащили из нее клик. Эта строчка заменяет три последующих строки
//        WebElement managerLoginButton = driver.findElement(By.xpath("//*[@ng-click='manager()']")); //по какому типу будем искать локатор. Сделали переменную, чтобы потом вызывать //эти три последующие три строчки написаны чисто на Селениуме, это для общего развития))
//        managerLoginButton.isDisplayed();
//        managerLoginButton.click();

        // Click on Add Customer Tab
        addCustomerPage = new AddCustomerPage(app.driver); //новый экземпляр класса и передаем в него app.driver
        addCustomerPage.openAddCustomerTab();
//        WebElement addCustomerTab = driver.findElement(By.cssSelector("[ng-class='btnClass1']"));
//        addCustomerTab.isDisplayed();
//        addCustomerTab.click();

        // Fill Add customer form
//        app.driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);//вводит в поле ввода наше значение
//        app.driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);//вводит в поле ввода наше значение
//        app.driver.findElement(By.xpath("//input[@placeholder='Post Code']")).sendKeys(postCode);//вводит в поле ввода наше значение
        addCustomerPage.fillFirstNameField(firstName);
        addCustomerPage.fillLastNameField(lastName);
        addCustomerPage.fillPostCodeField(postCode);

        // Click on Submit Button
        addCustomerPage.clickOnAddCustomerButton();
//        pageBase.click(By.xpath("//button[@type='submit']"));
//        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        submitButton.isDisplayed();
//        submitButton.click();


        // Verify Customer is added successfully (take alert text)
        String expectedSuccessfullyAlertText = "Customer added successfully with customer id";
        String err = "Actual alert text does not contain expected alert text";
        Assert.assertTrue(addCustomerPage.getAlertText().contains(expectedSuccessfullyAlertText), err);
        addCustomerPage.applyAlert();

        // Click on Home button
        homePage.clickOnHomeButton();

        // Click on Customer Login button
        homePage.clickOnCustomerLoginButton(); // вызвали метод

        // Choose customer from the dropdown
        customerLoginPage = new CustomerLoginPage(app.driver);
        customerLoginPage.selectCustomerName(expectedFirstAndLastName);
        customerLoginPage.clickOnLoginButton();

        // Verify correct customer is logged in (take text from the page)
        accountPage = new AccountPage(app.driver);
        err = "Actual first name and last name is not equal expected";
        Assert.assertEquals(accountPage.getCustomerFirstAndLastName(), expectedFirstAndLastName, err);
    }

    @Test
    public void addCustomerWithInvalidData() {
        String firstName = " ";
        String lastName = " ";
        String postCode = " ";

        homePage = new HomePage(app.driver);
        homePage.clickOnBankManagerLoginButton();
        addCustomerPage = new AddCustomerPage(app.driver);
        addCustomerPage.openAddCustomerTab();

        addCustomerPage.fillFirstNameField(firstName);
        addCustomerPage.fillLastNameField(lastName);
        addCustomerPage.fillPostCodeField(postCode);
        addCustomerPage.clickOnAddCustomerButton();

        String expectedUnSuccessfullyAlertText = "Please check the details. Customer may be duplicate.";
        String err = "Actual alert text does not contain expected alert text";
        Assert.assertTrue(addCustomerPage.getAlertText().contains(expectedUnSuccessfullyAlertText), err);
        addCustomerPage.applyAlert();
    }
}
