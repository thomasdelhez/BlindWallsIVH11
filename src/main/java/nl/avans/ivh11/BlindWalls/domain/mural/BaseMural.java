package nl.avans.ivh11.BlindWalls.domain.mural;

public class BaseMural implements MuralInterface {

    private String name;
    private String description;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }
}
