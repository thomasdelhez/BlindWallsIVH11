package nl.avans.ivh11.BlindWalls.controller;

import nl.avans.ivh11.BlindWalls.crosscutting.MyExecutionTime;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/mural")
public class MuralController {

    private final Logger logger = LoggerFactory.getLogger(MuralController.class);
    private ArrayList<Mural> murals = new ArrayList<>();

    // Views constants
    private final String VIEW_LIST_MURALS = "views/mural/list";
    private final String VIEW_CREATE_MURAL = "views/mural/create";
    private final String VIEW_READ_MURAL = "views/mural/read";
    private final String VIEW_EDIT_MURAL = "views/mural/edit";

    @Autowired
    private final MuralRepository muralRepository;
    private final IMuralService muralService;

    // Constructor with Dependency Injection
    public MuralController(IMuralService muralService, MuralRepository muralRepository) {
        this.muralService = muralService;
        this.muralRepository = muralRepository;
        getMuralsFromDB();
    }

    @MyExecutionTime
    @GetMapping
    public String listMurals(
            @RequestParam(value="category", required=false, defaultValue="all") String category,
            @RequestParam(value="size", required=false, defaultValue="10") String size,
            Model model) {

        logger.debug("listMurals called.");
        Iterable<Mural> murals = muralRepository.findAll();

        model.addAttribute("category", category);
        model.addAttribute("size", size);
        model.addAttribute("murals", murals);
        return VIEW_LIST_MURALS;
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Mural mural) {
        return new ModelAndView(VIEW_READ_MURAL, "mural", mural);
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String showCreateMuralForm(final Mural mural, final ModelMap model) {
        logger.debug("showCreateMuralForm");
        return VIEW_CREATE_MURAL;
    }

    @RequestMapping(value="/new", method = RequestMethod.POST)
    public ModelAndView validateAndSaveMural(
            @Valid Mural mural,
            final BindingResult bindingResult,
            RedirectAttributes redirect) {

        logger.debug("validateAndSaveMural - adding mural " + mural.getName());
        if (bindingResult.hasErrors()) {
            logger.debug("validateAndSaveMural - not added, bindingResult.hasErrors");
            return new ModelAndView(VIEW_CREATE_MURAL, "formErrors", bindingResult.getAllErrors());
        }

        //
        // ToDo: volgende acties naar de servicelaag verplaatsen.
        //

        mural = this.muralRepository.save(mural);

//        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
//        return new ModelAndView("redirect:/mural/{mural.id}", "mural.id", mural.getId());

        murals = (ArrayList<Mural>) this.muralRepository.findAll();
        return new ModelAndView(VIEW_LIST_MURALS, "murals", murals);
    }

    @GetMapping(value = "{id}/edit")
    public ModelAndView editMuralForm(@PathVariable("id") Mural mural) {
        logger.debug("edit" + mural.getName() + mural.getDescription());

        return new ModelAndView(VIEW_EDIT_MURAL, "mural", mural);
    }

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    public ModelAndView validateAndEditMural(
            @Valid Mural mural,
            final BindingResult bindingResult,
            RedirectAttributes redirect) {

        logger.debug("validateAndSaveMural - adding mural " + mural.getName());
        if (bindingResult.hasErrors()) {
            logger.debug("validateAndSaveMural - not added, bindingResult.hasErrors");
            return new ModelAndView(VIEW_EDIT_MURAL, "formErrors", bindingResult.getAllErrors());
        }

        //
        // ToDo: volgende acties naar de servicelaag verplaatsen.
        //

        mural.setId(mural.getId());
        mural = this.muralRepository.save(mural);

        murals = (ArrayList<Mural>) this.muralRepository.findAll();
        return new ModelAndView(VIEW_LIST_MURALS, "murals", murals);
    }

    private ArrayList<Mural> getAllMuralsFromAPI() throws JSONException {
        InputStream inputStream;
        String response = "";

        try {
            URL url = new URL("https://api.blindwalls.gallery/apiv2/murals");
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            if(httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }

        if(response.equals("")) {
            return null;
        }

        ArrayList<Mural> murals = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            JSONArray jsonArray2 = obj.getJSONArray("id");

            Mural mural = new Mural();
            mural.setId((long) jsonArray.getJSONObject(i).getInt("id"));
            mural.setName(jsonArray.getJSONObject(i).getString("title"));
            mural.setDescription(jsonArray.getJSONObject(i).getString("description"));

            murals.add(mural);
        }

        if(murals != null) {
            muralRepository.save(murals);
        }

        return murals;
    }

    private String getStringFromInputStream(InputStream inputStream) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    @RequestMapping("")
    public MuralViewModel getMuralsFromDB() {
        if (!muralService.getMuralsFromDB()) {
            try {
                getAllMuralsFromAPI();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
