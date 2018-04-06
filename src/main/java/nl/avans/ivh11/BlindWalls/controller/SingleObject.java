package nl.avans.ivh11.BlindWalls.controller;

import nl.avans.ivh11.BlindWalls.domain.user.User;

import java.util.ArrayList;

/**
 * Created by thomasdelhez on 05-04-18.
 */

public class SingleObject {

    private static SingleObject instance;
    private static ArrayList<User> users = new ArrayList<>();

    private SingleObject(){}

    public static synchronized SingleObject getInstance(){
        if(instance == null){
            instance = new SingleObject();
            showMessage("new");
        }
        else {
            showMessage("old");
        }
        return instance;

    }

    public static void showMessage(String text){
        System.out.println(text);
    }

    public static void addUserToSingleton(User user){

        if (users.contains(user)){
            System.out.println("User already logged in");
        }
        else {
        users.add(user);
        }

        for(int i = 0; i < users.size(); i++) {
            System.out.print("Authenticated user: " + users.get(i).getFirstname());
            System.out.println("");
        }
    }

    public static void removeUserFromSingleton(User user){
        users.remove(user);
    }

    public static ArrayList<User> getLoggedinUsers(){
        return users;
    }

}