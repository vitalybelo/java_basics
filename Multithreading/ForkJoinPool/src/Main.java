import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) throws IOException {


        String name = "skillbox";
        String site = "https://" + name + ".ru";
        System.out.println("Scanning for: " + site + "\n");

        long start = System.currentTimeMillis();
        RecursiveLinkParser parser = new RecursiveLinkParser(site);
        TreeSet<String> uniqueURL = new ForkJoinPool(10).invoke(parser);
        long finish1 = System.currentTimeMillis();;

        fileWriter(name, uniqueURL, parser);

        long finish2 = System.currentTimeMillis();
        System.out.println("\nLinks was read (program limit): " + uniqueURL.size() + " (" + parser.getUrlCounter() + ")");
        System.out.println("Common parsing time (sec): " + ((finish1 - start) / 1_000));
        System.out.println("Total progress time (sec): " + ((finish2 - start) / 1_000));

    }

    public static void fileWriter (String name, TreeSet<String> uniqueURL, RecursiveLinkParser parser) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/data/" + name + ".txt");
            writer.write("Links parsing results at: " + new Date().toInstant() + "\n");
            writer.write("Parsing request performed for: " + parser.getUrlCounter() + " links\n\n");
            for (String s : uniqueURL) {
                String sb = spaceTab(s) + s;
                //System.out.println(sb);
                writer.write(sb + "\n");
            }
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
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '/') count++;
        StringBuilder sb = new StringBuilder();
        if (count > 3)
            sb.append("\t".repeat(count - 3));
        return sb.toString();
    }

}
