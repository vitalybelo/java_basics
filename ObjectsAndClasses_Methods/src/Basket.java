public class Basket
{
    private static int count = 0;   // Quantity of items in basket
    private String items = "";      // Total string consist  item name, quantity, price, weight += next items
    private int totalPrice = 0;     // Rubles
    private int limit;              // Rubles
    private double totalWeight;     // Kilograms

    public Basket() {
        increaseCount(1);
        items = "Список товаров:";
        this.limit = 1000000;
        this.totalWeight = 0;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount(int count) {
        Basket.count = Basket.count + count;
    }

    public double getTotalWeight() { return totalWeight; }

    public void add(String name, int price)
    {   add(name, price, 1);
    }

    public void add(String name, int price, int count, double weight)
    {   add(name, price, count);
        totalWeight += weight;
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
                count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
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
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
