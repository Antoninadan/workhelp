package ua.i.mail100.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;
import ua.i.mail100.flux.scheduler.EventScheduler;
import ua.i.mail100.flux.scheduler.SchedulerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author : okolot
 * @created : 27.10.2021
 **/
@Component
@Slf4j
public class ServiceRunner implements Lifecycle {

    private final SchedulerBuilder schedulerBuilder;
    private final List<EventScheduler<?>> schedulers = new ArrayList<>();
    private boolean isRunning = false;

    public ServiceRunner(final SchedulerBuilder schedulerBuilder) {
        this.schedulerBuilder = schedulerBuilder;
    }

    @Override
    public void start() {
        if (!isRunning) {
            schedulers.addAll(schedulerBuilder.build());
            final ExecutorService eService = Executors.newFixedThreadPool(schedulerBuilder.getSize());
            schedulers.forEach(scheduler -> {
                eService.execute(scheduler::start);
                log.info("service {} started", scheduler.getName());
            });
            isRunning = true;
            log.info("services started");
        }
    }

    @Override
    public void stop() {
        schedulers.forEach(EventScheduler::stop);
        try {
            while (!schedulers.isEmpty()) {
                TimeUnit.MILLISECONDS.sleep(500);
                schedulers.removeIf(scheduler -> !scheduler.isRunning());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        isRunning = false;
        log.info("services finished");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }
}
