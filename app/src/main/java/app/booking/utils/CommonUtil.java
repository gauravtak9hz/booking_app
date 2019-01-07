package app.booking.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.booking.BookingApplication;
import app.booking.R;

//all the methods which are frequently used are kept in this class

/**
 * Created by NineHertz-and-team on 5/1/19.
 */

public class CommonUtil {

    public static String loadJSONFromAsset(String filename) {
        Context context = BookingApplication.getContext();
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
    public static String getDateFromDateValue(String dateValue)
    {
        //get the time and date with device time zone setting with the parsing of given format


        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX",Locale.getDefault());
        DateFormat targetFormat = new SimpleDateFormat("E MMM dd");
        Date date = null;
        try {
            date = originalFormat.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }

    public static String getTimeFromDateValue(String dateValue)
    {
        //get the time and date with device time zone setting with the parsing of given format


        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault());
        DateFormat targetFormat = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = originalFormat.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }
    public static String getDateTimeFromDateValue(String dateValue)
    {
        //get the time and date with device time zone setting with the parsing of given format


        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX",Locale.getDefault());
        DateFormat targetFormat = new SimpleDateFormat("EEEE MMM dd, hh:mm a");
        Date date = null;
        try {
            date = originalFormat.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);
        return formattedDate;
    }
    public static void showSnackbar(final Activity activity, final String msg)
    {
       Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }
    public static void setValueIntoTextView(TextView tv,String value,boolean isHtmlAllowed)
    {
        if(tv!=null)
        {
            if(value!=null && !value.equals(""))
                 if(isHtmlAllowed)
                     tv.setText(Html.fromHtml(value));
            else
                     tv.setText(value);

            else
                tv.setText(BookingApplication.getContext().getResources().getString(R.string.value_na));
        }
    }

    public static String getDoubleValueWithPlaces(Double value,int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        String strValue = (double) tmp / factor + "";
        return strValue;
    }
    public static String differenceBetweenDates(String startDateValue,String endDateValue)
    {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",Locale.getDefault());
        Date startDate = null,endDate = null;
        try {
            startDate = originalFormat.parse(startDateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = originalFormat.parse(endDateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long diff = endDate.getTime() - startDate.getTime();
        int diffInDays = (int) diff / (1000 * 60 * 60 * 24);
          diff -= diffInDays * (24 * 60 * 60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        diff -= diffHours * (60 * 60 * 1000);
        long diffMinutes = diff / (60 * 1000) % 60;
        diff -= diffMinutes * ( 60 * 1000);
        long diffSeconds = diff / 1000 % 60;
      //  diff -= diffMinutes * ( 60 * 1000);
        String difference = "";
        if(diffInDays>0 && diffInDays<=1)
            difference = difference+diffInDays+" day ";
       else if(diffInDays>1)
            difference = difference+diffInDays+" days ";
        if(diffHours>0 && diffHours<=1)
            difference = difference+diffHours+" hour ";
       else if(diffHours>1)
            difference = difference+diffHours+" hours ";
        if(diffMinutes>0 && diffMinutes<=1)
            difference = difference+diffMinutes+" min ";
       else if(diffMinutes>1)
            difference = difference+diffMinutes+" mins ";
        if(diffSeconds>0 && diffSeconds<=1)
            difference = difference+diffSeconds+" sec ";
        else if(diffSeconds>1)
            difference = difference+diffSeconds+" secs";

        if(difference.equals(""))
           difference ="No difference";

        return difference;
    }
}
