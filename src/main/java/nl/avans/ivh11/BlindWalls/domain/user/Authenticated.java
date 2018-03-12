package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */
public class Authenticated implements UserStateInterface {

    @Override
    public void logged() {
        System.out.println("You successfully logged in");
    }
}

