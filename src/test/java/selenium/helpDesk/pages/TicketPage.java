package selenium.helpDesk.pages;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketPage extends BaseSeleniumPage {
    @FindBy(xpath = "//th[text()='Submitter E-Mail']/following::td[1]")
    private static WebElement email;

    @FindBy(xpath = "//h3")
    private static WebElement title;

    @FindBy(xpath = "//td[@id='ticket-description']//p")
    private static WebElement body;

    public TicketPage() {
        PageFactory.initElements(driver, this);
    }

    public static String getEmail() {
        return email.getText();
    }

    public static String getTitle() {
        return title.getText();
    }

    public static String getBody() {
        return body.getText();
    }
}
