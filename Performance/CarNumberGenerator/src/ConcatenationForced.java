
public class ConcatenationForced {

    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        // StringBuilder - ускорят в 300-400 раз по сравнению с String
        // repeat() метод ускоряет в 2-3 раза по сравнению с циклом for
        sb.append("some text some text some text".repeat(20_000));

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

}
