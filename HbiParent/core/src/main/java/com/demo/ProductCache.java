package com.demo;

import com.hand.hap.account.dto.User;
import com.hand.hap.cache.impl.HashStringRedisCache;
import com.hand.user.dto.HapDemo;
import com.hand.user.mapper.HapDemoMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ProductCache extends HashStringRedisCache<List<HapDemo>> {
    private Logger logger = LoggerFactory.getLogger(HashStringRedisCache.class);

    //有效时间-一周
    private final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;

    private String productSql = HapDemoMapper.class.getName() + ".selectAllDemo";
    /**
     * 项目启动是否清空用户redis缓存
     * 默认不清
     */
    @Value("${sys.userCache.clearCacheOnStartUp:false}")
    private boolean CLEAR_USER_CACHE;

    {
        setLoadOnStartUp(false);
        setType(User.class);
    }

    @Override
    public void init() {
        super.init();
        if (CLEAR_USER_CACHE) {
            this.clearAll();
        }
        //商品信息加入缓存
        // 商品缓存key public static final String TOKEN_PRODUCT_KEY="PRODUCT_KEY";
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            List<HapDemo> ProductInfoList = sqlSession.selectList(productSql);
            setValue("demo", ProductInfoList, EXPIRE_TIME);

        }
    }


    @Override
    public List<HapDemo> getValue(String key) {
        return getRedisTemplate().execute((RedisCallback<List<HapDemo>>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            byte[] valueBytes = connection.get(keyBytes);
            try {
                if (null != valueBytes)
                    return (List<HapDemo>) getObjectMapper().readValue(valueBytes, Object.class);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        });
    }

    public void setValue(String key, List<HapDemo> productInfos,Long expireTime) {
        try {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            String value = getObjectMapper().writeValueAsString(productInfos);
            getRedisTemplate().execute((RedisCallback<Object>) (connection) -> {
                connection.setEx(keyBytes, expireTime, strSerializer.serialize(value));
                return null;
            });
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public void remove(String key) {
        getRedisTemplate().execute((RedisCallback<List<HapDemo>>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            connection.del(keyBytes);
            return (List<HapDemo>) null;
        });
    }

    /**
     * 清空所有用户redis缓存
     */
    public void clearAll() {
        getRedisTemplate().execute((RedisCallback<HapDemo>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":*");
            Set<byte[]> keys = connection.keys(keyBytes);
            Iterator<byte[]> iterable = keys.iterator();
            while (iterable.hasNext()) {
                connection.del(iterable.next());
            }
            return (HapDemo) null;
        });
    }

}
