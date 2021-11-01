package ua.i.mail100.math;

public class MathOperationApp {
    public static void main(String[] args) {
//        Long ms = 1200L;
        Long ms = 186400003L;
        Integer msInt = ms.intValue();
        Long oneDayMs = 86400000L;
        Integer oneDayMsInt =  oneDayMs.intValue();
        Integer x = msInt / oneDayMsInt;
        System.out.println("x = " + x);
    }

}
