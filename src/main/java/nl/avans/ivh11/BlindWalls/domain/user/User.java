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
    private String firstName; //required
    private String lastName; //required
    private String address; //required
    private String emailAddress; //required
    private String userName; //required
    private String password; //required
    private int age; //required
    private int telephoneNumber; //optional

    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.age = builder.age;
        this.telephoneNumber = builder.telephoneNumber;
        this.userName = builder.userName;
        this.password = builder.password;
        this.emailAddress = builder.emailAddress;
    }

    public static class UserBuilder {

        private final String firstName;
        private final String lastName;
        private final String address;
        private final int age;
        private int telephoneNumber;
        private final String userName;
        private final String password;
        private final String emailAddress;

        public UserBuilder(String firstName, String lastName, String address, int age, String userName, String password, String emailAddress) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.age = age;
            this.userName = userName;
            this.password = password;
            this.emailAddress = emailAddress;
        }

        public UserBuilder telephoneNumber(int telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
