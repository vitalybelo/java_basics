import java.util.*;

public class Bank {

    private Map<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new TreeMap<>();
    }

    public void put(String name, Account account) {
        this.accounts.put(name, account);
    }

    public String getAccountNumber (String key) {
        return accounts.get(key).getAccNumber();
    }

    public String toString(String key) {
        return "Номер счета: " + getAccountNumber(key) + " :: сумма = " + getBalance(key);
    }

    public int size() {
        return accounts.size();
    }

    public Set<String> getKeys() {
        return accounts.keySet();
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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long total = 0;
        for (String key : getKeys())
            total += getBalance(key);
        return total;
    }
}
