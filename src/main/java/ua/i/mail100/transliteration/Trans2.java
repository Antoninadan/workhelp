package ua.i.mail100.transliteration;
import com.ibm.icu.text.Transliterator; //ICU4J library import

import java.util.Arrays;
import java.util.List;

public class Trans2 {


    public static String TRANSLITERATE_ID = "NFD; Any-Latin; NFC";
    public static String NORMALIZE_ID = "NFD; [:Nonspacing Mark:] Remove; NFC";

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("Привіт", "Привет", "hjælpe", "支援する", "ସାହାଯ୍ୟ କରନ୍ତୁ |", "tree");
       strs.forEach(x -> System.out.println(String.format("%s = %s", x, transliterate(x))));

    }

    /**
     * Returns the transliterated string to convert any charset to latin.
     */
    public static String transliterate(String input) {
        Transliterator transliterator = Transliterator.getInstance(TRANSLITERATE_ID + "; " + NORMALIZE_ID);
        String result = transliterator.transliterate(input);
        return result;
    }

}
