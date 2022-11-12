import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class WebUsersCarousel {

    private final Jedis jedis;

    public WebUsersCarousel(Jedis jedis) {
        this.jedis = jedis;
    }

    public void pause (long milliseconds) {
        long finish = new Date().getTime() + milliseconds;
        long now;
        do {
            now = new Date().getTime();
        } while (now < finish);
    }

    public void listUsers() {

        jedis.del("web_users");

        // создаем 20 пользователей
        int USERS_COUNT = 20;
        for (int i = 0; i < USERS_COUNT; i++) {
            String username = "user #" + (i + 1);
            jedis.zadd("web_users", new Date().getTime(), username);
            pause(10);
        }
        List<Tuple> users = jedis.zscan("web_users", "0").getResult();

        // бесконечно показываем пользователей, вперемешку с оплаченной услугой
        while (true) {
            for (Tuple user : users) {
                System.out.println("Пользователь: " + user.getElement());
                pause(500);
                if ((new Random().nextInt(100)) < 20) {
                    int i = new Random().nextInt(19);
                    System.out.println("\tОплачена платная услуга, пользователь: " + users.get(i).getElement());
                }
            }
        }
    }

}
