import java.io.File;

public class Main {

    public static void main(String[] args) {

        int newWidth = 300;
        String srcFolder = "ImageResizer/src/data/src/";
        String dstFolder = "ImageResizer/src/data/dst/";

        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Количество ядер процессора = " + cores);

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        if (files != null) {

            int filesInThread = 4;
            int length = files.length;
            // корректируем количество процессов,
            // чтобы на каждый приходилось = filesInThread файлов
            if (cores > length / filesInThread) cores = length / filesInThread;

            int sizeArrays = Math.round((float) length / (float) cores);
            System.out.println("Минимум файлов в процессе = " + filesInThread);
            System.out.println("Всего файлов в каталоге = " + length);
            System.out.println("Количество процессов = " + cores + "\n");

            for (int i = 0, index = 0; i < cores; i++, index += sizeArrays) {

                if (i == (cores - 1)) sizeArrays = length - index;

                File[] threadFiles = new File[sizeArrays];
                System.arraycopy(files, index, threadFiles, 0, sizeArrays);
                ImageResizer imageResizer = new ImageResizer(threadFiles, newWidth, dstFolder);
                imageResizer.start();

            }
        } else {
            System.out.println("Каталог с фалами изображений не найден");
        }
    }

}
