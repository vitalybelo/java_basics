
/**
 * Замена операций со строками на StringBuilder ускоряет примерно в 30 раз
 * Замена PrintWriter ничего не дает, большую часть буферизации берет на себя OS
 * Оптимизация метода padNumber() - установить % ускорения сложно
 */
public class LoaderForcedThread {


    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        NumberGenerator numberGenerator = new NumberGenerator();

        for (int i = 0; i < 8; i++)
            numberGenerator.run();

        System.out.println((System.currentTimeMillis() - begin) + " ms");
    }

}
