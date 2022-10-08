import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private SubscriptionKey id;

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

}