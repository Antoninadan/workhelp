package ua.i.mail100.workwithstring;


import org.apache.commons.lang.StringUtils;

public class WorkWithStringApp {
    public static void main(String[] args) {
        String active = "I";
        String block = null;

        System.out.println(getCardStatusByActiveAndBlock(active, block));

        String a = String.format("%011d", 1);
        System.out.println("a = " + a);
        

    }

    private static String getCardStatusByActiveAndBlock(String active, String block) {
        String activeTemp = "";
        String blockTemp = "";
        if (active!= null) activeTemp = active.toUpperCase().trim();
        if (block!= null) blockTemp = block.toUpperCase().trim();

        if (blockTemp.equals("C")) return "Blocked";
        else if (activeTemp.equals("A") && StringUtils.isBlank(blockTemp)) return "Active";
        else if (activeTemp.equals("A") && blockTemp.equals("B")) return "TemporaryBlocked";
        else if (activeTemp.equals("I") && StringUtils.isBlank(blockTemp)) return "New";
        else return "NonActive";
    }
}
