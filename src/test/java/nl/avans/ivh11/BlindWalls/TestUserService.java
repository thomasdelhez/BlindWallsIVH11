package nl.avans.ivh11.BlindWalls;

import org.junit.Assert;
        import org.junit.Rule;
        import org.junit.Test;
        import org.mockito.Mockito;
        import org.mockito.junit.MockitoJUnit;
        import org.mockito.junit.MockitoRule;
        import nl.avans.ivh11.BlindWalls.service.UserService;
        import nl.avans.ivh11.BlindWalls.domain.user.User;
        import nl.avans.ivh11.BlindWalls.repository.UserRepository;
        import java.util.ArrayList;
        import static org.mockito.Mockito.when;

/**
 * Created by thomasdelhez on 05-04-18.
 */
public class TestUserService {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testAuthenticateWrong(){

        ArrayList<User> testUsers = new ArrayList<>();
        testUsers.add(new User.UserBuilder("firstname", "lastname", "address", "password", "username").build());

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(testUsers);

        UserService service = new UserService(userRepository);

        Assert.assertFalse(service.authenticate("wrongUserName", "wrongPassword").isAuthenticated());
    }

    @Test
    public void testAuthenticateGood(){
        ArrayList<User> testUsers = new ArrayList<>();
        testUsers.add(new User.UserBuilder("firstname", "lastname", "username", "password", "email").build());

        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(testUsers);

        UserService service = new UserService(userRepository);

        Assert.assertTrue(service.authenticate("username", "password").isAuthenticated());
    }
}

