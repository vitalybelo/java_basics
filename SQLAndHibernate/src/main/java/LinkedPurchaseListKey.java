import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
class LinkedPurchaseListKey implements Serializable {

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "student_id")
    private int studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedPurchaseListKey that)) return false;
        return courseId == that.courseId && studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }

}
