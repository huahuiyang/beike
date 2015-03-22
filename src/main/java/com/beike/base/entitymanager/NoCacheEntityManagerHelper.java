package com.beike.base.entitymanager;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import com.beike.base.IEntityManagerHelper;

/**
 * 无缓存的保持线程池的JPA数据库管理器
 * 是用来解决长链接问题的实现
 */
public class NoCacheEntityManagerHelper implements IEntityManagerHelper {

    private static final Map<String, ThreadLocal<EntityManager>> threadLocalMap =
            new HashMap<String, ThreadLocal<EntityManager>>();

    public EntityManager getEntityManager(String PUName) {

        if (!threadLocalMap.containsKey(PUName)) {
            threadLocalMap.put(PUName, new ThreadLocal<EntityManager>());
        }

        EntityManager manager = threadLocalMap.get(PUName).get();

        if (manager != null && manager.isOpen()) {
            manager.close();
            threadLocalMap.get(PUName).set(null);
            manager = null;
        }

        if (manager == null || !manager.isOpen()) {
            manager = new SimpleEntityManagerHelper().getEntityManager(PUName);
            threadLocalMap.get(PUName).set(manager);
        }
        return manager;
    }
}