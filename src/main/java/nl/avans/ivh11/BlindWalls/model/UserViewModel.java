package nl.avans.ivh11.BlindWalls.model;

import lombok.Getter;
import nl.avans.ivh11.BlindWalls.domain.user.User;

import java.util.ArrayList;

/**
 * Created by thomasdelhez on 12-03-18.
 */
@Getter
public class UserViewModel extends ArrayList<User> {
    private final Iterable<User> users;

    public UserViewModel(ArrayList<User> allUsers) {
        this.users = allUsers;
    }
}
