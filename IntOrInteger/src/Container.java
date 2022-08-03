public class Container
{
    private Integer count;
    // private Integer count = 0; можно так а можно сделать конструктор
    // изначально значение count = NULL, так как это не значение а ссылка

    public Container () {
        this.count = 0;
    }

    public void addCount(int value) {
        count = count + value;
    }

    public int getCount() {
        return count;
    }
}
