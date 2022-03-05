package ua.i.mail100.flux.handler.impl;

import lombok.extern.slf4j.Slf4j;
import ua.i.mail100.flux.handler.Writer;
import ua.i.mail100.flux.model.DataWriterContent;
import ua.i.mail100.util.FileUtil;

@Slf4j
public class WriterFileOne implements Writer<String> {
    @Override
    public void write(DataWriterContent<String> content) {
        try {
        FileUtil.appendTo(content.getData(), content.getPathName(), content.getFileName());

        }
        catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
