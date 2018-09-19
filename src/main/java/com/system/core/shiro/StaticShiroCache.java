package com.system.core.shiro;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.subject.Subject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StaticShiroCache {

    private static final Log logger = LogFactory.getLog(StaticShiroCache.class);

    private static StaticShiroCache instance = new StaticShiroCache();
    //Map<TokenId, Subject>
    private Map<String, Subject> cacheMap = new ConcurrentHashMap<>();
    //Map<principal,tokenId>
    private Map<String, String> cacheMap2 = new ConcurrentHashMap<>();
    //Map<TokenId,time>
    private Map<String, Long> cacheMap3 = new ConcurrentHashMap<>();

    private StaticShiroCache() {
    }

    public static StaticShiroCache getInstance() {
        return instance;
    }

    public void put(String key, Subject value) {
        this.cacheMap.put( key, value );
        this.cacheMap2.put( value.getPrincipal().toString(), key );
        this.cacheMap3.put( key, System.currentTimeMillis() + 30*60*1000 );
        logger.info( "Shiro Subject " +  value.getPrincipal().toString() + " 已经缓存");
    }

    public Subject getByTokenId(String tokenId) {
        return this.cacheMap.get(tokenId);
    }

    public Subject getByprincipal(String principal) {
        return this.cacheMap.get( this.cacheMap2.get( principal ) );
    }

    public void updateExpireTime(String principal) {
        this.cacheMap3.put( this.cacheMap2.get( principal ) , System.currentTimeMillis() + 30*60*1000 );
    }

    /**
     * 校验是否超时，超时返回true
     * @param principal
     * @return
     */
    public boolean checkExpireTime(String principal) {
        String tokenId = this.cacheMap2.get( principal );
        if( System.currentTimeMillis() - this.cacheMap3.get( tokenId ) > 0){
            this.cacheMap.remove( tokenId );
            this.cacheMap2.remove( principal );
            this.cacheMap3.remove( tokenId );
            logger.info( "Shiro Subject " +  principal + " 已经超时，清除缓存");
            return true;
        }else{
            this.cacheMap3.put( principal, System.currentTimeMillis() + 30*60*1000 );
            return false;
        }
    }

    /**
     * 校验某个TokenId是否还有效
     * @param tokenId
     * @return
     */
    public boolean checkTokenIdIsValid(String tokenId) {

        if( !this.cacheMap.containsKey(tokenId) ){
            return false;
        }

        String principal = this.cacheMap.get(tokenId).getPrincipal().toString();
        if( System.currentTimeMillis() - this.cacheMap3.get( tokenId ) > 0){
            this.cacheMap.remove( tokenId );
            this.cacheMap2.remove( principal );
            this.cacheMap3.remove( tokenId );
            logger.info( "Shiro Subject " +  principal + " 已经超时，清除缓存");
            return false;
        }else{
            this.cacheMap3.put( principal, System.currentTimeMillis() + 30*60*1000 );
            return true;
        }
    }

    /**
     * 清除某个Token
     * @param tokenId
     * @return
     */
    public void clearTonkenCache(String tokenId) {

        if( !this.cacheMap.containsKey(tokenId) ){
            return ;
        }

        String principal = this.cacheMap.get(tokenId).getPrincipal().toString();
        this.cacheMap.remove( tokenId );
        this.cacheMap2.remove( principal );
        this.cacheMap3.remove( tokenId );
        logger.info( "Shiro Subject " +  principal + " 已经登出");
    }

}
