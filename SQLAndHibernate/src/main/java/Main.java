import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * В задании сказано:
 * "Напишите код, который выведет среднее количество покупок в месяц для каждого курса за 2018 год."
 * Для получения ответа нужно знать для каждого курса 2 величины:
 * 1. количество продаж данного курса
 * 2. сколько месяцев продавался данный курс
 *
 * Самый простой способ это сделать - sql запрос с подсчетом count(*) и группировкой по курсам.
 * select
 *      course_name,
 *      count(*) count_sales,
 *      max(month(subscription_date)) count_months
 * from skillbox.purchaselist
 * group by course_name
 * ..... Этим запросом мы получим ответ на все вопросы !!!
 *
 * Но чтобы усложнить задачу :)) для тренировки и загрузки ресурсов процессора, есть 2-ое решение:
 * которое выполняет группировку по курсам программным образом, определяет количество продаж по
 * каждому курсу за весь отчетный период период, вычисляет среднее количество продаж по каждому
 * курсу за отчетный период + отображает список студентов купивших конкретный курс
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("\nРЕШЕНИЕ №1. Один SQL запрос для получения ответа:\n");
        solutionOne();

        System.out.println("\nРЕШЕНИЕ №2. Много SQL запросов:\n");
        solutionTwo();

    }

    public static void solutionOne() {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Vitalex88";

        try {
            ResultSet resultSet;
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();

            // Считываем названия курсов
            String request =
                    "select course_name, count(*) count_sales, max(month(subscription_date)) count_months " +
                    "from skillbox.purchaselist group by course_name";

            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                System.out.print("Название курса: " + resultSet.getString("course_name"));
                float salesCount = resultSet.getFloat("count_sales");
                float monthCount = resultSet.getFloat("count_months");
                float average = monthCount == 0 ? 0.0f : salesCount / monthCount;
                System.out.println(" :: среднее кол-во продаж в месяц = " + average);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void solutionTwo () {

        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "Vitalex88";

        try {
            ResultSet resultSet;
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            List<String> courseName = new ArrayList<>();

            // Считываем названия курсов в строковую коллекцию
            String request = "select name from skillbox.courses";
            resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                courseName.add(resultSet.getString("name"));
            }

            // Считываем список продаж по каждому курсу
            for (String s : courseName) {
                System.out.println("Название курса : \"" + s + "\"");
                System.out.println("\t\tДаты покупок и имена студентов : ");
                request =
                        "select student_name, subscription_date, month(subscription_date) month" +
                        " from skillbox.purchaselist where course_name = '" + s +
                        "' order by subscription_date";
                resultSet = statement.executeQuery(request);
                int sales = 0;
                int month = 0;
                while (resultSet.next()) {
                    String name = resultSet.getString("student_name");
                    String date = resultSet.getString("subscription_date");
                    System.out.println("\t\t" + date + " :: " + name);
                    month = Math.max(month, resultSet.getInt("month"));
                    sales++;
                }
                System.out.println("\t\t...\n\t\tКурс продавался: " + month + " месяцев");
                System.out.println("\t\tВсего за " + month + " месяцев продано " + sales + " курсов");
                float average = (month == 0 ? 0.0f : (float) sales / (float) month);
                System.out.println("\t\tСреднее количество покупок в месяц: " + average + "\n\t\t...");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}