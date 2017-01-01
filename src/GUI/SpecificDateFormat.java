package GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Michael Andersson
 */
public class SpecificDateFormat {

    private DateFormat dateFormat;

    public SpecificDateFormat(){
        this.dateFormat = new SimpleDateFormat("yyyy MMMM, EEEE hh:mm",
                Locale.ENGLISH);

    }

    public String formatToString(Date d){

        return dateFormat.format(d);
    }

    public Date formatToDate(String str){
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }


}
