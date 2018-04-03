package nl.avans.ivh11.BlindWalls.domain.mural;

public abstract class MuralDecorator implements MuralInterface {

    protected MuralInterface tempMural;

    public MuralDecorator(MuralInterface newMural) {
        tempMural = newMural;
    }

    public String getName() {
        return tempMural.getName();
    }

    public void setName() {

    }

    public String getDescription() {
        return tempMural.getDescription();
    }

    public void setDescription() {

    }

}
