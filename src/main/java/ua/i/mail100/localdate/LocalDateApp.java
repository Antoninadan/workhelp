package ua.i.mail100.localdate;

import java.time.LocalDate;

public class LocalDateApp {
    public static void main(String[] args) {
        LocalDate smallerDateValue = LocalDate.of(2012, 1, 20);
        LocalDate largerDateValue = LocalDate.of(2011, 1, 20);

        Boolean b1 = false;

        if (smallerDateValue != null && largerDateValue != null) {
            b1 = ((smallerDateValue.isAfter(largerDateValue) == false) || (smallerDateValue.isEqual(largerDateValue)));
        }

        System.out.println("b1 = " + b1);


    }
}
