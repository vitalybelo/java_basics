import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    List<Subscription> subscriptions;

    @Override
    public String toString() {
        return name;
    }

}