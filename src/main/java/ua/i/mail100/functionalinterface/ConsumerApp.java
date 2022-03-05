package ua.i.mail100.functionalinterface;

import java.util.function.Consumer;

public class ConsumerApp {


    public static void main(String[] args) {
        Consumer<String> printUpperCase = str -> System.out.print(str.toUpperCase());
        printUpperCase.accept("first");

        System.out.println();

        Consumer<String> printLowerCase = str -> System.out.print(str.toLowerCase());
        printUpperCase.andThen(printLowerCase).andThen(printLowerCase).accept("second");
    }

}
