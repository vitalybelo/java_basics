public class Account {

    private long money;                 // количество денег на счету клиента
    private final String accNumber;     // номер расчетного счета клиента
    private boolean statusBlock;        // true если счет блокирован

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
        this.statusBlock = false;
    }

    public long getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void addMoney (long money) {
        this.money += money;
    }

    public void subMoney (long money) {
        this.money -= money;
    }

    public boolean isStatusBlock() {
        return statusBlock;
    }

    public void setStatusBlock(boolean statusBlock) {
        this.statusBlock = statusBlock;
    }
}
