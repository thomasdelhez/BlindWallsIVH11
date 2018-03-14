package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String firstname; //required
    private String lastname; //required
    private String address; //optional
    private String emailaddress; //required
    private String username; //required
    private String password; //required
    private int telephonenumber; //optional
    private boolean isadmin; //required

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
