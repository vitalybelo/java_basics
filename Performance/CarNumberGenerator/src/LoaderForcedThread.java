import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Замена операций со строками на StringBuilder ускоряет примерно в 30 раз
 * Замена PrintWriter сложно сказать, скорее нет, большую часть буферизации берет на себя OS
 * Оптимизация метода padNumber() - установить % ускорения сложно, слишком маленькая задача
 * Если разделить каждый регион на поток, генерация занимает 25-35 секунд (ускорение в 2 раза)
 */
public class LoaderForcedThread {


    public static void main(String[] args) throws FileNotFoundException {

        long begin = System.currentTimeMillis();

        ExecutorService service = Executors.newCachedThreadPool();

        for (int region = 1; region < 200; region++) {
            service.submit(new NumberGenerator(region));
        }
        service.shutdown();
        while (!service.isTerminated()){}

        System.out.println("\nLASTED: " + (System.currentTimeMillis() - begin) + " ms");
    }

}
