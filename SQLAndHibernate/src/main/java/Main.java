import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        Session session = null;
        try {
            session = getSessionFactory().openSession();

            // Считываем одну запись из таблицы
            Course course = session.get(Course.class, 1);
            System.out.println("Название курса: " + course.getName() +
                    " :: количество студентов: " + course.getStudentsCount() + "\n\n");

            // Считываем 45 записей в коллекцию
            // (в базе есть students_count = null в id = 46 его пока не могу победить)
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Course> cq = cb.createQuery(Course.class);
            Root<Course> rootEntry = cq.from(Course.class);
            CriteriaQuery<Course> all = cq.select(rootEntry);
            TypedQuery<Course> allQuery = session.createQuery(all);
            List<Course> courses = allQuery.setMaxResults(45).getResultList();

            for (Course c : courses)
                System.out.println("Название курса: " + c.getName() +
                        " --> количество студентов: " + c.getStudentsCount());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static SessionFactory getSessionFactory () {

        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();

        return metadata.getSessionFactoryBuilder().build();
    }
}