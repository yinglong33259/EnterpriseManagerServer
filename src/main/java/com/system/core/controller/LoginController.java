package com.system.core.controller;

import com.boot.utils.JsonUtils;
import com.system.core.StaticCache;
import com.system.core.bean.ResponseResult;
import com.system.core.shiro.StaticShiroCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class LoginController {
    @RequestMapping(
            value = {"/loginService/login"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public Object doService(HttpServletRequest request, HttpServletResponse response)  {

        ResponseResult result = new ResponseResult();

        String paramsStr = request.getParameter("params");
        Object[] params = JsonUtils.json2Array( paramsStr );
        String userName = (String) params[0];
        String password = (String) params[1];

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(true);
        subject.login(token);

        String tokenId = UUID.randomUUID().toString();
        StaticShiroCache.getInstance().put( tokenId, subject );

        result.setData(tokenId);
        return JsonUtils.object2Json( result );

    }
}
