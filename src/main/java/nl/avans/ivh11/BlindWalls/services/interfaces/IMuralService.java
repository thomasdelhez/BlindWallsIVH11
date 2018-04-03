package nl.avans.ivh11.BlindWalls.services.interfaces;

import nl.avans.ivh11.BlindWalls.domain.mural.Mural;
import nl.avans.ivh11.BlindWalls.viewModel.MuralViewModel;
import org.json.JSONException;

import java.util.ArrayList;

public interface IMuralService {

    MuralViewModel getAllMurals(long muralId);
    boolean getMuralsFromDB();
    ArrayList<Mural> getAllMuralsFromAPI() throws JSONException;
}
