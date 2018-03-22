package nl.avans.ivh11.BlindWalls.viewModel;

import nl.avans.ivh11.BlindWalls.domain.Mural;

public class MuralViewModel {

    private final Iterable<Mural> murals;

    public MuralViewModel(Iterable<Mural> murals) {
        this.murals = murals;
    }
}
