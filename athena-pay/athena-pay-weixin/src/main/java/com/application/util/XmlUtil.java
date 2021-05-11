package com.application.util;


import java.util.Map;

public class XmlUtil {

    public static String toXml(Map<String, Object> map){
        StringBuilder builder = new StringBuilder();
        builder.append("<xml>");
        String format = "<%s>%s</%s>";
        map.forEach((key, value) -> {
            builder.append(String.format(format, key, value, key));
        });
        builder.append("</xml>");
        return builder.toString();
    }
    

}
