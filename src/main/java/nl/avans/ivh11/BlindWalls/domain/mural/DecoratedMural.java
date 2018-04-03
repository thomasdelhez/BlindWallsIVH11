package nl.avans.ivh11.BlindWalls.domain.mural;

public class DecoratedMural extends MuralDecorator {

    public DecoratedMural(MuralInterface newMural) {
        super(newMural);
    }

    public String getName() {
        return tempMural.getName();
    }

    @Override
    public void setName(String name) {

    }

    public String getDescription() {
        return tempMural.getDescription();
    }

    @Override
    public void setDescription(String description) {

    }
}
