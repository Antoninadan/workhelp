package ua.i.mail100.match;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegExpApp {
    public static void main(String[] args) throws IOException {
        Integer i = 1234567890;


//        List<String> regexps = Arrays.asList("(?=^\\d{4}$)" // pin
//        List<String> regexps = Arrays.asList("^[a-z_][a-z_0-9]*$"); // zalando field
//        List<String> regexps = Arrays.asList("[1-9]+\\d{0,9}"); // integer
//        List<String> regexps = Arrays.asList("^[1-9]([0-9]{0,9})$"); // 10 integer starts without 0
//        List<String> regexps = Arrays.asList("[0-9]+"); // цифри
//        List<String> regexps = Arrays.asList("^[0-9]{1,2}\\.[0-9]{2}$"); // 12.00; 1.00
//        List<String> regexps = Arrays.asList("^([1-9]|1[0-2])$"); // 1-12
//        List<String> regexps = Arrays.asList("^((?=.{6,256}$)(?=.{1,64}@)[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+(\\.[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+)*@[A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?(\\.[A-Za-z0-9]+)*(\\.([A-Za-z0-9]{2,})+)+)*$"); // email dont work
//        List<String> regexps = Arrays.asList("^((?=.{6,256}$)(?=.{1,64}@)[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+(\\.[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+)*@[A-Za-z0-9]([A-Za-z0-9-]*[A-Za-z0-9])?(\\.[A-Za-z0-9]+)*(\\.([A-Za-z0-9]{2,})+)+)*$"); // email dont work
        List<String> regexps = Arrays.asList("^(\\+380[0-9]{9})$"); // phone starts with +380
//        List<String> regexps = Arrays.asList("[^\\x00-\\xFF]"); // printable
//        List<String> regexps = Arrays.asList("^[a-zA-Z]+$"); // definitly letters
//        List<String> regexps = Arrays.asList("[a-zA-Z]+"); // letters should be





        List<String> testsOk = Arrays.asList(
              "dfgd",
                "TTT"
        );

        List<String> testsBad = Arrays.asList(
                "+38064543421",
                "+3806454342158",
                "+580645434154",
                "65df756",
                "bhjb`"
        );

//        Matcher matcher = Pattern.compile(regexps.get(0)).matcher(testsOk.get(0));
//        System.out.println("matcher.find() = " + matcher.find());


        System.out.println("Ok should be = " + testsOk.size() + ":");
        matchAllReport(testsOk, regexps).entrySet().stream().forEach(x -> System.out.println(x.getKey() + "  = " + x.getValue()));

        System.out.println();
        System.out.println("Bad should be empty");
        matchAllReport(testsBad, regexps).entrySet().stream().forEach(x -> System.out.println(x.getKey() + "  = " + x.getValue()));



    }

    public static boolean isMatched(String text, String regexp) {
        Matcher matcher = Pattern.compile(regexp).matcher(text);
        return matcher.find();
    }

    public static List<String> matchAll(String text, String regexp) {
        List<String> results = new ArrayList<>();
        Matcher matcher = Pattern.compile(regexp).matcher(text);
        while (matcher.find()) {
            results.add(text.substring(matcher.start(), matcher.end()));
        }
        return results;
    }


    public static List<String> matchAll(List<String> strings, String regexp) {
        List<String> results = new ArrayList<>();
        for (String each : strings) {
            Matcher matcher = Pattern.compile(regexp).matcher(each);
            if (matcher.find()) {
                results.add(each);
            }
        }
        return results;
    }

    public static List<String> matchAll(List<String> strings, List<String> regexps) {
        List<String> results = new ArrayList<>();
        for (String eachRegexp : regexps) {
            for (String each : strings) {
                Matcher matcher = Pattern.compile(eachRegexp).matcher(each);
                if (matcher.find()) {
                    results.add(each);
                }
            }
        }
        return results;
    }

    public static Map<Integer, String> matchAllReport(List<String> strings, List<String> regexps) {
        Map<Integer, String> results = new HashMap<>();

        for (String eachRegexp : regexps) {
            for (int i = 0; i < strings.size(); i++) {
                Matcher matcher = Pattern.compile(eachRegexp).matcher(strings.get(i));
                if (matcher.find()) {
                    results.put(i + 1, strings.get(i));
                   continue;
                }
            }
        }
        return results;
    }

}
