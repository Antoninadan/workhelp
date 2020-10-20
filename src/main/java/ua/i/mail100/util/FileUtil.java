package ua.i.mail100.util;

import ua.i.mail100.settings.Settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileUtil {

    public static void appendTo(List<String> strLines, String pathName, String fileName) throws IOException {
        Path path = Paths.get(pathName + Settings.FILE_SEP + fileName);
        Files.write(path, strLines, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<String> read(String pathName, String fileName) throws IOException {
        Path path = Paths.get(pathName + Settings.FILE_SEP + fileName);
        return Files.readAllLines(path, StandardCharsets.ISO_8859_1);
    }

    public static Boolean isLineSeparatorAtEnd(String pathName, String fileName) throws IOException {
        String path = pathName + Settings.FILE_SEP + fileName;
        File file = new File(path);
        byte[] bytes = Files.readAllBytes(file.toPath());
        int size = bytes.length;

        if (((bytes[size - 2]==13 && bytes[size - 1]==10)) || ( bytes[size - 1]==13)) {
            return true;
        }
        return false;
    }
}