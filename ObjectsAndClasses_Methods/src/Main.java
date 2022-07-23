public class Main
{

    public static void main(String[] args)
    {
        Basket basket = new Basket();

        basket.add("Milk", 40, 1, 0.110);
        basket.print("Milk");

        System.out.println("Общий вес в корзине = " + basket.getTotalWeight() + " кг.");
    }
}
