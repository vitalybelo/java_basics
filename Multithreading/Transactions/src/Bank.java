import java.util.*;

public class Bank {

    private final Map<String, Account> accounts;          // < account ID , Account >
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

    public int transfer(String fromAccountId, String toAccountId, long amount) {

        Account accountOn = accounts.get(toAccountId);
        Account accountOff = accounts.get(fromAccountId);

        if (accountOn.isStatusBlock() || accountOff.isStatusBlock()) {
            return -1; // one of accounts is blocked
        }

        if (accountOff.getMoney() >= amount) {
            synchronized (accounts) {
                accountOff.subMoney(amount);
                accountOn.addMoney(amount);
            }
        } else return -2; // refuse amount to transfer

        if (amount > trustAmount) {
            try {
                if (isFraud(fromAccountId, toAccountId, amount)) {
                    synchronized (accounts) {
                        accountOn.subMoney(amount);
                        accountOff.addMoney(amount);
                        accountOn.setStatusBlock(true);
                        accountOff.setStatusBlock(true);
                    }
                    return -3; // refuse security
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0; // success
    }

    public Account getAccount (String accountId) {
        return accounts.get(accountId);
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
