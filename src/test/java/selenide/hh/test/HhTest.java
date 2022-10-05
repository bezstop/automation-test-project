package selenide.hh.test;

import core.BaseTest;
import selenide.hh.pages.HhResumePage;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static selenide.hh.pages.HhResumePage.*;
import static org.junit.Assert.assertEquals;

public class HhTest extends BaseTest {

    private final static String URL = "https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    @Test
    public void checkAttributesHashMap() {

        new HhResumePage(URL);

        Map<String, Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(GENDER, "М");
        expectedAttributes.put(AGE, 26);
        expectedAttributes.put(CITY, "Санкт-Петербург");
        expectedAttributes.put(RELOCATE, false);
        expectedAttributes.put(CONFIRMED_PHONE, true);

        Map<String, Object> actualAttributes = getAttributes();

        assertEquals(expectedAttributes, actualAttributes);
    }
}
