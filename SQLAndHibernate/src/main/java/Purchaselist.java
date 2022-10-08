import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchaselist")
@Getter
@Setter
public class PurchaseList {

    @EmbeddedId
    private PurchaseListKey id;

    @Column(name = "student_name", insertable = false, updatable = false)
    private String studentName;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "student_name", referencedColumnName = "name", insertable = false, updatable = false)
//    Student student;

    @Column(name = "course_name", insertable = false, updatable = false)
    private String courseName;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "course_name", referencedColumnName = "name", insertable = false, updatable = false)
//    Course course;

    private int price;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;
}