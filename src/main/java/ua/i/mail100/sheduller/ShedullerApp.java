package ua.i.mail100.sheduller;

import ua.i.mail100.util.FileUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ShedullerApp {
    private static final String FILE_PATH_URL = "C:\\Java\\workhelp\\src\\main\\java\\ua\\i\\mail100\\sheduller";
    private static final String FILE_BODY_NAME = "body.txt";
    private static final String FILE_PERCENTS_NAME = "percents.txt";
    private static final String FILE_COMMISSION_NAME = "commission.txt";
    private static final String FILE_PREPAID_EXPENSE_NAME = "prepaid_expense.txt";


    public static void main(String[] args) throws IOException {
        LocalDate startDate = LocalDate.of(2021, 9, 9);
//        getHpText(startDate, 1);
//        getApiText(startDate, 1);
//        print4HpFromFile(startDate, 5);
        print4ApiFromFile(startDate, 3);
    }

    public static void getHpText(LocalDate startDate, int yearNumber) {
        List<LocalDate> yearList = getYearList(startDate, yearNumber);
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"utf-16\"?> \n" +
                "<CalendarImport>");
        for (int i = 0; i < yearList.size() - 1; i++) {
            result.append(
                    "\n" +
                            print4Hp(yearList.get(i)));
        }
        result.append("\n</CalendarImport>");
        System.out.println(result);
    }

    public static void getApiText(LocalDate startDate, int yearNumber) {
        List<LocalDate> yearList = getYearList(startDate, yearNumber);
        StringBuilder result = new StringBuilder();
        result.append("\"schedule\": [");
        for (int i = 0; i < yearList.size() - 2; i++) {
            result.append(
                    "\n" +
                            print4Api(yearList.get(i)) +
                            ",");
        }
        result.append("\n" +
                print4Api(yearList.get(yearList.size() - 1)) + "]");
        System.out.println(result);
    }

    public static Long dateToLong(LocalDate localDate) {
        return ChronoUnit.DAYS.between(LocalDate.of(1900, 1, 1), localDate);
    }

    public static LocalDate beforeHoliday(LocalDate localDate) {
        if (localDate.getDayOfWeek().ordinal() == 5) return localDate.minusDays(1);
        if (localDate.getDayOfWeek().ordinal() == 6) return localDate.minusDays(2);
        return localDate;
    }

    public static String getNameLocalDateWithWeekDay(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter) + " " + localDate.getDayOfWeek().name();
    }

    public static String getNameLocalDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static String print4HpWithComment(LocalDate localDate) {
        Long l = dateToLong(localDate);
        String result = String.format("<Events DayDate=\"%s\" Refund=\"6250.6\" Percent=\"268.4\" GetCommission=\"70.75\" FutureExpenses=\"-1980\"/>", l);
        return result + "       --" + getNameLocalDateWithWeekDay(localDate);
    }

    public static String print4HpFromFile(LocalDate localDate, int yearNumber) throws IOException {
        List<String> bodyStringList = FileUtil.read(FILE_PATH_URL, FILE_BODY_NAME);
        List<String> percentsStringList = FileUtil.read(FILE_PATH_URL, FILE_PERCENTS_NAME);
//        List<String> commissionStringList = FileUtil.read(FILE_PATH_URL, FILE_COMMISSION_NAME);
        List<String> prepaidExpenseStringList = FileUtil.read(FILE_PATH_URL, FILE_PREPAID_EXPENSE_NAME);
        List<Double> bodyList = bodyStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<Double> percentsList = percentsStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
//        List<Double> commissionList = commissionStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<Double> prepaidExpenseList = prepaidExpenseStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<LocalDate> yearList = getYearList(localDate, yearNumber);
        if (bodyList.size() != percentsStringList.size()) throw new RuntimeException("Bad numbers");

        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"utf-16\"?> \n" +
                "<CalendarImport>");
        for (int i = 0; i < yearList.size(); i++) {
            Long l = dateToLong(yearList.get(i));
            result.append(
                    "\n" +
                            String.format("<Events DayDate=\"%s\" Refund=\"%f\" Percent=\"%f\" FutureExpenses=\"%f\"/>",
                                    l, bodyList.get(i), percentsList.get(i), prepaidExpenseList.get(i)));
        }
        result.append("\n</CalendarImport>");
        System.out.println(result);
        return result.toString();
    }

    public static String print4ApiFromFile(LocalDate localDate, int yearNumber) throws IOException {
        List<String> bodyStringList = FileUtil.read(FILE_PATH_URL, FILE_BODY_NAME);
        List<String> percentsStringList = FileUtil.read(FILE_PATH_URL, FILE_PERCENTS_NAME);
//        List<String> commissionStringList = FileUtil.read(FILE_PATH_URL, FILE_COMMISSION_NAME);
        List<String> prepaidExpenseStringList = FileUtil.read(FILE_PATH_URL, FILE_PREPAID_EXPENSE_NAME);
        List<Double> bodyList = bodyStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<Double> percentsList = percentsStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
//        List<Double> commissionList = commissionStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<Double> prepaidExpenseList = prepaidExpenseStringList.stream().map(x -> Double.valueOf(x)).collect(Collectors.toList());
        List<LocalDate> yearList = getYearList(localDate, yearNumber);
        if (bodyList.size() != percentsStringList.size()) throw new RuntimeException("Bad numbers");


        StringBuilder result = new StringBuilder();
        result.append("\"schedule_payments\": [");
        for (int i = 0; i < yearList.size() - 1; i++) {
            String l = getNameLocalDate(yearList.get(i));
            result.append(
                    String.format(Locale.ROOT,
                            "{\n" +
                                    "            \"date\": \"%s\",\n" +
                                    "            \"repayment_body\": %f,\n" +
                                    "            \"repayment_percents\": %f,\n" +
                                    "            \"prepaid_expense\": %f\n" +
                                    "        },",
                            l, bodyList.get(i), percentsList.get(i), prepaidExpenseList.get(i)));
        }
        int size = yearList.size();
        String l = getNameLocalDate(yearList.get(size - 1));
        result.append(String.format(Locale.ROOT,
                "{\n" +
                        "            \"date\": \"%s\",\n" +
                        "            \"repayment_body\": %f,\n" +
                        "            \"repayment_percents\": %f,\n" +
                        "            \"prepaid_expense\": %f\n" +
                        "        }]",
                l, bodyList.get(size - 1), percentsList.get(size - 1), prepaidExpenseList.get(size - 1)));


        System.out.println(result);
        return result.toString();
    }


    public static String print4Hp(LocalDate localDate) {
        Long l = dateToLong(localDate);
        String result = String.format("<Events DayDate=\"%s\" Refund=\"6250.6\" Percent=\"268.4\" GetCommission=\"70.75\" FutureExpenses=\"-1980\"/>", l);
        return result;
    }

    public static String print4Api(LocalDate localDate) {
        String l = getNameLocalDate(localDate);
        String result = String.format(
                "{\n" +
                        "            \"date\": \"%s\",\n" +
                        "            \"repayment_body\": 6250.6,\n" +
                        "            \"repayment_percents\": 268.4,\n" +
                        "            \"repayment_commission\": 70.75,\n" +
                        "            \"prepaid_expense\": -1980\n" +
                        "        }", l);
        return result;
    }

    public static List<LocalDate> getYearList(LocalDate startDate, int yearNumber) {
        List<LocalDate> result = new ArrayList<>();
        LocalDate localDate = startDate;
        beforeHoliday(startDate);
        for (int i = 1; i <= yearNumber * 12; i++) {
            localDate = startDate.plusMonths(i);
            LocalDate dateCorrectHoliday = beforeHoliday(localDate);
            result.add(dateCorrectHoliday);
        }
        return result;
    }


}
