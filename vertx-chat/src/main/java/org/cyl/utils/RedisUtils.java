package org.cyl.utils;

import io.vertx.core.Vertx;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisOptions;
import java.util.Properties;
import io.vertx.redis.client.Redis;

public class RedisUtils {

    private static final RedisOptions options = new RedisOptions();
    private static final Properties config = new Properties();
    static   Vertx vertx = Vertx.vertx();
    static Redis redisClient = Redis.createClient(vertx, options);
    static RedisAPI redisAPI=RedisAPI.api(redisClient);

    static {
        try {
            config.load(RedisUtils.class.getClassLoader().getResourceAsStream("redis.properties"));

            options.setEndpoint(config.getProperty("host"))
                    .setPassword(config.getProperty("auth"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Redis getRedisClient(Vertx vertx) {
        return redisClient;
    }

    public static RedisAPI getRedisApi(){
        return redisAPI;
    }


}

