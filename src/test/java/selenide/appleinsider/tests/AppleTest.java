package selenide.appleinsider.tests;

import selenide.appleinsider.pages.MainPage;
import core.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;

//@ExtendWith({ScreenShooterExtension.class})
public class AppleTest extends BaseTest {

    final static String SEARCH_STRING = "Чем iPhone 13 отличается от iPhone 12";
    public final static String EXPECTED_WORD = "iphone-13";
    final static String EXPECTED_URL = "https://appleinsider.ru/tag/imac-4k";
    private final static String BASE_URL = "https://appleinsider.ru/";

    @Test
    public void checkHref() throws IOException {

        //        mainPage.openWebSite(BASE_URL);
        //        SearchPage searchPage = new SearchPage();
        //        String href = searchPage.getHrefFirstASrticle();
        Assert.assertTrue(new MainPage().readProperties()
                .search(SEARCH_STRING)
                .getHrefFirstArticle()
                .contains(EXPECTED_WORD));
    }

    @Test
    public void clickHeader() throws IOException {
        new MainPage().readProperties()
                .getHeaderElement();
    }

    //    @RegisterExtension
    //    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");
    @Test
    public void clickHeaders() throws IOException {
        new MainPage().readProperties().getHeaderLastElement("href");
    }

    @Test
    @DisplayName("Тест проверяет верхний заголовок")
    //    @Description("Проверка: если количество ссылок в шапке больше 3, то открываем ссылки")
    public void clickHeaders2() throws IOException {
        new MainPage().readProperties().listHeaderLastElement();
    }
}
