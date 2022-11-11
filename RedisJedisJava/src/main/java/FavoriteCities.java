import redis.clients.jedis.*;

public class FavoriteCities {

    private Jedis client = new Jedis();

    public FavoriteCities(Jedis client) {
        this.client = client;
    }
}
