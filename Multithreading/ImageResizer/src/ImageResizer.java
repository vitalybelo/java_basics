import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer extends Thread {

    final private File[] files;
    final private int newWidth;
    final private String dstFolder;

    public ImageResizer(File[] files, int newWidth, String dstFolder) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();
        if (files != null) {
            try {
                for (File file : files) {

                    // считываем из файла изображение в объект в памяти
                    BufferedImage image = ImageIO.read(file);
                    if (image == null) continue;

                    // определяем высоту уменьшенного изображения
                    int newHeight = (int) Math.round(
                            image.getHeight() / (image.getWidth() / (double) newWidth));

                    // открываем новое изображение как пустой объект
                    BufferedImage newImage =
                            new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                    // шаг уменьшения изображения по высоте и по ширине
                    int stepWidth = image.getWidth() / newWidth;
                    int stepHeight = image.getHeight() / newHeight;

                    // создаем в памяти новое изображение из большого - уменьшенное
                    for (int y = 0; y < newHeight; y++) {
                        for (int x = 0; x < newWidth; x++) {
                            int rgb = image.getRGB(x * stepWidth, y * stepHeight);
                            newImage.setRGB(x, y, rgb);
                        }
                    }
                    // записываем изображение в файл
                    File newFile = new File(dstFolder + "pxl_" + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        long finish = System.currentTimeMillis();
        System.out.println("Timing :: " + (finish - start));

    }

}
