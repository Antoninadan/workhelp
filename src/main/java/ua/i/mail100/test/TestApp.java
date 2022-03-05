package ua.i.mail100.test;

public class TestApp {
    public static void main(String[] args) {
        String s = "{\n" +
                "  \"id\": 24667306,\n" +
                "  \"number\": \"14654398\",\n" +
                "  \"statusId\": 1,\n" +
                "  \"organizerName\": \"АТ \\\"ПУМБ\\\"\",\n" +
                "  \"lots\": [\n" +
                "    {\n" +
                "      \"id\": 2615392,\n" +
                "      \"number\": 1,\n" +
                "      \"nomenclatureId\": 14542966,\n" +
                "      \"additionalFeatures\": [],\n" +
                "      \"title\": \"test_lot2\",\n" +
                "      \"quantity\": 1.00000,\n" +
                "      \"unitCode\": \"KGM\",\n" +
                "      \"initialRate\": 0.00,\n" +
                "      \"bidStep\": 0.00,\n" +
                "      \"description\": \"test2\",\n" +
                "      \"addressId\": null\n" +
                "    }\n" +
                "  ],\n" +
                "  \"tenderPublishedSchedule\": {\n" +
                "    \"contactId\": 5596153,\n" +
                "    \"scheduledDate\": \"2021-11-28T08:00:06.293\",\n" +
                "    \"isScheduled\": false\n" +
                "  },\n" +
                "  \"hasRoute\": true,\n" +
                "  \"biddingType\": {\n" +
                "    \"biddingTypeId\": 3,\n" +
                "    \"title\": \"Открытый тендер на продажу\",\n" +
                "    \"biddingTypeCode\": \"103\",\n" +
                "    \"isSale\": true,\n" +
                "    \"isPublic\": true,\n" +
                "    \"initialRateAvailable\": true,\n" +
                "    \"bidStepAvailable\": true,\n" +
                "    \"prolongationRuleAvailable\": true,\n" +
                "    \"reducedPriceAvailable\": true,\n" +
                "    \"analogAvailable\": true,\n" +
                "    \"allowRaising\": false,\n" +
                "    \"isMultistage\": false,\n" +
                "    \"tenderingForPercentage\": false\n" +
                "  },\n" +
                "  \"additionalFeatures\": [],\n" +
                "  \"tenderPeriodEnd\": \"2021-11-29T17:44:06.293\",\n" +
                "  \"deliveryPeriodStart\": \"2021-11-30T08:44:06.293\",\n" +
                "  \"deliveryPeriodEnd\": \"2021-11-30T12:44:06.293\",\n" +
                "  \"categoryCode\": \"03111000-2\",\n" +
                "  \"title\": \"test_tender1\",\n" +
                "  \"description\": \"test_tender2\",\n" +
                "  \"biddingTypeId\": 3,\n" +
                "  \"prolongationRuleCode\": null,\n" +
                "  \"currencyCode\": \"UAH\",\n" +
                "  \"currencyOwner\": 0,\n" +
                "  \"vatInclude\": true,\n" +
                "  \"allowRaising\": false,\n" +
                "  \"allLotsRequiredBid\": true,\n" +
                "  \"requiredAttachmentBid\": true,\n" +
                "  \"publicTenderResults\": true,\n" +
                "  \"hideBestPrice\": true,\n" +
                "  \"bidStepType\": 0,\n" +
                "  \"criteryCardId\": 16397,\n" +
                "  \"testMode\": true,\n" +
                "  \"allowPhys\": false,\n" +
                "  \"contactPersonId\": 5596153,\n" +
                "  \"additionalContactPersons\": [\n" +
                "    {\n" +
                "      \"contactId\": 5596153,\n" +
                "      \"name\": \"Маркин Богдан Сергеевич\",\n" +
                "      \"email\": \"bogdan.markin@fuib.com\",\n" +
                "      \"phone\": \"380957300665\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"scheduledStart\": null\n" +
                "}";

        if (s.trim().startsWith("{")) {
            System.out.println(1);
        } else if (s.trim().startsWith("[")) {
            System.out.println(2);
        } else if (!s.trim().startsWith("\"") && !s.trim().matches("-?(?:0|[1-9]\\d*)(?:\\.\\d+)?(?:[eE][+-]?\\d+)?")) {
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }
}


