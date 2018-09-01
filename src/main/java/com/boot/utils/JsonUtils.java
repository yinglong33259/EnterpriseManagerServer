package com.boot.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class JsonUtils {
    public static String object2Json(Object object){

        JSONObject result = new JSONObject();

        if(object instanceof List){
            JSONArray jsonObject = (JSONArray) JSONArray.toJSON(object);
            result.put("data", jsonObject);
        }else if(object instanceof String){
            result.put("data", object);
        }else{
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
            result.put("data", jsonObject);
        }
        return result.toString();
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
}
