public class Manager extends Worker {

    private static final int MIN_INCOME = 115_000; // рублей
    private static final int MAX_INCOME = 140_000; // рублей

    public Manager() {
        super();
        int bonus = MAX_INCOME - MIN_INCOME;
        bonus = (int)(Math.random() * bonus) + MIN_INCOME;
        super.addBonus(bonus / 20); // 5%
    }

}
