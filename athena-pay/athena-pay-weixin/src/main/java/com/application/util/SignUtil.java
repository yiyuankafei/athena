package com.application.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

public class SignUtil {

    public static String generateSign (Map<String, Object> params, String key) throws Exception {
        Set<String> keySet = params.keySet();
        List<String> keyList = new ArrayList<>(keySet);
        Collections.sort(keyList);
        StringBuilder builder = new StringBuilder();
        keyList.forEach(item -> {
            builder.append(item).append("=").append(params.get(item)).append("&");
        });
        String signParam = builder.toString();
        signParam = signParam.substring(0, signParam.length() - 1) + "&key=" + key;

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(signParam.getBytes());
        String sign = new BigInteger(1, md.digest()).toString(16).toUpperCase();
        return sign;
    }

}
