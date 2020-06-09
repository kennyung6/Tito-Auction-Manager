package util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {

    //1 minute = 60 seconds
    //1 hour = 60 x 60 = 3600
    //1 day = 3600 x 24 = 86400
    public long printDifference(String startDateTime, String endDateTime){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime1= LocalDateTime.parse(endDateTime, formatter);
        LocalDateTime dateTime2= LocalDateTime.parse(startDateTime, formatter);

        long diffInMilli = java.time.Duration.between(dateTime1, dateTime2).toMillis();
        long diffInSeconds = java.time.Duration.between(dateTime1, dateTime2).getSeconds();
        long diffInMinutes = java.time.Duration.between(dateTime1, dateTime2).toMinutes();


       // System.out.println("startDate : " + startDateTime);
        System.out.println("endDate : "+ endDateTime);
        System.out.println("different in Seconds : " + diffInSeconds);
       /* System.out.println("different in Milli : " + diffInMilli);
        System.out.println("different in Minutes : " + diffInMinutes);*/

        return diffInSeconds;

    }

    public long printSpecific(long different) {
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays,
                elapsedHours, elapsedMinutes, elapsedSeconds);


        return different;
    }

    public String getTime(String timestamp){


        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
        Date currentdate= null;
        String time = null;
        try {
            currentdate = sdf.parse(timestamp);
            SimpleDateFormat sdf2=new SimpleDateFormat("hh:mm:ss a");
             time = sdf2.format(currentdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return time;
    }

}
