package ua.i.mail100.flux.handler;

import ua.i.mail100.flux.model.DataWriterContent;

public interface Writer<T> {
    void write(DataWriterContent<T> content);
}
