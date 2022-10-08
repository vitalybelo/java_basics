import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;


}