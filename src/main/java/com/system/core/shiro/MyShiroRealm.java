package com.system.core.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

public class MyShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) getAvailablePrincipal(principals);
        String password = (String) getAvailablePrincipal(principals);
        return getAccount(username,password);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        return getAccount(upToken.getUsername(), upToken.getPassword().toString() );
    }

    protected SimpleAccount getAccount(String username, String password) {
        //just create a dummy.  A real app would construct one based on EIS access.
        //SimpleAccount account = new SimpleAccount(username, "sha256EncodedPasswordFromDatabase", getName());
        SimpleAccount account = new SimpleAccount(username, username, getName());
        //simulate some roles and permissions:
        account.addRole("user");
        account.addRole("admin");
        //most applications would assign permissions to Roles instead of users directly because this is much more
        //flexible (it is easier to configure roles and then change role-to-user assignments than it is to maintain
        // permissions for each user).
        // But these next lines assign permissions directly to this trivial account object just for simulation's sake:
        account.addStringPermission("blogEntry:edit"); //this user is allowed to 'edit' _any_ blogEntry
        //fine-grained instance level permission:
        account.addStringPermission("printer:print:laserjet2000"); //allowed to 'print' to the 'printer' identified
        //by the id 'laserjet2000'

        return account;
    }

}
