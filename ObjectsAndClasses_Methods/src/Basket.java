public class Basket
{

    private static int globalPrice;     // общая сумма во всех корзинах
    private static int globalCount;     // общее количество товаров в корзинах
    private static int globalBasket;    // общее количество корзин
    private int count = 0;              // Quantity of items in basket
    private String items = "";          // Total string consist item name, quantity, price, weight += next items
    private int totalPrice = 0;         // Rubles
    private int limit;                  // Rubles
    private double totalWeight;         // Kilograms

    public Basket() {
        items = "Список товаров:";
        this.limit = 1000000;
        this.totalWeight = 0;
        increaseGlobalBasket(1);
    }
    public Basket(int limit) {
        this();
        this.limit = limit;
    }
    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
        increaseGlobalPrice(totalPrice);
    }

    public static void setGlobalBasket(int globalBasket) {
        Basket.globalBasket = globalBasket;
    }
    public static void setGlobalPrice(int globalPrice) {
        Basket.globalPrice = globalPrice;
    }
    public static void setGlobalCount(int globalCount) {
        Basket.globalCount = globalCount;
    }
    public static int getGlobalPrice() {
        return globalPrice;
    }
    public static int getGlobalCount() {
        return globalCount;
    }
    public static int getGlobalBasket() {
        return globalBasket;
    }
    public static void increaseGlobalPrice (int price) {
        globalPrice += price;
    }
    public static void increaseGlobalCount(int count) {
        globalCount += count;
    }
    public static void increaseGlobalBasket(int basket) {
        globalBasket += basket;
    }
    public static int averageCountPrice () {
        return ( globalPrice / globalCount );
    }
    public static int averageBasketPrice () {
        return ( globalPrice / globalBasket );
    }

    public int getCount() {
        return count;
    }
    public double getTotalWeight() {
        return totalWeight;
    }
    public void add(String name, int price) {
        add(name, price, 1);
    }
    public void add(String name, int price, int count, double weight) {
        add(name, price, count);
        totalWeight += (weight * count);
    }
    public void add(String name, int price, int count)
    {
        boolean error = contains(name);
        if (totalPrice + count * price >= limit) error = true;

        if (error) {
            System.out.println("Error occured :(");
            return;
        }
        items = items + "\n" + name + " ::: " +
                count + " шт. ::: " + price;
        totalPrice += (count * price);
        this.count += count;
        increaseGlobalCount( count );
        increaseGlobalPrice( count * price );
    }
    public void clear() {
        items = "";
        totalPrice = 0;
    }
    public int getTotalPrice() {
        return totalPrice;
    }
    public boolean contains(String name) {
        return items.contains(name);
    }
    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) System.out.println("Корзина пуста");
        else
        {
            System.out.println(items);
            System.out.println("Товаров в корзине = " + this.count + " шт");
            System.out.println("На общую сумму = " + this.totalPrice + " рублей");
            System.out.println("Общий вес товаров = " + this.totalWeight + " кг");
        }
    }
}
