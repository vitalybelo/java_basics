import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseListKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

}