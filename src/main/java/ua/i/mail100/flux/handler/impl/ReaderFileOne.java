package ua.i.mail100.flux.handler.impl;

import ua.i.mail100.flux.handler.Reader;
import ua.i.mail100.util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderFileOne implements Reader<String> {
    private static final String FILE_PATH_URL = "C:\\Java\\workhelp\\src\\main\\java\\ua\\i\\mail100\\flux";
    private static final String FILE_NAME = "text.txt";

    @Override
    public List<String> get(int fetchSize) throws IOException {
        List<String> list = FileUtil.read(FILE_PATH_URL, FILE_NAME);
        List<String> result = new ArrayList<>();
        for(int i=0; i<Math.min(fetchSize, list.size()); i++)
            result.add(list.get(i));
        return result;
    }
}
