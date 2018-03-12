package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */
public class Offline implements UserStateInterface {

    @Override
    public void logged() {
        System.out.println("You are offline");
    }
}

