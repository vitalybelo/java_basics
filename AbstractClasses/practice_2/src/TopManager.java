public class TopManager extends Worker {

    public static final long BONUS_AMOUNT = 10_000_000;

    public TopManager(long companyAmount) {
        super();
        if (companyAmount >= BONUS_AMOUNT)
            addBonus(getWorkerSalary() * 3 / 2);
    }

}
