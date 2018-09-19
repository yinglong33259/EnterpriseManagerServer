package com.system.core.controller;

import com.boot.utils.JsonUtils;
import com.system.core.bean.ResponseResult;
import com.system.core.shiro.StaticShiroCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {
    @RequestMapping(
            value = {"/logoutService/logout"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response)  {

        ResponseResult result = new ResponseResult();
        String paramsStr = request.getParameter("params");
        Object[] params = JsonUtils.json2Array( paramsStr );
        String tokenId = (String) params[0];
        StaticShiroCache.getInstance().clearTonkenCache( tokenId );
        return JsonUtils.object2Json( result );

    }
}
