package group.intelliboys.cimsfrontend.models.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TmpUser {
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String middleName;
    private String sex;
    private String birthDate;
    private String email;
    private String role;
    private byte age;
    private String address;
    private byte[] profilePic;
}