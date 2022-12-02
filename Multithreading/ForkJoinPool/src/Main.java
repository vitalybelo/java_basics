import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    /**
     * Алгоритм парсинга отдельно лежит в - Main_Process.java (без параллелей, винтажный).
     * Сбор ссылок выполняется в TreeSet, чтобы потом не терять время на сортировку.
     */
    public static void main(String[] args) {

        String name =  "skillbox";
        String site = "https://" + name + ".ru";
        System.out.println("Scanning for: " + site);

        long start = System.currentTimeMillis();
        RecursiveLinkParser parser = new RecursiveLinkParser(site);
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        TreeSet<String> uniqueURL = commonPool.invoke(parser);
        long finish1 = System.currentTimeMillis();

        fileWriter(name, uniqueURL, parser.getUrlCounter());

        long finish2 = System.currentTimeMillis();
        System.out.println("\nLinks was read (program limit): " + uniqueURL.size() + " (" + parser.getUrlCounter() + ")");
        System.out.println("Common parsing time (sec): " + ((finish1 - start) / 1_000));
        System.out.println("Total progress time (sec): " + ((finish2 - start) / 1_000));
    }

    public static void fileWriter (String name, TreeSet<String> uniqueURL, int linkSize) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/data/" + name + ".txt");
            writer.write("Links parsing results at: " + new Date().toInstant() + "\n");
            writer.write("Parsing request performed for: " + linkSize + " links\n\n");

            StringBuilder sb = new StringBuilder();
            for (String s : uniqueURL) {
                sb.append(spaceTab(s)).append("\n");
            }
            writer.write(sb.toString());

        } catch (FileNotFoundException e) {
            System.err.println("Error writing output file: " + name);
        } finally {
            assert writer != null;
            writer.flush();
            writer.close();
        }
    }

    public static String spaceTab(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '/') count++;
        }
        StringBuilder sb = new StringBuilder();
        if (count > 3) {
            sb.append("\t".repeat(count - 3));
        }
        sb.append(s);
        return sb.toString();
    }

}
