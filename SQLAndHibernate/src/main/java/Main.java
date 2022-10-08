import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Session session = null;
        try (SessionFactory sessionFactory = getSessionFactory())
        {
            session = sessionFactory.openSession();
            List<Course> cList = getCourseList(session);
            List<Student> sList = getStudentList(session);
            List<PurchaseList> pList = getPurchaseList(session);

            Transaction transaction = session.beginTransaction();
            for (PurchaseList pl : pList) {

                /**
                 * Всего 3 запроса к базе. при этом создается 3 коллекции в памяти
                 * id курсов и студентов выбираются из коллекций по названию
                 */
                int courseId = getCourseId(cList, pl.getCourseName());
                int studentId = getStudentId(sList, pl.getStudentName());
                System.out.println(pl.getCourseName() + " = " + courseId +
                                    " :: " + pl.getStudentName() + " = " + studentId);
                /**
                 * Сначала сделал связку в классе PurchaseList с классами
                 * Student и Course чтобы "легко" получать id курсов и студентов
                 * НО ... этот способ при всей своей лаконичности в коде генерирует
                 * очень много запросов к базе (я насчитал 46 запросов) и переделал
                 int courseId = pl.getCourse().getId();
                 int studentId = pl.getStudent().getId();
                 */
                LinkedPurchaseList linked = new LinkedPurchaseList();
                linked.setCourseId(courseId);
                linked.setStudentId(studentId);
                linked.setId(new LinkedPurchaseListKey(courseId, studentId));
                session.save(linked);
            }
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static int getCourseId (List<Course> courses, String name) {
        for (Course course : courses)
            if (course.getName().equals(name))
                return course.getId();
        return 0;
    }

    public static int getStudentId (List<Student> students, String name) {
        for (Student student : students)
            if (student.getName().equals(name))
                return student.getId();
        return 0;
    }

    public static List<Student> getStudentList(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        CriteriaQuery<Student> all = cq.select(root);
        TypedQuery<Student> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<Course> getCourseList(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Course> cq = cb.createQuery(Course.class);
        Root<Course> root = cq.from(Course.class);
        CriteriaQuery<Course> all = cq.select(root);
        TypedQuery<Course> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static List<PurchaseList> getPurchaseList(Session session) {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PurchaseList> cq = cb.createQuery(PurchaseList.class);
        Root<PurchaseList> root = cq.from(PurchaseList.class);
        CriteriaQuery<PurchaseList> all = cq.select(root);
        TypedQuery<PurchaseList> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static SessionFactory getSessionFactory () {

        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }
}