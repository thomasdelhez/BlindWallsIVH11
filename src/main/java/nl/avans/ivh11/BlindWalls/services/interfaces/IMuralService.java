package nl.avans.ivh11.BlindWalls.services.interfaces;

import nl.avans.ivh11.BlindWalls.domain.Mural;
import nl.avans.ivh11.BlindWalls.viewModel.MuralViewModel;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMuralService {

    MuralViewModel getAllMurals(long muralId);
    boolean getMuralsFromDB();
}
