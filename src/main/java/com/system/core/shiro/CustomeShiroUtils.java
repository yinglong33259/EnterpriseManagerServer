package com.system.core.shiro;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

public class CustomeShiroUtils {

    public static boolean checkPermission(HttpServletRequest request, String serviceName, String funcName){
        String tokenId = request.getHeader("tokenId");
        if (tokenId == null || "".equals(tokenId))
            return false;
        StaticShiroCache staticShiroCache = StaticShiroCache.getInstance();
        Subject subject =  staticShiroCache.getByTokenId( tokenId  );
        if ( subject == null )
            return false;
        if ( subject.isPermitted("service."+serviceName+"."+funcName) ){
            return true;
        }
        if ( subject.isPermitted("service."+serviceName+".*") ){
            return true;
        }
        return false;

    }

    /**
     * 校验TokenId是否失效
     * @return
     */
    public static boolean checkTokenIdIsValid(String tokenId){
        return StaticShiroCache.getInstance().checkTokenIdIsValid(tokenId);
    }

}
