package ua.i.mail100.transliteration;

import com.ibm.icu.text.Transliterator;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.util.Enumeration;

public class Trans {
    public static final String CYRILLIC_TO_LATIN = "Cyrillic-Latin";

    public static void main(String[] args) {
        String input = "Привіт Мир";

        Transliterator toLatin= Transliterator.getInstance(CYRILLIC_TO_LATIN);
        String resul = toLatin.transliterate(input);
        System.out.println("resul = " + resul);

        Enumeration<String> availableIDs = Transliterator.getAvailableIDs();
        availableIDs.asIterator().forEachRemaining(id -> {
            Transliterator toLatinTrans = Transliterator.getInstance(id);
            String result = toLatinTrans.transliterate(input);
            System.out.println(id + " : " +input + " -> " + result);
        });
    }
}
