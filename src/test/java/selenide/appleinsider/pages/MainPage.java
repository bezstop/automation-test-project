package selenide.appleinsider.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static selenide.appleinsider.tests.AppleTest.EXPECTED_WORD;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта Appleinsider.ru
 */

public class MainPage {
    private final SelenideElement textBoxInput = $x("//input[@type='text']");

    private final SelenideElement headersElement = $x("//*[@id='menu-primary']//li[3]");

    private final ElementsCollection headersElements = $$x("//*[@id='menu-primary']//li//a");

    public MainPage(String url) {
        Selenide.open(url);
    }

    public MainPage() {

    }

    @Step("Вычиваем значения пропертей и подключаемся к тестируемому сайту")
    public MainPage readProperties() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("app.properties"));
        String urlFromProperty = System.getProperty("site");
        //        System.out.println(urlFromProperty);
        return new MainPage(urlFromProperty);
    }

    /**
     * Метод выполняет поиск среди статей
     *
     * @param searchString поисковая строка
     */
    //    public void search(String searchString) {
    //        textBoxInput.setValue(searchString).sendKeys(Keys.ENTER);
    //    }

    /**
     * Выполняется поиск среди статей
     *
     * @param searchString поисковая строка
     */

    @Step
    public SearchPage search(String searchString) {
        textBoxInput.setValue(searchString).sendKeys(Keys.ENTER);
        return new SearchPage();
    }

    @Step("Кликаем по заголоку в шапке с номер 3")
    public void getHeaderElement() {
        headersElement.click();
        System.out.println("asd");
    }

    @Step("Получаем коллекцию ссылок из заголовка")
    public void listHeaderLastElement() {
        headersElement.shouldBe(Condition.visible);
        List<String> linksHref = headersElements.stream().map(x -> x.getAttribute("href")).collect(Collectors.toList());
        clickEachElement(linksHref);
    }

    @Step("Открываем каждую ссылку коллекции если элементов в коллекции больше 3, список ссылок {0}")
    public void clickEachElement(List<String> list) {
        Assert.assertTrue(list.size() > 3);
        list.forEach(Selenide::open);
    }

    /**
     * @param attribute
     * @return
     */

    @Step("Проверяем, что последняя ссылка в коллекции содержит адрес https://appleinsider.ru/tag/imac-4k и если это так, то кликаем по этой ссылке")
    public MainPage getHeaderLastElement(String attribute) {
        String href = headersElements.last().getAttribute(attribute);
        if (checkRightLink(EXPECTED_WORD, href)) {
            headersElements.last().click();
        }
        return new MainPage();
    }

    public boolean checkRightLink(String expectedLink, String realLink) {
        Assert.assertEquals(expectedLink, realLink);
        return true;
    }

    @Step("Открываем сайт по адресу {0}")
    public void openWebSite(String url) {
        Selenide.open(url);
    }
}
