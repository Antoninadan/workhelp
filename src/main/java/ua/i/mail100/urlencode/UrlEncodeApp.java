package ua.i.mail100.urlencode;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriUtils;

public class UrlEncodeApp {
    public static void main(String[] args) {
        String str = "{\"parameters\":[{\"field_id\":\"4f2f2b8e-8fc3-4926-82cf-de2eed203b01\",\"is_required\":false,\"value\":\"1234567\"}]}";
        String strResult = encodePath(str);

        System.out.println("strResult = " + strResult);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("p", str);

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        factory.builder()
                .scheme("hhtp")
                .host("k")
                .path("")
                .queryParam("is_multilateral", encodePath("1"))
                .queryParam("is_internal", encodePath("0"))
                .queryParam("share_to", encodePath("40ca484c-36ac-43ae-a7c9-4ccbdadae59c"))
                .queryParam("category", encodePath("5"))
                .queryParam("title", encodePath("Тесткейсы создания документа Вчасно"))
                .queryParam("is_ttn", encodePath("0"))
                .queryParam("recipient_edrpou", encodePath("77777777"))
                .queryParam("parent_id", encodePath("40ca484c-36ac-43ae-a7c9-4ccbdadae59c"))
                .queryParam("doc_number", encodePath(""))
                .queryParam("date_document", encodePath("20211010"))
                .queryParam("parallel_review", "true")
                .queryParam("is_required_review", "true")
                .queryParam("vendor_id", encodePath("1b04746b-e953-4b87-ae0a-117c0d29c9e3"))
//                .queryParam("flows","[\n" +
//                        "    {\"edrpou\": \"44444444\", \"emails\": [\"admin0@fuib.com\"], \"order\": 0, \"sign_num\": 1},\n" +
//                        "    {\"edrpou\": \"55555555\", \"emails\": [\"admin1@fuib.com\"], \"order\": 1, \"sign_num\": 1},\n" +
//                        "    {\"edrpou\": \"77777777\", \"emails\": [\"admin2@fuib.com\"], \"order\": 2, \"sign_num\": 1},\n" +
//                        "    {\"edrpou\": \"77777667\", \"emails\": [\"admin3@fuib.com\"], \"order\": 3, \"sign_num\": 1},\n" +
//                        "    {\"edrpou\": \"77777667\", \"emails\": [\"admin3@fuib.com\"], \"order\": 4, \"sign_num\": 2}\n" +
//                        "]")
                .build();
    }

    private static String encodePath(String str) {
        str = UriUtils.encodePath(str, "UTF-8");
        return str;
    }
}





