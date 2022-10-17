import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeMap;

public class Main {

    private static final int numberAccounts = 20;

    public static void main(String[] args) {

        Bank accounts = new Bank();
        initBankAccounts(accounts, numberAccounts);

        System.out.println(accounts.size());
        for (String key : accounts.getKeys())
            System.out.println(key + "\t:: " + accounts.toString(key));

        System.out.println("Общая сумма на счетах = " + accounts.getSumAllAccounts());

    }

    public static void initBankAccounts (Bank bank, int numberAccounts) {

        for (int i = 0; i < numberAccounts; i++) {
            String accountName = String.valueOf(i+1);
            String accountNumber = "440500005599" + Math.round(999 + (Math.random() * (double) 9000));
            long amount = (long)(3_000 + Math.random() * (double) 10_000);
            bank.put(accountName, new Account(amount,accountNumber));
        }
    }
}
