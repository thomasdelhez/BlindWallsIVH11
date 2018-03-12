package nl.avans.ivh11.BlindWalls.domain;

// Singleton for config settings and for other information that only requires one class
public final class Configuration {

    private final String version = "0.1";
    private final String name = "Blind Walls Application";
    private final int users =  0;

    private Configuration() {
    }

    public static Configuration getConfiguration(){
        return new Configuration();
    }
}