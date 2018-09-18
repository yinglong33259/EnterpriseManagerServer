package com.system.core.shiro;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

public class CustomeShiroUtils {

    public static boolean checkPermission(HttpServletRequest request, String serviceName, String funcName){
        String tokenId = request.getHeader("tokenId");
        if (tokenId == null || "".equals(tokenId))
            return false;

        Subject subject =  StaticShiroCache.getInstance().getByTokenId( tokenId  );
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

}
