package com.uWinOPCTjyx.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class JsonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

    private static Gson gson;
    
    /**
     * ���������л�
     * 
     * @param obj
     * @return
     */
    public static String getJsonFromObject(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����л������ַ�
     * 
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T getObjectFromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����л��ַ�����Ϊ��
     * @param json
     * @param valueTypeRef
     * @return
     */
    public static <T> T getObjectFromJson(String json, TypeReference<T> valueTypeRef) {
        try {
            return objectMapper.readValue(json, valueTypeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /** 
     * ��json�ַ�������ת��Ϊpojo����list 
     * @param jsonData 
     * @param beanType 
     * @param <T> 
     * @return 
     */  
    public static <T> List<T> jsonToList(String jsonData, Class<T> beanType){  
        try {  
        	List<T> beanList = objectMapper.readValue(jsonData, new TypeReference<List<T>>() {});   
            return beanList;  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  

    /**
     * @param rescontent
     * @param key
     * @return
     */
    public static String getJsonValue(String rescontent, String key) {
        Map<String, Object> data = fromJson(rescontent);
        return (String) data.get(key);
    }

    public static final <T> T fromJson(final String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    public static final <T> T fromJson(final String json, Type t) {
        return gson.fromJson(json, t);
    }

    /**
     * ��json�����������map����
     * @param json
     * @return
     */
    public static final Map<String, Object> fromJson(final String json) {
        return fromJson(json, Map.class);
    }
}
