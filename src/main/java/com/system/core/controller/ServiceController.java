package com.system.core.controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.system.core.bean.ServiceException;
import com.system.core.shiro.CustomeShiroUtils;
import com.system.core.shiro.StaticShiroCache;
import com.system.core.utils.ServiceUtils;
import com.system.core.bean.ResponseResult;
import com.boot.utils.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServiceController {
    private static final Log logger = LogFactory.getLog(ServiceController.class);

    @Autowired( required = true )
    private ApplicationContext context;

    @Autowired(required = true)
    public ServiceController() {}


    @RequestMapping(
            value = {"/service/{serviceName}/{funcName}"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @CrossOrigin(origins = "*", maxAge = 3600)
    @ResponseBody
    public Object doService(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceName, @PathVariable String funcName)  {

        ResponseResult result = new ResponseResult();

        if ( !CustomeShiroUtils.checkPermission(request,serviceName,funcName) ) {
            result.setErrorCode(0001);
            result.setErrorMsg("auth error!");
            return JsonUtils.object2Json(result);
        }

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

            Object data = null;
            try {
                data = ServiceUtils.callService(service, serviceName, funcName, params );
                result.setData( data );
            } catch (ServiceException e){
                result.setErrorCode(e.getErrCode());
                result.setErrorMsg(e.getErrMessage());
            } catch (Exception e) {
                result.setErrorCode(0000);
                result.setErrorMsg("unknown error");
            }

        }

        return JsonUtils.object2Json(result);

    }
}
