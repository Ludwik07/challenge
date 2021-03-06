package commons;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GeneralUtils {

    public String getCurrentTimeStamp(String pattern) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(pattern);
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
