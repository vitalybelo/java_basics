import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int duration;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    private String description;

    //@Column(name = "teacher_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher teacher;

    @Column(name = "students_count", nullable = true)
    private Integer studentsCount;

    private int price;

    @Column(name = "price_per_hour")
    private float pricePerHour;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    List<Subscription> subscriptions;

    public Course(String name, int duration, CourseType type, String description, Teacher teacher, int studentsCount, int price, float pricePerHour) {
        this.name = name;
        this.duration = duration;
        this.type = type;
        this.description = description;
        this.teacher = teacher;
        this.studentsCount = studentsCount;
        this.price = price;
        this.pricePerHour = pricePerHour;
    }

    public Course(String name, CourseType type, Teacher teacher) {
        this(name, 0, type,  "", teacher, 0, 0, 0.0f);
    }
    public Course() {
        this("", 0, CourseType.UNDEFINED,  "", null, 0, 0, 0.0f);
    }

}
