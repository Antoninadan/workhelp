package ua.i.mail100.rx;

import rx.Observable;

import java.util.Random;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TemperatureApp {
    public static void main(String[] args) {
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        temperatureSensor.temperatureStream().forEach(x ->System.out.println(x.getValue()));
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class TemperatureSensor {
    private final Random rnd = new Random();

    private final Observable<Temperature> dataStream =
            Observable
                    .range(0, Integer.MAX_VALUE)
                    .concatMap(ignore -> Observable
                            .just(1)
                            .delay(rnd.nextInt(100), MILLISECONDS)
                            .map(ignore2 -> this.probe()))
                    .publish()
                    .refCount();

    public Observable<Temperature> temperatureStream() {
        return dataStream;
    }

    private Temperature probe() {
        double actualTemp = 16 + rnd.nextGaussian() * 10;
        return new Temperature(actualTemp);
    }
}

final class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}