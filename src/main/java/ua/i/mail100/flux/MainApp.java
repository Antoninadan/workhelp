package ua.i.mail100.flux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication(scanBasePackages = {"ua.i.mail100.flux"})
public class MainApp {
    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = new SpringApplicationBuilder()
                .sources(MainApp.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.REACTIVE)
                .run(args);
        Environment env = ctx.getEnvironment();
        log.info("Application '{}-{}' is running!"
                        + "Profile(s): {}"
                        + System.lineSeparator()
                        + "{}",
                env.getProperty("spring.application.name"),
                env.getProperty("spring.application.version"),
                env.getActiveProfiles());
        final ServiceRunner runner = ctx.getBean(ServiceRunner.class);
        runner.start();
    }
}
