package ua.i.mail100.util;

public class TimeFixUtil {
    private final long start;

    public TimeFixUtil() {
        start = System.currentTimeMillis();
    }

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start);
    }

    public void elapsedTimePrint() {
        System.out.println("Elapsed time = " + elapsedTime());
    }
}
