package ua.i.mail100.transliteration;
import com.ibm.icu.text.Transliterator; //ICU4J library import

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Trans2 {


    public static String TRANSLITERATE_ID = "NFD; Any-Latin; NFC";
    public static String NORMALIZE_ID = "NFD; [:Nonspacing Mark:] Remove; NFC";

    public static void main(String[] args) {
        List<String> strs = Arrays.asList("Привіт", "Привет", "hjælpe", "支援する", "ସାହାଯ୍ୟ କରନ୍ତୁ |", "tree", "Прив іт", "При  вет", "hjæl pe", "支援 する", "Пример с длинным названи№ем спецсимволами № 1.txt");
        ArrayList<String> strsTrans = (ArrayList<String>) strs.stream().collect(Collectors.toList());
//        strsTrans.forEach(x -> x = transliterate(x));
//        strsTrans.forEach(x -> x = x.replaceAll("\\s+", "_"));
        for(int i = 0; i <strs.size(); i++){

            strsTrans.set(i, transliterate(strsTrans.get(i)));
            strsTrans.set(i, strsTrans.get(i).replaceAll("\\s+", "_"));
            strsTrans.set(i, strsTrans.get(i).replaceAll("[^[\\x00-\\x7F\\xA0-\\xFF]+$]+", "_"));


        System.out.println(String.format("%s = %s", strs.get(i),strsTrans.get(i)));
        }

    }

    /**
     * Returns the transliterated string to convert any charset to latin.
     */
    public static String transliterate(String input) {
        Transliterator transliterator = Transliterator.getInstance(TRANSLITERATE_ID ); //+ "; " + NORMALIZE_ID);
        String result = transliterator.transliterate(input);
        return result;
    }

}
