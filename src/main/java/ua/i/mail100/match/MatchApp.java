package ua.i.mail100.match;

import ua.i.mail100.settings.Settings;
import ua.i.mail100.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchApp {
    public static void main(String[] args) throws IOException {


        List<String> regexps = Arrays.asList("(?i)\\s\\[dbo\\]", // <пробел-таб-новая строка>[dbo]
                "(?i)\\sdbo", // <пробел-таб-новая строка>dbo
                "\\.\\.",  // ..
//                "update(\\s)(\\w)\\s"
//                "(?i)[^\\s]update\\s+(w+(?!8))"
                "(?i)(update|insert|insert into|join|from|exec)[\\s]+(((?!\\.).)*$)"
//                "(?i)(update|insert|insert into|join|from|exec)[\\s]+^((?!#|@)((?!\\.).)*$)"  // ^(?!#|@)(((?!\.).)*$)
//                "^((?!#|@)((?!\\.).)*$)"  // ^(?!#|@)(((?!\.).)*$)
//                "(?i)update([\\s]+)[^8]"
//                "[^8]"
//                        "q{1,}(?!werty)"
//                update, insert, join, from, exec <пробел-таб-новая строка> <символ окрім .>  <пробел-таб-новая строка>

        );

        List<String> tests = Arrays.asList(
                "@dfg  ",
                "d@fg  ",
                "d.fg  ",
                "c xbv bn    UPdate     bad.    ",
                "c xbv bn    UPdate     @bad.    ",
                "c xbv bn    UPdate     @bad    ",
                "c xbv bn    UPdate     bad.bc    ",
                "c xbv bn    UPdate     ..good    ",
                "c xbv bn    insert     ..goodinsert    ",
                "c xbv bn    UPdate     bad.bc",
                "c xbv bn    UPdate",
                "c xbv bn    qt",
                "c xbv bn    qwerty",
                "c xbv bn    qqwerty",
                "update     good    ",
                "insert     goodinsert    ",
                "c xbv bn    update     good    ",
                "c xbv bn    update     good");
        matchAllReport(tests, regexps).entrySet().stream().forEach(x -> System.out.println(x.getKey() + "  = " + x.getValue()));

//        List<File> files = FileUtil.getAllFilesInPath("C:\\Java\\SQL\\ORION\\orion2\\Procedures");
//
//        for(File each:files){
//            List<String> strings = Files.readAllLines(each.toPath(), StandardCharsets.ISO_8859_1);
//            System.out.println(Settings.LINE_SEP + each.getName());
//            matchAllReport(strings, regexps).entrySet().stream().forEach(x  -> System.out.println(x.getKey() + "  = "+ x.getValue()));
//        }

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
