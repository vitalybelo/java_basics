import junit.framework.TestCase;
import java.util.List;

public class TestBank extends TestCase {

    private Bank bank;

    @Override
    protected void setUp() throws Exception {

        bank = new Bank();
        bank.put("ID_1",new Account(110000,"4504000056040001"));
        bank.put("ID_2",new Account(100000,"4504000056040002"));
        bank.put("ID_3",new Account(75000,"4504000056040003"));
        bank.put("ID_4",new Account(35000,"4504000056040004"));
        bank.put("ID_5",new Account(90000,"4504000056040005"));

    }

    public void testGetBalance () {

        List<String> accountId = bank.getKeySet().stream().toList();
        long[] balances = new long[] {110_000,100_000,75_000,35_000,90_000};
        for (int i = 0; i < balances.length; i++) {
            long balance = bank.getBalance(accountId.get(i));
            assertEquals(balances[i],balance);
        }
    }

    public void testTotalBalance () {

        long total1 = bank.getSumAllAccounts();
        long total2 = 410_000;
        assertEquals(total2, total1);
    }


    public void testOnceTransfer () {

        bank.transfer("ID_1", "ID_2",15300);
        long result1 = bank.getBalance("ID_1");
        long result2 = bank.getBalance("ID_2");
        long answer1 = 94_700;
        long answer2 = 115_300;
        assertEquals(result1, answer1);
        assertEquals(result2, answer2);
    }

    public void testOnceTransferRefuseAmount () {
        long answer4 = 35_000;
        long answer5 = 90_000;
        bank.transfer("ID_3", "ID_4",100_000);
        long result4 = bank.getBalance("ID_4");
        long result5 = bank.getBalance("ID_5");

        assertEquals(result4, answer4);
        assertEquals(result5, answer5);
    }

    public void testOnceTransferAccountIsBlocked () {

        long answer3 = 75_000;
        long answer4 = 35_000;
        bank.getAccount("ID_3").setStatusBlock(true);
        bank.transfer("ID_3", "ID_4",55_000);
        long result3 = bank.getBalance("ID_3");
        long result4 = bank.getBalance("ID_4");

        assertEquals(result3, answer3);
        assertEquals(result4, answer4);

        bank.transfer("ID_4", "ID_3",10000);
        result3 = bank.getBalance("ID_3");
        result4 = bank.getBalance("ID_4");

        assertEquals(result3, answer3);
        assertEquals(result4, answer4);
        bank.getAccount("ID_3").setStatusBlock(false);

    }


}
