import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "purchaselist")
public class Purchaselist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String student_name;
    private String course_name;
    private int price;
    private Date subscription_date;

    public String getStudent_name() {
        return student_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getPrice() {
        return price;
    }

    public Date getSubscription_date() {
        return subscription_date;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSubscription_date(Date subscription_date) {
        this.subscription_date = subscription_date;
    }
}

