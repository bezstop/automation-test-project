package selenium.helpDesk.tests;

import core.BaseSeleniumTest;
import selenium.helpDesk.pages.MainPage;
import org.junit.Assert;
import org.junit.Test;
import selenium.helpDesk.readProperties.ConfigProvider;

import static selenium.helpDesk.pages.TicketPage.*;
import static selenium.helpDesk.helpers.StringModifier.getUniqueStrong;
import static selenium.helpDesk.helpers.TestValues.*;

public class HelpDeskTest extends BaseSeleniumTest {

    @Test
    public void checkTicket() {
        String title = getUniqueStrong(TEST_TITLE);
        //        String body = "Сообщеине в тех поддержку с помощью селениума";
        //        String email = "testYou@fake.ru";
        //        MainPage mainPage = new MainPage();

        new MainPage().createTicket(title, TEST_BODY, TEST_EMAIL)
                .openLoginPage()
                .auth(ConfigProvider.DEMO_LOGIN, ConfigProvider.DEMO_PASSWORD)
                .findTicket(title);

        Assert.assertTrue(getTitle().contains(title));
        Assert.assertEquals(getBody(), TEST_BODY);
        Assert.assertEquals(getEmail(), TEST_EMAIL);
    }
}
