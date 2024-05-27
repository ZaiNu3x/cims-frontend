package group.intelliboys.cimsfrontend.models.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String sex;
    private String birthDate;
    private byte age;
    private String address;
    private String email;
    private byte[] profilePic;
}
