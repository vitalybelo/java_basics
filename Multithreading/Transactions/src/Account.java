public class Account {

    private long money;
    private String accNumber;

    public Account(long money, String accNumber) {
        this.money = money;
        this.accNumber = accNumber;
    }

    public long getMoney() {
        return money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public void setMoney(long money) {
        this.money = money;
    }

}
