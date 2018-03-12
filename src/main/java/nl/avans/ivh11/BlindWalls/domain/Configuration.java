package nl.avans.ivh11.BlindWalls.domain;

// Singleton for config settings and for other information that only requires one class
public final class Configuration {

    private final String version = "0.1";
    private final String name = "Blind Walls Application";
    private final int users =  0;

    private Configuration() {
    }
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public int getUsers() { return users; }

    public static Configuration getConfiguration(){
        return new Configuration();
    }
}