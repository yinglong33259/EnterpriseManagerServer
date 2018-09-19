package com.system.core.controller;

import com.boot.utils.JsonUtils;
import com.system.core.bean.ResponseResult;
import com.system.core.shiro.StaticShiroCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
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
        try{
            subject.login(token);
            String tokenId = UUID.randomUUID().toString();
            StaticShiroCache.getInstance().put( tokenId, subject );

            result.setData(tokenId);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return JsonUtils.object2Json( result );
        }catch (AuthenticationException e){
            result.setErrorCode(0002);
            result.setErrorMsg("Login Error, Username or password Error!");
            return JsonUtils.object2Json( result );
        }


    }

    @RequestMapping(
            value = {"/loginService/checkToken"},
            produces = {"text/plain;charset=UTF-8"}
    )
    @ResponseBody
    public Object checkToken(HttpServletRequest request, HttpServletResponse response)  {

        ResponseResult result = new ResponseResult();
        String paramsStr = request.getParameter("params");
        Object[] params = JsonUtils.json2Array( paramsStr );
        String tokenId = (String) params[0];
        result.setData( StaticShiroCache.getInstance().checkTokenIdIsValid( tokenId ) );
        return JsonUtils.object2Json( result );

    }

}
