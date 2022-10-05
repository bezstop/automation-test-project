package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({ScreenShooterExtension.class})
abstract public class BaseTest {
    //    @Rule
    //    public ScreenShooter makeScreenshotOnFailure = ScreenShooter.failedTests().succeededTests();
    //    @RegisterExtension
    //    static ScreenShooterExtension screenshotEmAll = new ScreenShooterExtension(true).to("target/screenshots");

    //    @BeforeAll
    //    static void setupAllureReports() {
    //        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    //    }

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "2560x1440";
        Configuration.headless = false;
        //        Configuration.reportsFolder = "target/allure-results";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Before
    public void init() {
        setUp();
    }

    @After
    @Step("Закрываем браузер")
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    //    @BeforeAll
    //    static void setupAllureReports() {
    ////        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    ////
    //        // or for fine-tuning:
    //        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
    //                .screenshots(false)
    //                .savePageSource(true)
    //        );
    //    }
}
