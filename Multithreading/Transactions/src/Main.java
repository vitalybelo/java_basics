import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;

public class Main {

    private static final int numberAccounts = 99;

    public static void main(String[] args) {

        Bank accounts = new Bank();
        initBankAccounts(accounts, numberAccounts);

        System.out.println(accounts.size());
        int counter = 0;
        for (String key : accounts.getKeys()) {
            System.out.println(key + "\t:: " + accounts.toString(key));
            if (accounts.getBalance(key) > 50_000) counter++;
        }
        System.out.println(counter);
        System.out.println("Общая сумма на счетах = " + accounts.getSumAllAccounts());

    }

    public static void initBankAccounts (Bank bank, int numberAccounts) {
        //
        for (int i = 0; i < numberAccounts; i++) {
            String accountName = String.valueOf(i+1);
            String accountNumber = "440500005599" + Math.round(999 + (Math.random() * (double) 9000));
            long amount = (long)(40_500 + Math.random() * (double) 10_000);
            bank.put(accountName, new Account(amount,accountNumber));
        }
    }
}
