package GUI;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  Simple utility class to format date format.
 *
 * @author Michael Andersson
 * @version 4 January 2017
 */
public class SpecificDateFormat {

    private DateFormat dateFormat;
    private DateFormat timeAndDay;

    /**
     * Constructor for SpecificDateFormat
     */
    public SpecificDateFormat(){
        this.dateFormat = new SimpleDateFormat("yyyy MMMM, EE  HH:mm",
                new Locale("Swedish", "Sweden"));
        this.timeAndDay = new SimpleDateFormat("EEEE hh:mm");
    }

    /**
     * Format Date to String
     * @param d Date
     * @return String of date
     */
    public String formatToString(Date d){

        return dateFormat.format(d);
    }

    /**
     * Format String to Date
     * @param str String of a date
     * @return Date
     */
    public Date formatToDate(String str){
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    /**
     * Function to only get the time and date from Date.
     * @param d Date
     * @return String
     */
    public String getTimeAndDayFromDate(Date d){
        return timeAndDay.format(d);
    }


}
