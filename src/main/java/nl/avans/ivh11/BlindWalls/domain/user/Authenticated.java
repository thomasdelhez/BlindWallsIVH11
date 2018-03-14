package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */
public class Authenticated implements UserStateInterface {

    public Authenticated(){
        logged();
    }

    @Override
    public void logged() {
        System.out.println("State: Authenticated");
    }
}

