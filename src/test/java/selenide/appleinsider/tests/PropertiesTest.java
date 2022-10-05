package selenide.appleinsider.tests;

import selenide.appleinsider.ConfigProvider;
import core.BaseTest;
import org.junit.Test;

public class PropertiesTest extends BaseTest {
    // Вычитываем из app.properties
    //    @Test
    //    public void selenium.helpDesk.readProperties() throws IOException {
    //        System.getProperties().load(ClassLoader.getSystemResourceAsStream("app.properties"));
    //        String urlFromProperty = System.getProperty("url");
    ////        System.out.println(urlFromProperty);
    //        Selenide.open(urlFromProperty);

    // Вычитываем из ConfigProvider
    @Test
    public void readFromConf() {
        String urlFromAdmin = ConfigProvider.URL;
        System.out.println(urlFromAdmin);
        Boolean isDemoAdmin = ConfigProvider.IS_DEMO_ADMIN;
        System.out.println(isDemoAdmin);
        if (ConfigProvider.readConfig().getBoolean("usersParams.admin.isAdmin")) {
            System.out.println("Админ является амином");
        } else {
            System.out.println("Немного не админ");
        }
    }
}

