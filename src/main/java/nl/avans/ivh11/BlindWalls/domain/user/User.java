package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty(message = "Voornaam is verplicht.")
    private String firstname; //required
    @NotEmpty(message = "Achternaam is verplicht.")
    private String lastname; //required
    private String address; //optional
    @NotEmpty(message = "Email is verplicht.")
    private String emailaddress; //required
    @NotEmpty(message = "Usernaam is verplicht.")
    private String username; //required
    @NotEmpty(message = "Password is verplicht.")
    private String password; //required
    private int telephonenumber; //optional
    private boolean isadmin; //auto

    private User(UserBuilder builder) {
        this.firstname = builder.firstName;
        this.lastname = builder.lastName;
        this.address = builder.address;
        this.telephonenumber = builder.telephoneNumber;
        this.username = builder.userName;
        this.password = builder.password;
        this.emailaddress = builder.emailAddress;
        this.isadmin = builder.isAdmin;
    }

    public static class UserBuilder {
        private final String firstName;
        private final String lastName;
        private String address;
        private int telephoneNumber;
        private final String userName;
        private final String password;
        private final String emailAddress;
        private boolean isAdmin;

        public UserBuilder(String firstName, String lastName, String userName, String password, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.password = password;
            this.emailAddress = emailAddress;
            this.isAdmin = false;
        }

        public UserBuilder telephoneNumber(int telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public UserBuilder address(String address){
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
