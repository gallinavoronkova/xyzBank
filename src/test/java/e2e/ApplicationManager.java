package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager { //хранит в себе описание методов и драйвер
    public WebDriver driver;

    protected void init() {
        WebDriverManager.chromedriver().setup(); //будет сам находить необходимую версию веб драйвера
        driver = new ChromeDriver(); //проинициализировали веб драйвер. новый экземпляр класса
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize(); //обратились к драйверу, методу manage, к окну и максимизировали величину окна
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    protected void stop() {
        driver.quit();
    }
}
