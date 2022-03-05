package ua.i.mail100.flux.handler;

import reactor.core.publisher.Mono;
import ua.i.mail100.flux.model.DataWriterContent;

import java.util.List;

public interface DataProcessor {

    <SOURCE> Mono<DataWriterContent<SOURCE>> process(List<SOURCE> dataList);
}
