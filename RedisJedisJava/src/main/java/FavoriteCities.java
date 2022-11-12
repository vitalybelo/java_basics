import redis.clients.jedis.*;

import java.util.List;

public class FavoriteCities {

    private final Jedis jedis;

    public FavoriteCities(Jedis client) {
        this.jedis = client;
    }

    public void listCities() {

        jedis.zadd("Favorite Cities",69000, "Bali");
        jedis.zadd("Favorite Cities",89000, "Singapore");
        jedis.zadd("Favorite Cities",29000, "Paris");
        jedis.zadd("Favorite Cities",34000, "London");
        jedis.zadd("Favorite Cities",65000, "Washington");
        jedis.zadd("Favorite Cities",23000, "Rome");
        jedis.zadd("Favorite Cities",25000, "Neapol");
        jedis.zadd("Favorite Cities",31000, "Venice");
        jedis.zadd("Favorite Cities",15000, "Berlin");
        jedis.zadd("Favorite Cities",45000, "Madrid");


        System.out.print("ТОП 3 самых дешёвых путешествий: ");
        System.out.println(jedis.zrange("Favorite Cities",0,2));

        System.out.print("ТОП 3 самых дорогих путешествий: ");
        System.out.println(jedis.zrevrange("Favorite Cities",0,2));

        jedis.del("Favorite Cities");
    }

}
