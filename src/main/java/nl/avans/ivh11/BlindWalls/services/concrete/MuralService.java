package nl.avans.ivh11.BlindWalls.services.concrete;

import nl.avans.ivh11.BlindWalls.domain.Mural;
import nl.avans.ivh11.BlindWalls.repository.MuralRepository;
import nl.avans.ivh11.BlindWalls.services.interfaces.IMuralService;
import nl.avans.ivh11.BlindWalls.viewModel.MuralViewModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class MuralService implements IMuralService {

    private static final Logger logger = LoggerFactory.getLogger(MuralService.class);
    private final MuralRepository muralRepository;

    @Autowired
    public MuralService(MuralRepository muralRepository) {
        this.muralRepository = muralRepository;
    }

    @Override
    public MuralViewModel getAllMurals(long muralId) {
        return null;
    }

    @Override
    public boolean getMuralsFromDB() {
        return muralRepository.count() > 0;
    }

}

