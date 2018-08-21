package com.system.core.shiro;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {
    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager( manager );
        //配置登录的url和登录成功的url
//        bean.setLoginUrl("/login");
//        bean.setSuccessUrl("/home");
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/jsp/login.jsp*", "anon"); //表示可以匿名访问
        filterChainDefinitionMap.put("/loginUser", "anon");
        filterChainDefinitionMap.put("/logout*","anon");
        filterChainDefinitionMap.put("/jsp/error.jsp*","anon");
        filterChainDefinitionMap.put("/jsp/index.jsp*","authc");
        filterChainDefinitionMap.put("/*", "anon");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/**", "anon");//表示需要认证才可以访问
        filterChainDefinitionMap.put("/*.*", "anon");
//        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    //配置核心安全事务管理器
    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
        System.out.println("加载SHIRO成功");
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 使用自己的realm
        manager.setRealm(myShiroRealm);
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(true);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        return manager;
    }

    //配置自定义的权限登录器
    @Bean(name="myShiroRealm")
    public MyShiroRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        MyShiroRealm authRealm=new MyShiroRealm();
        authRealm.setCredentialsMatcher(matcher);
        return authRealm;
    }
    //配置自定义的密码比较器
    @Bean(name="credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new MyCredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        creator.setProxyTargetClass(true);
        return creator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
