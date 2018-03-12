package nl.avans.ivh11.BlindWalls.model;

import lombok.Getter;

/**
 * Created by thomasdelhez on 12-03-18.
 */
@Getter
public class UserInfo {
    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final int phone;

    public UserInfo(String userName, String firstName, String lastName, String email, String address, int phone) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }
}
