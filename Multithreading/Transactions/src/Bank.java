import java.util.*;

public class Bank {

    private Map<String, Account> accounts;          // < account ID , Account >
    private final Random random = new Random();
    private final int trustAmount = 50_000;

    public Bank() {
        accounts = new TreeMap<>();
    }

    public void put(String accountId, Account account) {
        this.accounts.put(accountId, account);
    }

    public String toString(String key) {
        return "номер счета: " + getAccountNumber(key) + " :: сумма = " + getBalance(key);
    }

    public int size() {
        return accounts.size();
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountId, String toAccountId, long amount) {

        Account accountOff = accounts.get(fromAccountId);
        Account accountOn = accounts.get(toAccountId);

        System.out.print("счет списания: " + accountOff.getAccNumber());
        System.out.print("  :: счет зачисления: " + accountOn.getAccNumber());
        System.out.print("  :: сумма перевода = " + amount + "\n");
        if (accountOff.getMoney() >= amount) {
            accountOn.addMoney(amount);
            accountOff.subMoney(amount);
        } else {
            System.err.println("Недостаточно средств на счету: " + accountOff.getAccNumber());
            System.err.println("Операция отменена.");
            return;
        }

//        if (amount > trustAmount) {
//            try {
//                if (isFraud(fromAccountId, toAccountId, amount)) return;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
    }

    public long getBalance(String accountId) {
        return accounts.get(accountId).getMoney();
    }

    public String getAccountNumber (String accountId) {
        return accounts.get(accountId).getAccNumber();
    }

    public Set<String> getKeySet() {
        return accounts.keySet();
    }

    public long getSumAllAccounts() {
        long total = 0;
        for (String accountId : getKeySet()) {
            total += accounts.get(accountId).getMoney();
        }
        return total;
    }
}
