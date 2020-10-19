package ua.i.mail100.features.staticblock;

import java.util.HashMap;
import java.util.Map;

public class ClassWithStaticBlock {
    public static final Map<Integer, String> INDEX_TO_SETTER_MAP = new HashMap<>();

    static {
        int i = 0;
        INDEX_TO_SETTER_MAP.put(i++, "setMainClientId");
        INDEX_TO_SETTER_MAP.put(i++, "setOldClientId");
    }
}
