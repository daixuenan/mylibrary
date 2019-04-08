package com.dai.mylibrary.utils.format;

import java.util.UUID;

public class Utils {

    //get UUID
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
