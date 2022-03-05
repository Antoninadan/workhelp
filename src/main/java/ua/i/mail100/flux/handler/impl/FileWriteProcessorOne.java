//package ua.i.mail100.flux.handler.impl;
//
//import reactor.core.publisher.Mono;
//import ua.i.mail100.flux.handler.DataProcessor;
//import ua.i.mail100.flux.model.DataWriterContent;
//
//import java.util.List;
//
//public class FileWriteProcessorOne implements DataProcessor {
//    private static final String FILE_PATH_URL = "C:\\Java\\workhelp\\src\\main\\java\\ua\\i\\mail100\\flux";
//    private static final String FILE_NAME = "res1.txt";
//
//    @Override
//    public <SOURCE> Mono<DataWriterContent<SOURCE>> process(List<SOURCE> dataList) {
//        return Mono.just(new DataWriterContent<SOURCE>()
//                .setData(dataList)
//                .setPathName(FILE_PATH_URL)
//                .setFileName(FILE_NAME);
//        );
//    }
//}
