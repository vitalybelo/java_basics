import java.io.File;

public class Main {


    public static void main(String[] args) {

        int newWidth = 900;
        String srcFolder = "ImageResizer/src/data/src/";
        String dstFolder = "ImageResizer/src/data/dst/";

        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        if (files != null) {

            int size1 = files.length / 2;
            int size2 = files.length - size1;

            // implements Runnable class
            File[] files1 = new File[size1];
            System.arraycopy(files,0,files1,0, files1.length);
            ImageResizerRun imageThread1 = new ImageResizerRun(files1, newWidth, dstFolder);
            new Thread(imageThread1).start();

            // 3-rd thread print file names
            new Thread(()->{
                for (File f : files)
                    System.out.println(f.getName());
            }).start();

            // extend Thread class
            File[] files2 = new File[size2];
            System.arraycopy(files, size1, files2, 0, files2.length);
            ImageResizer imageThread2 = new ImageResizer(files2, newWidth, dstFolder);
            imageThread2.start();

        }


    }
}
