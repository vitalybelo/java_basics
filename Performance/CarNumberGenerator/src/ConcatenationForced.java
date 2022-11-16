
public class ConcatenationForced {

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20_000; i++) {
            sb.append("some text some text some text");
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

}
