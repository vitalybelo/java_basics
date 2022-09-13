import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Main {

    public static Logger logger;
    public static Logger errorLoger;
    public static Logger queryLoger;
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        logger = LogManager.getRootLogger();
        errorLoger = LogManager.getLogger("ErrorLogger");
        queryLoger = LogManager.getLogger("QueriesLogger");

        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);

            queryLoger.info("Введена консольная команда: " + command);
            try {
                switch (tokens[0]) {
                    case "add":
                        executor.addCustomer(tokens[1]);
                        break;
                    case "list":
                        executor.listCustomers();
                        break;
                    case "remove":
                        if (tokens.length == 1) {
                            errorLoger.info("Попытка удаления записи без параметра");
                            throw new IllegalArgumentException
                                    ("\nНе указано имя клиента. Правильно:\nremove Василий Петров.\n");
                        }
                        executor.removeCustomer(tokens[1]);
                        break;
                    case "count":
                        System.out.println("There are " + executor.getCount() + " customers");
                        break;
                    case "help":
                        System.out.println(helpText);
                        break;
                    default:
                        System.out.println(COMMAND_ERROR);
                        break;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException ex) {
                logger.info("Обработана ошибка: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
