import java.sql.Date;

public class Patient {

    private String name;
    private String surname;
    private String CF;
    private Date birthDate;
    private String address;
    private String email;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCF() {
        return CF;
    }
}
