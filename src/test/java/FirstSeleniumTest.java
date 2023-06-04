import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver; //он отвечает за управление веб браузером

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup(); //будет сам находить необходимую версию веб драйвера
        driver = new ChromeDriver(); //проинициализировали веб драйвер. новый экземпляр класса
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize(); //обратились к драйверу, методу manage, к окну и максимизировали величину окна
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void firstTest() throws InterruptedException {
        WebElement managerLoginButton = driver.findElement(By.xpath("//*[@ng-click='manager()']")); //по какому типу будем искать локатор
        managerLoginButton.isDisplayed();
        managerLoginButton.click();
        //     Thread.sleep(3000); //задержка действия на 3 секунды
        managerLoginButton.click();
        // Thread.sleep(3000);


//        driver.findElement(By.cssSelector("[ng-reflect-name='email']"));
//        driver.findElement(By.name("password"));
//        driver.findElement(By.cssSelector("[ng-reflect-name='confirm_password']"));
//        driver.findElement(By.className("btn btn-info my-1 btn-block"));
//
//
//        driver.findElement(By.id("notch"));
//        driver.findElement(By.tagName("button"));
//        driver.findElement(By.name("email"));
//        driver.findElement(By.className("center"));
//        driver.findElement(By.linkText("/user/registration"));
//        driver.findElement(By.cssSelector("[type='email']"));
//        driver.findElement(By.cssSelector("[ng-click='manager()']"));
//        driver.findElement(By.cssSelector("[name='email']"));
//        driver.findElement(By.cssSelector(".btn.btn-primary.btn-lg"));
//        driver.findElement(By.cssSelector("#notch")); // по id, но через селектор, а можно просто, как выше, по id
//        driver.findElement(By.cssSelector("*"));
//        driver.findElement(By.xpath("//*[@class='center']//*[@ng-click='manager()']"));
//
//        driver.findElement(By.cssSelector("#see-book-Designing\\ Evolvable\\ Web\\ APIs\\ with\\ ASP\\.NET"));
    }

    @AfterMethod
    //метод который будет выполняться в независимости упал ли тест или нет. Наш браузер будет закрываться всегда
    public void tearDown() {
        // driver.close(); - закрывает текущую вкладку
        driver.quit(); // закрывает браузер
    }
}
