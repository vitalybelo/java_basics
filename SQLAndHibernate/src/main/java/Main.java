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
        try (SessionFactory sessionFactory = getSessionFactory())
        {
            session = sessionFactory.openSession();
            List<PurchaseList> pList = getAllPurchaseList(session);

            for (PurchaseList pl : pList)
                System.out.println(pl.getCourseName() + " ::\t" + pl.getStudentName());
            System.out.println("\nСчитано записей: " + pList.size());
            System.out.println(PurchaseList.class);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert session != null;
            session.close();
        }
    }

    public static List<PurchaseList> getAllPurchaseList(Session session) {

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