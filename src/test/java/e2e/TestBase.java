package e2e;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    protected static ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setupTest() {
        app.init();
    }

    @AfterMethod
    //метод который будет выполняться в независимости упал ли тест или нет. Наш браузер будет закрываться всегда
    public void tearDown() {
        app.stop();
    }
}
