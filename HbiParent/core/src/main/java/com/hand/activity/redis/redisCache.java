package com.hand.activity.redis;

import com.hand.activity.dto.RentActivity;
import com.hand.activity.mapper.RentActivityMapper;
import com.hand.hap.cache.impl.HashStringRedisCache;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisCallback;
import com.hand.hap.account.dto.User;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.core.JsonProcessingException;

public class redisCache extends HashStringRedisCache<List<RentActivity>> {

    private Logger logger = LoggerFactory.getLogger(HashStringRedisCache.class);

    private String productSql = RentActivityMapper.class.getName() + ".selectStartActivity";
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
            List<RentActivity> ProductInfoList = sqlSession.selectList(productSql);
            setValue("selectStartActivity", ProductInfoList);

        }
    }


    @Override
    public List<RentActivity> getValue(String key) {
        return getRedisTemplate().execute((RedisCallback<List<RentActivity>>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            byte[] valueBytes = connection.get(keyBytes);
            try {
                if (null != valueBytes) {
                    return (List<RentActivity>) getObjectMapper().readValue(valueBytes, Object.class);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            return null;
        });
    }

    public void setValue(String key, List<RentActivity> productInfos,Long expireTime) {
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
        getRedisTemplate().execute((RedisCallback<List<RentActivity>>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":" + key.toLowerCase());
            connection.del(keyBytes);
            return (List<RentActivity>) null;
        });
    }

    /**
     * 清空所有用户redis缓存
     */
    public void clearAll() {
        getRedisTemplate().execute((RedisCallback<RentActivity>) (connection) -> {
            byte[] keyBytes = strSerializer.serialize(getFullKey(null) + ":*");
            Set<byte[]> keys = connection.keys(keyBytes);
            Iterator<byte[]> iterable = keys.iterator();
            while (iterable.hasNext()) {
                connection.del(iterable.next());
            }
            return (RentActivity) null;
        });
    }
}




