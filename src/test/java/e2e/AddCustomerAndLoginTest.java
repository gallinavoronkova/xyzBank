package e2e;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.HomePage;

public class AddCustomerAndLoginTest extends TestBase {

    //Select select; //типизированная переменная, если мы ее будем исплользовать несколько раз или обьявили ее в 69 строке

    Faker faker = new Faker(); // инициализируем новый экземпляр класса Faker из которого потом будем вытаскивать методы

    HomePage homePage;

    @Test
    public void addCustomerAndLogin() {
        String firstName = faker.internet().uuid(); //рандом берем из интернета
        String lastName = faker.internet().uuid(); //рандом берем из интернета
        String postCode = faker.address().zipCode(); //рандом берем из интернета
        String expectedFirstAndLastName = firstName + " " + lastName; //создали переменную, склеенную из двух, потому что будев в поле вводить имя и фамилию и они у нас через пробел должны быть
        homePage = new HomePage(app.driver);



        //Click on Bank Manager Login Button
        homePage.clickOnBankManagerLoginButton(); // вызвали переменную и вытащили из нее клик. Эта строчка заменяет три последующих строки
//        WebElement managerLoginButton = driver.findElement(By.xpath("//*[@ng-click='manager()']")); //по какому типу будем искать локатор. Сделали переменную, чтобы потом вызывать
//        managerLoginButton.isDisplayed();
//        managerLoginButton.click();

        // Click on Add Customer Button
      pageBase.click(By.cssSelector("[ng-class='btnClass1']"));
//        WebElement addCustomerTab = driver.findElement(By.cssSelector("[ng-class='btnClass1']"));
//        addCustomerTab.isDisplayed();
//        addCustomerTab.click();

        // Fill Add customer form
        app.driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);//вводит в поле ввода наше значение
        app.driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);//вводит в поле ввода наше значение
        app.driver.findElement(By.xpath("//input[@placeholder='Post Code']")).sendKeys(postCode);//вводит в поле ввода наше значение

        // Click on Submit Button
        pageBase.click(By.xpath("//button[@type='submit']"));
//        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        submitButton.isDisplayed();
//        submitButton.click();

        // Verify Customer is added successfully (take alert text)
        String actualSuccessfullyAlertText = app.driver.switchTo().alert().getText(); //создали переменную для того, чтоб сверить текст в алерте
        String expectedSuccessfullyAlertText = "Customer added successfully with customer id";
        String err = "Actual alert text does not contain expected alert text";
        Assert.assertTrue(actualSuccessfullyAlertText.contains(expectedSuccessfullyAlertText), err);
        app.driver.switchTo().alert().accept(); //подтвердить алерт, на нем нажали ОК

        // Click on Home button
        homePage.clickOnHomeButton();
//        WebElement homeButton = driver.findElement(By.xpath("//*[@ng-click='home()']"));
//        homeButton.isDisplayed();
//        homeButton.click();

        // Click on Customer Login button
        homePage.clickOnCustomerLoginButton(); // вызвали метод
//        WebElement customerLoginButton = driver.findElement(By.xpath("//button[@ng-click='customer()']"));
//        customerLoginButton.isDisplayed();
//        customerLoginButton.click();

        // Choose customer from the dropdown
        WebElement customerDropdown = app.driver.findElement(By.xpath("//*[@ng-model='custId']"));
        Select select = new Select(customerDropdown); // используем только, когда на странице есть тег <select> для выбора из выпадающего списка
        select.selectByVisibleText(expectedFirstAndLastName);

        // Click on Login Button
        pageBase.click(By.xpath("//button[@type='submit']"));
//        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        loginButton.isDisplayed();
//        loginButton.click();

        // Verify correct customer is logged in (take text from the page)
        String firstNameAndLastName = app.driver.findElement(By.xpath("//*[@class='fontBig ng-binding']")).getText();
        Assert.assertEquals(firstNameAndLastName, expectedFirstAndLastName, "Actual first name and last name is not equal expected");
    }
}
