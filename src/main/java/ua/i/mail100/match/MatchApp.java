package ua.i.mail100.match;

import ua.i.mail100.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchApp {
    public static void main(String[] args) throws IOException {

        List<String> strings = FileUtil.read("files", "up_auto_orion_confirmation_orchestrator_orig_1.000.sql");

        String regexp = " dbo.";
        matchAll(strings, regexp).stream().forEach(x -> System.out.println(x));

        String regexp2 = " ..";
        matchAll(strings, regexp2).stream().forEach(x -> System.out.println(x));


        //        List<String> strings = FileUtil.read("files", "up_auto_orion_limits_table_1.004.sql");

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


}
