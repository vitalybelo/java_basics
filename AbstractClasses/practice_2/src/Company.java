import java.util.*;

public class Company {

    private long companyInCome;
    private final ArrayList<Employee> employees;

    public Company(long companyInCome) {
        employees = new ArrayList<>();
        this.companyInCome = companyInCome;
    }

    public Company() {
        this(10_000_000);
    }

    public void setCompanyInCome(long companyInCome) {
        this.companyInCome = companyInCome;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public double getIncome() {
        return companyInCome;
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll (Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public boolean fire(Employee employee) {
        return employees.remove(employee);
    }

    public double getWorkerSalary(int index) {
        return employees.get(index).getMonthSalary();
    }


    private List<Employee> sortList(int count, Comparator<Employee> comparator) {
        List<Employee> sortedList = new ArrayList<Employee>(employees);
        List<Employee> returnList = new ArrayList<Employee>();
        sortedList.sort(comparator); //         Collections.sort(sortedList, comparator);

        for (int i = 0; i < count; i++)
            returnList.add(sortedList.get(i));
        return returnList;
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return sortList(count, (o1, o2) -> o1.getMonthSalary() - o2.getMonthSalary());
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return sortList(count, (o1, o2) -> o2.getMonthSalary() - o1.getMonthSalary());
    }


}
