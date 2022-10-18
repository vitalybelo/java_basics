import java.util.List;

public class Main {

    private static final int numberAccounts = 5;

    public static void main(String[] args) {

        Bank accounts = new Bank();
        initBankAccounts(accounts, numberAccounts);

        for (String key : accounts.getKeySet()) {
            System.out.println(key + "\t:: " + accounts.toString(key));
        }
        System.out.println("Общая сумма на счетах = " + accounts.getSumAllAccounts() + "\n");
        List<String> accountId = accounts.getKeySet().stream().toList();
        accounts.transfer(accountId.get(0), accountId.get(1), 2000);

        for (String key : accounts.getKeySet()) {
            System.out.println(key + "\t:: " + accounts.toString(key));
        }

    }

    public static void initBankAccounts (Bank bank, int numberAccounts) {
        // создаем счет в банке
        for (int i = 0; i < numberAccounts; i++) {
            String accountName = "ID_" + (i + 1);
            String accountNumber = "440500005599" + Math.round(999 + (Math.random() * (double) 9000));
            long amount = (long)(40_500 + Math.random() * (double) 10_000);
            bank.put(accountName, new Account(amount,accountNumber));
        }
    }
}
