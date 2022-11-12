import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // Инициализация сервера (должен быть запущен сервер в powerPoint > redis-server)
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);

        // Get the pool and use the database (great thanks to Baeldung education platform !)
        try (Jedis jedis = jedisPool.getResource()) {

            System.out.println("\nЗадание №1");
            FavoriteCities cities = new FavoriteCities(jedis);
            cities.listCities();

            System.out.println(new Date().getTime());


        } catch (Exception e) {
            e.printStackTrace();
        }

        jedisPool.close();
    }
}
