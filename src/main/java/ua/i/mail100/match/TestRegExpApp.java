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
        List<String> regexps = Arrays.asList("^[1-9]([0-9]{0,9})$"); // 10 integer starts without 0


        List<String> testsOk = Arrays.asList(
                "1234567890",
                "1234567"
        );

        List<String> testsBad = Arrays.asList(
                "12345678901",
                "1234567890153452",
                "0",
                "09",
                "аафф"
        );

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
