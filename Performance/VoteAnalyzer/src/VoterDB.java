import java.text.SimpleDateFormat;

public class VoterDB {

    private String name;
    private String birthDay;

    public VoterDB(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    @Override
    public boolean equals(Object obj) {
        VoterDB voter = (VoterDB) obj;
        return name.equals(voter.name) && birthDay.equals(voter.birthDay);
    }


    @Override
    public int hashCode() {
        long code = name.hashCode() + birthDay.hashCode();
        while (code > Integer.MAX_VALUE) {
            code = code / 10;
        }
        return (int) code;
    }

    public String toString() {
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy.MM.dd");
        return name + " (" + birthDay + ")";
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return birthDay;
    }
}
