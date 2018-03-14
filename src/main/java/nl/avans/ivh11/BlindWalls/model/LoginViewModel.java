package nl.avans.ivh11.BlindWalls.model;

import lombok.Getter;

/**
 * Created by thomasdelhez on 12-03-18.
 */
@Getter
public class LoginViewModel {
    private final boolean authenticated;
    private final long userId;

    public LoginViewModel(boolean authenticated, long userId) {
        this.authenticated = authenticated;
        this.userId = userId;
    }
}