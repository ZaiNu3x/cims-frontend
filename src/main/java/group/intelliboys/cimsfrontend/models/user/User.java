package group.intelliboys.cimsfrontend.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;
    private String sex;
    private LocalDate birthDate;
    private byte age;
    private String address;
    private byte[] profilePic;
}
