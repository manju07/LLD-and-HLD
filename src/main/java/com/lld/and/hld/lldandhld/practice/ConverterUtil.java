package com.lld.and.hld.lldandhld.practice;

import java.util.Objects;

public class ConverterUtil {

    public static int stringToInt(String data) throws Exception {
        if (Objects.isNull(data)) {
            throw new Exception("String is null");
        }
        return Integer.parseInt(data);
    }

}
