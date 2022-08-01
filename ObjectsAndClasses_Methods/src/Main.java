public class Main
{
    public static void main(String[] args)
    {
        Basket basket1 = new Basket();
        Basket basket2 = new Basket();

        basket1.add("Молоко Lowlactose", 40, 1, 0.11);
        basket1.add("Сок древесный с/в", 140, 2, 0.30);
        basket1.print("Корзина №1");
        System.out.println("");

        basket2.add("Напиток стрёмный 45%", 99, 1, 0.25);
        basket2.add("Виски безалкогольный", 300,2,0.500);
        basket2.add("Сырок плавленный 10%", 45,3,0.60 );
        basket2.print("Корзина №2");
        System.out.println("");

        System.out.println("Общая статистика: ");
        System.out.println("Общее количество корзин = " + Basket.getGlobalBasket());
        System.out.println("Товаров во всех корзинах = " + Basket.getGlobalCount());
        System.out.println("Стоимость во всех корзинах = " + Basket.getGlobalPrice());
        System.out.println("Средний ценник всех товаров = " + Basket.averageCountPrice());
        System.out.println("Средний ценник всех корзин = " + Basket.averageBasketPrice());

    }
}
