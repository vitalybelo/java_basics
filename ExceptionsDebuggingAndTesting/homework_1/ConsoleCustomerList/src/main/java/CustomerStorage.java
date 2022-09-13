import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String regexPhone = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
        String regexEmail = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            Main.errorLoger.info("Неверный формат строки добавления: add " + data);
            throw new ArrayIndexOutOfBoundsException("Неправильный формат строки. Правильно:" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        if (!components[INDEX_PHONE].matches(regexPhone)) {
            Main.errorLoger.info("Неверный формат номера телефона: " + components[INDEX_PHONE]);
            throw new IllegalArgumentException ("Неверный формат номера телефона");
        };
        if (!components[INDEX_EMAIL].matches(regexEmail)) {
            Main.errorLoger.info("Неверный формат электронной почты: " + components[INDEX_EMAIL]);
            throw new IllegalArgumentException ("Неверный формат электронной почты");
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));

    }

    public void listCustomers() {
        if (storage.size() > 0)
            storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        if (!storage.containsKey(name)) {
            Main.errorLoger.info("Удаление не выполнено, клиент " + name + " не найден.");
            throw new IllegalArgumentException("Удаление не выполнено, клиент " + name + " не найден.");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}