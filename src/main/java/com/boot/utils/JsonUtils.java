package com.boot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class JsonUtils {

    private static final Log logger = LogFactory.getLog(JsonUtils.class);

    public static String object2Json(Object object){

        if(object instanceof List){
            JSONArray jsonObject = (JSONArray) JSONArray.toJSON(object);
            return jsonObject.toString();
        }else if(object instanceof String){
            return (String) object;
        }else if(object instanceof Boolean){
            return (String) object;
        }else{
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
            return jsonObject.toString();
        }
    }

    public static Object[] json2Object(String jsonStr, Class<?>[] paramTypes){
        int paramCount = paramTypes.length;
        Object[] objects = new Object[paramCount];
        JSONArray jo = JSON.parseArray(jsonStr);
        for(int i=0; i<paramCount; i++){
            Object c = JSON.parseObject( jo.get(i).toString(),paramTypes[i]);
            objects[i] = c;
        }
        return objects;
    }

    public static Object[] json2Array(String jsonStr) {
        JSONArray jo = JSON.parseArray(jsonStr);
        Object[] objects = new Object[jo.size()];
        for(int i=0; i< jo.size(); i++){
            objects[i] = jo.get(i).toString();
        }
        return objects;
    }
}
