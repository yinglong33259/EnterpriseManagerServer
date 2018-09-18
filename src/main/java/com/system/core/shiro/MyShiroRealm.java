package com.system.core.shiro;

import com.boot.entity.TRight;
import com.boot.entity.TRole;
import com.boot.entity.TUser;
import com.boot.repository.RoleRightRepository;
import com.boot.repository.UserRepository;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        //校验是否超时
        if( StaticShiroCache.getInstance().checkExpireTime( principals.toString() ) ){
            return null;
        }

        Session session = StaticShiroCache.getInstance().getByprincipal( principals.toString() ).getSession();
        TUser user = (TUser) session.getAttribute("USER_SESSION");
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        List<TRole> roleList = userRepository.findUserRoles(user.getId());
        List<TRight> rightList = userRepository.findUserRights(user.getId());

        Set<String> roleSet = new HashSet<>();
        for(TRole role : roleList){
            roleSet.add(role.getRoleName());
        }
        Set<String> rightSet = new HashSet<>();
        for(TRight right : rightList){
            rightSet.add(right.getRightName());
        }
        info.setRoles(roleSet);
        info.setStringPermissions(rightSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;


        return getAccount(upToken.getUsername(), upToken.getPassword().toString() );
    }

    protected SimpleAccount getAccount(String username, String password) {

        TUser user = userRepository.findUserByLoginIdAndPwd("admin","1");

        if (user == null) {
            throw new LockedAccountException();
        }

        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("USER_SESSION", user);

        SimpleAccount account = new SimpleAccount(username, password, getName());

        return account;
    }

}
