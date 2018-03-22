package nl.avans.ivh11.BlindWalls.domain.user;

/**
 * Created by thomasdelhez on 12-03-18.
 */
public class UserStateContext implements UserStateInterface {

    private UserStateInterface state;

    public UserStateInterface getState() {
        return state;
    }

    @Override
    public void logged() {
        this.state.logged();
    }

    public void setState(UserStateInterface state) {
        this.state = state;
    }
}
