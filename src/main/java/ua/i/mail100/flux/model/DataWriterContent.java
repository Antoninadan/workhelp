package ua.i.mail100.flux.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class DataWriterContent<T> {
    List<T> data;
    String pathName;
    String fileName;

    public DataWriterContent<T> setData(List<T> data){
        this.data = data;
        return this;
    }

    public DataWriterContent<T> setPathName(String pathName){
        this.pathName = pathName;
        return this;
    }

    public DataWriterContent<T> setFileName(String fileName){
        this.fileName = fileName;
        return this;
    }
}
