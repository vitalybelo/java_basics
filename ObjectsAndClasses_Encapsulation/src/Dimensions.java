public class Dimensions
{
    private final int height;    // высота в сантиметрах
    private final int width;     // ширина в сантиметрах
    private final int depth;     // глубина в сантиметрах

    public Dimensions(int height, int width, int depth)
    {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    // возвращает объем в метрах квадратных
    public double volume () {
        return ((double)(height * width * depth) / 1000000.0);
    }

    public Dimensions setHeight (int height) {
        return new Dimensions(height, width, depth);
    }

    public Dimensions setWidth (int width) {
        return new Dimensions(height, width, depth);
    }

    public Dimensions setDepth (int depth) {
        return new Dimensions(height, width, depth);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getDepth() {
        return depth;
    }

    public String toString () {
        return (height + "*" + width + "*" + depth + " см");
    }
}
