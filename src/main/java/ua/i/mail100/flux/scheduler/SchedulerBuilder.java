package ua.i.mail100.flux.scheduler;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.i.mail100.flux.handler.impl.ReaderFileOne;
import ua.i.mail100.flux.handler.impl.WriterFileOne;

import java.util.List;

/**
 * @author : okolot
 * @created : 27.10.2021
 **/
@Component
@AllArgsConstructor
public class SchedulerBuilder {
    private List<EventScheduler<?>> schedulers;

    public List<EventScheduler<?>> build() {

        schedulers.add(
                 new EventScheduler<String>()
                        .setReader(new ReaderFileOne())
//                         .setProcessor()
                        .setWriter(new WriterFileOne())
                        .setName("file1"));
            return schedulers;
    }

    public Integer getSize(){
        return schedulers.size();
    }


}
