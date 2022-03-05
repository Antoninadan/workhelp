package ua.i.mail100.flux.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import reactor.core.publisher.Flux;
import ua.i.mail100.flux.handler.DataProcessor;
import ua.i.mail100.flux.handler.Reader;
import ua.i.mail100.flux.handler.Writer;
import ua.i.mail100.flux.model.DataWriterContent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class EventScheduler<DTO> implements Lifecycle {

    private static final String FILE_PATH_URL = "C:\\Java\\workhelp\\src\\main\\java\\ua\\i\\mail100\\flux";
    private static final String FILE_NAME = "res1.txt";

    private static final int MAX_PROCESS_BATCH_SIZE = 33;  // max in minute
    private static final int PROCESS_BATCH_SIZE = 10;      // write 10
    private static final int READ_FETCH_SIZE = 15;         // read po 15
    private static final int SCHEDULE_INTERVAL = 60;         // 60


    private final AtomicBoolean canWork = new AtomicBoolean();
    private final AtomicBoolean isRunning = new AtomicBoolean();
    private final AtomicInteger countInScheduleInterval = new AtomicInteger(0);
    private ScheduledExecutorService scheduler;

    private Reader<DTO> reader;
    private Writer<DTO> writer;
    private DataProcessor processor;
    private String name;

    public EventScheduler() {
    }

    @Override
    public void start() {
        isRunning.set(true);
        canWork.set(true);
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> countInScheduleInterval.set(0),
                0,
                SCHEDULE_INTERVAL,
                TimeUnit.SECONDS);
        while (canWork.get()) {
            while (canWork.get() && countInScheduleInterval.get() >= MAX_PROCESS_BATCH_SIZE) {
                sleep(200);
            }
            try {
                final List<DTO> dataList = reader.get(READ_FETCH_SIZE);
                log.info("{} read data from file, size={}", "?", dataList.size());
                countInScheduleInterval.getAndAdd(dataList.size());
                if (dataList.size() < READ_FETCH_SIZE) {
                    countInScheduleInterval.set(MAX_PROCESS_BATCH_SIZE);
                }

//                Flux.fromIterable(dataList)
//                        .window(PROCESS_BATCH_SIZE)
//                        .map(Flux::collectList)
//                        .subscribe(mono -> mono.subscribe(writer.write(new DataWriterContent<DTO>()
//                                        .setData(dataList)
//                                        .setPathName(FILE_PATH_URL)
//                                        .setFileName(FILE_NAME)
//                                )
//                                )
//                        );

            } catch (Exception ex) {
                log.error(ex.getMessage(), ex);
                sleep(1000);
            }
        }
        log.info("service '{}' finished", "??");
        isRunning.set(false);
    }

    @Override
    public void stop() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
        canWork.set(false);
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    public EventScheduler<DTO> setReader(Reader<DTO> reader) {
        this.reader = reader;
        return this;
    }

    public EventScheduler<DTO> setProcessor(DataProcessor processor) {
        this.processor = processor;
        return this;
    }

    public EventScheduler<DTO> setWriter(Writer<DTO> dataWriter) {
        this.writer = dataWriter;
        return this;
    }


    public EventScheduler<DTO> setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public static void sleep(final long timeoutInMs) {
        try {
            TimeUnit.MILLISECONDS.sleep(timeoutInMs);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
