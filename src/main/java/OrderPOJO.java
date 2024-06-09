import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class OrderPOJO {
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String rentTime;
    private String deliveryDate;
    private String comment;
    private String [] color;

    public OrderPOJO (String firstName, String lastName, String address, String metroStation, String phone, String rentTime, String deliveryDate, String comment, String [] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }
}
