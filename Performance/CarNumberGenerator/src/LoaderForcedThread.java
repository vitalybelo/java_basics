
/**
 * Замена операций со строками на StringBuilder ускоряет примерно в 30 раз
 * Замена PrintWriter сложно сказать, скорее нет, большую часть буферизации берет на себя OS
 * Оптимизация метода padNumber() - установить % ускорения сложно, слишком маленькая задача
 * Если например разделить каждый регион на поток, можно ускорить процесс на 15%
 */
public class LoaderForcedThread {


    public static void main(String[] args) {

        long begin = System.currentTimeMillis();
        NumberGenerator numberGenerator = new NumberGenerator();

        for (int i = 0; i < 8; i++) {
            numberGenerator.run();
        }

        System.out.println((System.currentTimeMillis() - begin) + " ms");
    }

}
