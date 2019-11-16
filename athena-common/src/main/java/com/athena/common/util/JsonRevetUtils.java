package com.athena.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public class JsonRevetUtils {


    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
    }


    public static <T>String beanToJson(T t) {
        String json = "";
        StringWriter writer = null;
        try {
            writer = new StringWriter();
            objectMapper.writeValue(writer, t);
            json = writer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }


    public static <T>T parseJson(String jsonStr,Class<T> valueType){
        T t =  null;
        try {
            t = (T)objectMapper.readValue(jsonStr,valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T>T parseJson(String jsonStr,TypeReference valueType){
        T t =  null;
        try {
            t = (T)objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }
}
