package nl.avans.ivh11.BlindWalls.service;

/**
 * Created by thomasdelhez on 12-03-18.
 */

import nl.avans.ivh11.BlindWalls.domain.user.Authenticated;
import nl.avans.ivh11.BlindWalls.domain.user.Offline;
import nl.avans.ivh11.BlindWalls.domain.user.User;
import nl.avans.ivh11.BlindWalls.domain.user.UserStateContext;
import nl.avans.ivh11.BlindWalls.model.LoginViewModel;
import nl.avans.ivh11.BlindWalls.model.UserViewModel;
import nl.avans.ivh11.BlindWalls.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * controleert of de opgegeven inlognaam en wachtwoord overeen komen met een user in de db
     * @param userName inlognaam
     * @param password wachtwoord
     * @return true als user bestaat met deze username wachtwoord combinatie
     */
    public LoginViewModel authenticate(String userName, String password){
        for (User user: userRepository.findAll()){
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
                UserStateContext userStateContext = new UserStateContext();
                userStateContext.setState(new Authenticated());
                return new LoginViewModel(true, user.getId());
            }
        }
        return new LoginViewModel(false, -1);
    }

    public LoginViewModel stateOffline(String userName){
        for (User user: userRepository.findAll()){
            if(user.getUsername().equals(userName)){
                UserStateContext userStateContext = new UserStateContext();
                userStateContext.setState(new Offline());
                return new LoginViewModel(false, user.getId());
            }
        }
        return new LoginViewModel(false, -1);
    }

    /**
     * haalt alle gebruikers op uit de db
     * @return lijst met gebruikers
     */
    public UserViewModel getAllUsers(){
        ArrayList<User> allUsers = (ArrayList<User>)userRepository.findAll();
        return new UserViewModel(allUsers);
    }

    /**
     * voegt een nieuwe user toe in de db
     * @param user toe te voegen user
     */
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUser(String userName, String password){
        for(User user: userRepository.findAll()){
            if(user.getUsername().equals(userName) && user.getPassword().equals(password)) return user;
        }
        return null;
    }

    /**
     * haalt een user op aan de hand van een id
     * @param id id van de op te halen user
     * @return user
     */
    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

}
