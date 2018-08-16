package com.system.core.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.core.utils.ServiceUtils;
import com.boot.entity.ResponseResult;
import com.boot.utils.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {
    private static final Log logger = LogFactory.getLog(ServiceController.class);
    @Autowired(
            required = true
    )
    private ApplicationContext context;
    @Autowired(
            required = true
    )

    public ServiceController() {
    }

    @RequestMapping(
            value = {"/service/{serviceName}/{funcName}"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public String doService(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName, @PathVariable String funcName) throws BeansException, JSONException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {

        } else {

        }
        ResponseResult result = new ResponseResult();
        if (!this.context.containsBean(serviceName)) {
            logger.info("请求无对应服务 " + serviceName + "." + funcName);
            response.setHeader("error_code", "104");
            return null;
        } else {
            Object service = this.context.getBean(serviceName);
            Map<String, String> params = new LinkedHashMap<>();
            String errLog;
            if (request.getParameterNames() != null && request.getParameterNames().hasMoreElements()) {
                Enumeration<String> enumList = request.getParameterNames();
                errLog = null;
                String value = null;

                while(enumList.hasMoreElements()) {
                    errLog = (String)enumList.nextElement();
                    value = request.getParameter(errLog);
                    params.put(errLog, value);
                }
            }

            Object data = ServiceUtils.callService(service, serviceName, funcName, params );
//            result.setData(data == null ? null : this.frameworkJsonService.toJson(data));
//            response.setHeader("error_code", "200");
//            CurrentSessionStore store = CurrentSessionStoreFactory.getCurrentSessionStore();
//            FrameworkSession session = store.getCurrentSession();
//            if (session != null) {
//                try {
//                    session.touchSession();
//                    SessionCookieUtils.setSessionCookies(request, response, session);
//                } catch (InvalidSessionException var12) {
//                    SessionCookieUtils.delSessionCookies(request, response);
//                }
//            }
//
            String resultJson = JsonUtils.object2Json(data);
            return resultJson;
        }
    }
}
