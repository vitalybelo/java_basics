import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int numberAccounts = 100;

    public static void main(String[] args) {

        Bank accounts = new Bank();
        initBankAccounts(accounts, numberAccounts);

        long startAmount = accounts.getSumAllAccounts();
        List<String> accountId = accounts.getKeySet().stream().toList();

        for (int t = 0; t < 8; t++) {
            new Thread(()-> {
                for (int i = 1; i < accounts.size(); i++) {
                    long amount = (long) (40_500 + Math.random() * 10_000);
                    accounts.transfer(accountId.get(i - 1), accountId.get(i), amount);
                }
            }).start();
        }

        long finalAmount = accounts.getSumAllAccounts();
        System.out.println("\nОбщая сумма на счетах до операций = " + startAmount);
        System.out.println("Общая сумма на счетах после операций = " + finalAmount + "\n");

    }

    public static void initBankAccounts (Bank bank, int numberAccounts) {
        // создаем счета в банке
        for (int i = 0; i < numberAccounts; i++) {
            String accountName = "ID_" + (i + 1);
            String accountNumber = "440500005599" + Math.round(999 + (Math.random() * (double) 9000));
            long amount = (long)(100_000 + Math.random() * 100_000);
            bank.put(accountName, new Account(amount,accountNumber));
        }
    }
}
