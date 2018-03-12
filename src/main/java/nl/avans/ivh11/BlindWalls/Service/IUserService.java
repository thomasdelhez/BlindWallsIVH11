package nl.avans.ivh11.BlindWalls.service;

import nl.avans.ivh11.BlindWalls.domain.user.User;
import nl.avans.ivh11.BlindWalls.model.LoginViewModel;

/**
 * Created by thomasdelhez on 12-03-18.
 */
public interface IUserService {
    void addUser(User user);
    User getUser(String userName, String password);
    LoginViewModel authenticate(String userName, String password);
    User getUser(long id);
}

