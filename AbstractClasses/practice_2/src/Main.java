import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {

        long amount = 12_000_000; // Создаем компанию с доходом 12М
        Company company = new Company(amount);
        ArrayList<Employee> employees = new ArrayList<>();

        // Нанимаем 180 операторов на фиксированную зарплату 100k
        for (int i = 0; i < 180; i++ )
            employees.add(new Operator());
        // Нанимаем 80 менеджеров на фиксированную зарплату с бонусами
        for (int i = 0; i < 80; i++)
            employees.add(new Manager());
        // Нанимаем 10 топ менеджеров на зарплату с бонусами ++
        for (int i = 0; i < 10; i++)
            employees.add(new TopManager(amount));

        // нанимаем сразу всех
        company.hireAll(employees);

        System.out.println("Всего нанято сотрудников = " + company.getEmployees().size());

        System.out.println("10 самых высоких зарплат:");
        for (Employee employee : company.getTopSalaryStaff(10))
            System.out.println(employee.getMonthSalary());

        System.out.println("30 самых низких зарплат:");
        for (Employee employee : company.getLowestSalaryStaff(30))
            System.out.println(employee.getMonthSalary());

        System.out.println("Уволено 50% сотрудников по случайному выбору");
        Collections.shuffle(employees);

        int halfSize = company.getEmployees().size() / 2;
        for (int i = 0; i < halfSize; i++)
            company.fire(company.getEmployees().get(i));
        System.out.println("В компании осталось " + company.getEmployees().size() + " сотрудников.");

        System.out.println("15 самых высоких зарплат:");
        for (Employee employee : company.getTopSalaryStaff(15))
            System.out.println(employee.getMonthSalary());

        System.out.println("30 самых низких зарплат:");
        for (Employee employee : company.getLowestSalaryStaff(30))
            System.out.println(employee.getMonthSalary());

    }

}
