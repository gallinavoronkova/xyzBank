package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PageBase { // это наш основной класс
    public WebDriver driver;

    public PageBase(WebDriver driver) { //конструктор
        this.driver = driver; //у класса появятся аргументы, кот можно будет передать в драйвер

        PageFactory.initElements(driver, this); // инициализируем драйвер для этой страницы. Для FindBy. Без этого он не будет работать
    }

    // сюда будем писать общие методы (те, которые постоянно повторяются)

    @FindBy(xpath = "//*[@ng-click='home()']")
    WebElement homeButton;

    public void click(WebElement element) {
        element.isDisplayed();
        element.click();
    }

    public void clickOnHomeButton() {
        click(homeButton);
    }

    public void selectDropdownText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }


}
