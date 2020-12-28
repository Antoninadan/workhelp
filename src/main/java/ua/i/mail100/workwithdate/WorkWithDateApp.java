package ua.i.mail100.workwithdate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class WorkWithDateApp {
    public static void main(String[] args) {
        String dateStr1 = "2020-08-31T00:00:00";
        String dateStr2 = "2020-08-31";

        LocalDate strToLocalDate1 = LocalDate.parse(dateStr1, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDate strToLocalDate2 = LocalDate.parse(dateStr2, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(strToLocalDate1.compareTo(strToLocalDate2));


        LocalDateTime localDateTime1 = LocalDateTime.parse(dateStr1, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(localDateTime1);
        System.out.println(localDateTime1.toLocalDate());

    }
}
