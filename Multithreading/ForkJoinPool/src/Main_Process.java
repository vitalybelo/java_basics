import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Set;
import java.util.TreeSet;

public class Main_Process {

    public static String USER_AGENT1 = "Mozilla/106.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.";
    public static String USER_AGENT2 = "Chrome/106.0.5249.119 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.";
    public static String USER_AGENT3 = "Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0";
    public static String REFERRER = "https://www.google.com";
    public static int TIME_OUT = 60000;
    public static int MAX_URLS = 500;
    public static int STEP_OUT = MAX_URLS / 10;
    public static int urlCounter = 0;
    public static String site;

    public static void main(String[] args) {

        Set<String> uniqueURL = new TreeSet<>();
        System.out.print("Процесс запущен (окончание = 10 точек): ");
        site = "https://skillbox.ru";
        getLinks(site, uniqueURL);

        System.out.println("\n");
        for (String s : uniqueURL) { //.stream().sorted().toList())
            System.out.println(spaceTab(s) + s);
        }
        System.out.println("\nСчитано ссылок: " + uniqueURL.size());
    }

    public static void getLinks (String site, Set<String> urls){

        try {
            Thread.sleep(100);
            Connection connection = Jsoup
                    .connect(site)
                    .userAgent(USER_AGENT2)
                    .referrer(REFERRER)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .timeout(TIME_OUT)
                    .newRequest();
            Document doc = connection.execute().parse();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                if (urlCounter >= MAX_URLS) break;
                String url = link.attr("abs:href");
                if (ignoreLink(url)) continue;
                if (urls.add(url)) {
                    urlCounter++;
                    if (urlCounter % STEP_OUT == 0) System.out.print('.');
                    getLinks(url, urls);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ignoreLink (String url) {
        if (url.isEmpty()) return true;
        if (!url.startsWith(site)) return true;
        if (url.endsWith(".pdf")) return true;
        if (url.contains("#")) return true;
        if (url.contains(" ")) return true;
        return false;
    }

    public static String spaceTab(String s) {
        int counter = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '/') counter++;
        StringBuilder sb = new StringBuilder();
        if (counter > 3) {
            sb.append("\t".repeat(counter - 3));
        }
        return sb.toString();
    }


}
