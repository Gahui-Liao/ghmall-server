package com.gahui.ghmall.server.util;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @description: ehcache缓存工具类
 * 本项目估计后期只使用单节点部署，故使用ehcache作为缓存
 * ehcache为java实现的进程内缓存
 * @author: Gahui
 * @since: 2021/3、12
 **/
@Component("ghCacheHelper")
public class GhCacheHelper {

    @Resource
    protected CacheManager cacheManager;

    /**
     * 获取
     */
    public Element get(String cacheName, String key) {
        Ehcache ehcache = this.getEhcache(cacheName);
        if (ehcache != null) {
            return ehcache.get(key);
        }
        return null;
    }

    /**
     * 删除
     */
    public int remove(String cacheName, String key) {
        Ehcache ehcache = this.getEhcache(cacheName);
        if (ehcache != null) {
            if (ehcache.remove(key)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 设置
     */
    public int set(String cacheName, String key, Object value) {
        Ehcache ehcache = this.getEhcache(cacheName);
        Element element = new Element(key, value);
        if (ehcache != null) {
            ehcache.put(element);
            return 1;
        }
        return 0;
    }

    /**
     * 不存在才设置
     */
    public int setNx(String cacheName, String key, Object value) {
        Ehcache ehcache = this.getEhcache(cacheName);
        Element element = new Element(key, value);
        if (ehcache != null) {
            if (this.get(cacheName, key) != null) {
                return 0;
            }
            ehcache.put(element);
            return 1;
        }
        return 0;
    }

    /**
     * 带有过期时间的设置
     */
    public int setEx(String cacheName, String key, Object value, int expireSeconds) {
        Ehcache ehcache = this.getEhcache(cacheName);
        Element element = new Element(key, value, 0, expireSeconds);
        if (ehcache != null) {
            ehcache.put(element);
            return 1;
        }
        return 0;
    }

    /**
     * 设置值，如有过期时间，不更新过期时间
     */
    public int put(String cacheName, String key, Object value) {
        Ehcache ehcache = this.getEhcache(cacheName);
        Element element;
        if (ehcache != null) {
            element = ehcache.get(key);
            if (element == null) {
                element = new Element(key, value);
            } else {
                if (element.getTimeToLive() > 0 && element.getTimeToIdle() == 0) {
                    element = new Element(key, value, element.getVersion(), element.getCreationTime(), element.getLastAccessTime(),
                            element.getLastUpdateTime(), element.getHitCount());
                } else {
                    element = new Element(key, value);
                }
            }
            ehcache.put(element);
            return 1;
        }
        return 0;
    }

    /**
     * 增加value值
     */
    public int incr(String cacheName, String key) {
        Ehcache ehcache = this.getEhcache(cacheName);
        Element element;
        if (ehcache != null) {
            element = ehcache.get(key);
            if (element == null) {
                this.put(cacheName, key, 1);
                return 1;
            }
            Integer value = (Integer) element.getObjectValue() + 1;
            this.put(cacheName, key, value);
            return 1;
        }
        return 0;
    }

    /**
     * 正则查询缓存中key的总数
     */
    public int sizeByRegex(String cacheName, String regex) {
        int size = 0;
        Ehcache ehcache = this.getEhcache(cacheName);
        if (ehcache != null) {
            if (regex == null) {
                return ehcache.getSize();
            }
            for (Object temp : ehcache.getKeys()) {
                String key = temp.toString();
                if (key.matches(regex)) {
                    size++;
                }
            }
        }
        return size;
    }

    /**
     * 查询缓存中以prefix开头的key的总数
     */
    public int sizeByPrefix(String cacheName, String prefix) {
        int size = 0;
        Ehcache ehcache = this.getEhcache(cacheName);
        if (ehcache != null) {
            if (prefix == null) {
                return 0;
            }
            for (Object temp : ehcache.getKeys()) {
                String key = temp.toString();
                if (key.contains(prefix)) {
                    size++;
                }
            }
        }
        return size;
    }

    /**
     * 正则删除所有元素
     */
    public int removeByRegex(String cacheName, String regex) {
        Ehcache ehcache = this.getEhcache(cacheName);
        if (ehcache != null) {
            if (regex == null) {
                ehcache.removeAll();
                return 1;
            }
            for (Object temp : ehcache.getKeys()) {
                String key = temp.toString();
                if (key.matches(regex)) {
                    ehcache.remove(key);
                }
            }
            return 1;
        }
        return 0;
    }

    /**
     * 根据缓存名称获取对应缓存
     */
    private Ehcache getEhcache(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        EhCacheCache ehCacheCache = (EhCacheCache) cache;
        if (ehCacheCache != null) {
            return ehCacheCache.getNativeCache();
        }
        return null;
    }
}
