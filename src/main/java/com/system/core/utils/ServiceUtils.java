package com.system.core.utils;

import com.boot.utils.JsonUtils;
import com.system.core.StaticCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.MethodInvocationException;

import javax.el.MethodNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceUtils {
    private static final Log logger = LogFactory.getLog(ServiceUtils.class);

    public static Object callService(Object service, String serviceName, String funcName, Map<String, String> params) throws MethodInvocationException, JSONException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (params == null) {
            params = new LinkedHashMap<>();
        }
        Method method = getInvokeMethod(service, funcName);
        if (method == null) {
            logger.info("请求无对应服务 " + serviceName + "." + funcName);
            throw new MethodNotFoundException();
        } else {
            //if (logger.isInfoEnabled() && method.getAnnotation(DisabledLog.class) == null) {
            if (logger.isInfoEnabled()) {
                StringBuilder sb = new StringBuilder();
                Iterator var8 = ((Map)params).keySet().iterator();

                while(var8.hasNext()) {
                    String key = (String)var8.next();
                    sb.append(key).append("=").append((String)((Map)params).get(key)).append("&");
                }
                logger.info("请求的服务 : " +serviceName+"/"+funcName);
                logger.info("请求的参数requestParameters : " + sb.toString());
            }

            String paramsStr = (String)((Map)params).get("params");
            Object[] paramObjs = JsonUtils.json2Object(paramsStr,method.getParameterTypes());

//            if (!hasGatewayAnnotation(service)) {
//                logger.info("there is no gateway annotation : " + serviceName);
//                throw new PermissionException();
//            } else if (!hasPermission(service, method, "service", logger, frameworkSession)) {
//                throw new PermissionException("没有访问方法的权限:" + serviceName + "." + funcName);
//            } else {
//                int sessionAttributeAnnotationCount = getSessionAttributeAnnotationCount(method);
//                Object[] objects = Helper.json2ObjectArray(frameworkJsonService, array, method, array.length(), (Pageable)null, sessionAttributeAnnotationCount > 0, service);
//                if (!isConsumerSide(service)) {
//                    DBContextHolder.setDBTypeByService(getBeanClassName(service), funcName);
//                }

                Object data = method.invoke(service, paramObjs);
//                if (!isConsumerSide(service)) {
//                    DBContextHolder.clearDBType();
//                }

                //if (logger.isInfoEnabled() && method.getAnnotation(DisabledLog.class) == null && !(data instanceof File) && !(data instanceof InputStream)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(data == null ? "" : data.toString());
                    logger.info("从controller返回的结果 : " + sb.substring(0, sb.length() > 100 ? 100 : sb.length()));
                //}

                return data;
//            }
        }
    }

    public static Method getInvokeMethod(Object target, String methodName) {
        String key = String.format("class:%s_method:%s_param:%s,%s", ServiceUtils.class.getName(), "getInvokeMethod", target.getClass().getName(), methodName);
        Method result = (Method)StaticCache.getInstance().get(key);
        if (result == null) {

            Method[] methodList = target.getClass().getDeclaredMethods();
            for(int i=0; i< methodList.length; i++){
                Method method = methodList[i];
                if (methodName.equals(method.getName())) {
                    result = method;
                    StaticCache.getInstance().put(key, method);
                    break;
                }
            }

        }

        return result;
    }



}
