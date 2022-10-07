import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private Key id;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}