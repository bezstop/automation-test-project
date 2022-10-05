package selenium.helpDesk.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringModifier {

    /**
     * @param str Строка которая будет преобразована к формату yyyy-MM-dd_HH:mm:ss
     * @return преобразованную строку формата yyyy-MM-dd_HH:mm:ss
     */
    public static String getUniqueStrong(String str) {
        return str + new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
    }
}
