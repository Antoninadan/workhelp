package ua.i.mail100.flux.handler;
import java.io.IOException;
import java.util.List;

public interface Reader<T> {
    List<T> get(int fetchSize) throws IOException;
}
