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
    private DateFormat timeAndDay;

    public SpecificDateFormat(){
        this.dateFormat = new SimpleDateFormat("yyyy MMMM, EEEE HH:mm",
                new Locale("Swedish", "Sweden"));
        this.timeAndDay = new SimpleDateFormat("EEEE hh:mm");
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

    public String getTimeAndDayFromDate(Date d){
        return timeAndDay.format(d);
    }


}
