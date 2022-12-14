import java.util.List;

public class Main {

    private static final int numberAccounts = 100;

    public static void main(String[] args) {

        Bank accounts = new Bank();
        initBankAccounts(accounts, numberAccounts);

        long startAmount = accounts.getSumAllAccounts();
        System.out.println("\nОбщая сумма на счетах до операций = " + startAmount);
        List<String> accountId = accounts.getKeySet().stream().toList();

        for (int t = 0; t < 8; t++) {
            new Thread(()-> {
                for (int i = 1; i < accounts.size(); i++) {
                    long amount = (long) (40_500 + Math.random() * 10_000);
                    int result = accounts.transfer(accountId.get(i - 1), accountId.get(i), amount);
                    switch (result) {
                        case -1 -> System.err.println("Карта заблокирована");
                        case -2 -> System.err.println("Недостаточно средств для перевода");
                        case -3 -> System.err.println("Операция отменена службой безопасности");
                        default -> System.out.println(accounts.getSumAllAccounts());
                    }
                }
            }).start();
        }

        long finalAmount = accounts.getSumAllAccounts();
        System.out.println("Общая сумма на счетах после операций = " + finalAmount + "\n");
    }

    public static void initBankAccounts (Bank bank, int numberAccounts) {
        // создаем счета в банке
        for (int i = 0; i < numberAccounts; i++) {
            String accountName = "ID_" + (i + 1);
            String accountNumber = "440500005599" + Math.round(999 + (Math.random() * 9_000));
            long amount = (long)(200_000 + Math.random() * 100_000);
            bank.put(accountName, new Account(amount,accountNumber));
        }
    }
}
