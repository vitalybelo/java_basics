import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

import static org.hibernate.id.PersistentIdentifierGenerator.PK;

public class Main {

    public static void main(String[] args) {

        Session session = null;
        try (SessionFactory sessionFactory = getSessionFactory())
        {
            session = sessionFactory.openSession();

            Course course = session.get(Course.class, 3);
            System.out.println();
            System.out.println("название курса: " + course.getName());
            System.out.println("тип курса: " + course.getType());
            System.out.println("описание: " + course.getDescription());
            System.out.println("преподаватель: " + course.getTeacher().getName());
            System.out.println();

            List<Student> studentList = course.getStudents();

            System.out.println("Список студентов курса:");
            for (Student s : studentList)
                System.out.println(s.toString());

            Subscription subscription = session.get(Subscription.class,new Key(1,2));
            System.out.println("\n" + subscription.getSubscriptionDate());


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