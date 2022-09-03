public abstract class Worker implements Employee {

    private static final int MIN_SALARY = 100_000;
    private static final int MAX_SALARY = 150_000;
    private int workerSalary;

    public Worker() {
        int difference = MAX_SALARY - MIN_SALARY;
        workerSalary = (int)(Math.random() * difference + MIN_SALARY);
        workerSalary = (int)Math.round(workerSalary / 100.00) * 100;
    }

    public int getWorkerSalary() {
        return workerSalary;
    }

    public void addBonus (int bonus) {
        workerSalary += (int) Math.round(bonus / 100.00) * 100;
    }

    @Override
    public int getMonthSalary() {
        return workerSalary;
    }

}
