public class CourierPOJO {
    private String login;
    private String password;
    private String firstName;
    private Integer id;
    public CourierPOJO (String login, String password, String firstName, Integer id) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CourierPOJO () {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "login: " + this.login + "; password: " + this.password + "; firstName: " + this.firstName + "; id: " + this.id;
    }
}
