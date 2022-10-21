import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {


        long start = new Date().getTime();

        String name = "skillbox";
        String site = "https://" + name + ".ru";
        System.out.println("Scanning for: " + site);

        RecursiveLinkParser parser = new RecursiveLinkParser(site);
        TreeSet<String> uniqueURL = new ForkJoinPool(10).invoke(parser);
        long finish1 = new Date().getTime();

        System.out.println("\n");
        for (String s : uniqueURL) {
            System.out.println(spaceTab(s) + s);
        }
        System.out.println("\nLinks was read (program limit): " + uniqueURL.size() + " (" + parser.getUrlCounter() + ")");
        System.out.println();

        long finish2 = new Date().getTime();

        System.out.println("Parsing time (sec): " + ((finish1 - start) / 1_000));
        System.out.println("Total progress time (sec): " + ((finish2 - start) / 1_000));

    }

    public static String spaceTab(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '/') count++;
        StringBuilder sb = new StringBuilder();
        if (count > 3)
            sb.append("\t".repeat(count - 3));
        return sb.toString();
    }

}
