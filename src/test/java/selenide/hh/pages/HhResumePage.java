package selenide.hh.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class HhResumePage {

    protected final static SelenideElement ПОЛ = $x("//span[@data-qa='resume-personal-gender']");
    protected final static SelenideElement ГОРОД = $x("//span[@data-qa='resume-personal-address']");
    protected final static SelenideElement ВОЗРАСТ = $x("//span[@data-qa='resume-personal-age']/span");
    protected final static SelenideElement liveData = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    protected final static SelenideElement tick =
            $x("//div[@data-qa='resume-contacts-phone']/span[@class='bloko-icon bloko-icon_done bloko-icon_initial-secondary']");

    public static String GENDER = "Пол";
    public static String CITY = "Город";
    public static String AGE = "Возраст";
    public static String RELOCATE = "Готовность к переезду";
    public static String CONFIRMED_PHONE = "Подтвержденный номер телефона";

    public HhResumePage(String url) {
        Selenide.open(url);
    }

    public static Map<String, Object> getAttributes() {
        return new HashMap<>() {{
            put(GENDER, checkGenderValue(ПОЛ, "Мужчина"));
            put(AGE, parseStringForInt(replacePartOfDataForEmptyString((getText(ВОЗРАСТ)), "[^0-9]", "")));
            put(CITY, getText(ГОРОД));
            put(RELOCATE, isReadyToRelocate());
            put(CONFIRMED_PHONE, isPhoneConfirmed());
        }};
    }

    public static boolean isPhoneConfirmed() {
        return tick.isDisplayed();
    }

    public static boolean isReadyToRelocate() {
        return !liveData.getText().split(", ")[1].equals("не готов к переезду");
        //        String relocate = array[2];

    }

    /**
     * Метод получает текст у элемента
     *
     * @param element
     * @return
     */
    public static String getText(SelenideElement element) {
        return element.getText();
    }

    /**
     * Метод для замены строковых букв в промежутке от а-я на пустые значения
     *
     * @param strReplace
     * @param regexValue
     * @param replacementValue
     * @return
     */
    public static String replacePartOfDataForEmptyString(String strReplace, String regexValue, String replacementValue) {
        return strReplace.replaceAll(regexValue, replacementValue);
    }

    /**
     * Метод получаем пол и преобразуем в М или Ж
     *
     * @return
     */

    public static String checkGenderValue(SelenideElement element, String textValue) {
        return getText(element).equals(textValue) ? "М" : "Ж";
    }

    /**
     * Метод для преобразования строки в число
     *
     * @param textForPars
     * @return
     */
    public static int parseStringForInt(String textForPars) {
        return Integer.parseInt(textForPars);
    }
}
