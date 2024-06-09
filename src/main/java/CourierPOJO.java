import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CourierPOJO {
    private String login;
    private String password;
    private String firstName;
    private Integer id;

    public CourierPOJO (String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }
    public CourierPOJO (String login, String password) {
        this.login = login;
        this.password = password;
    }
    public CourierPOJO () {
    }
    @Override
    public String toString() {
        return "login: " + this.login + "; password: " + this.password + "; firstName: " + this.firstName + "; id: " + this.id;
    }
}
