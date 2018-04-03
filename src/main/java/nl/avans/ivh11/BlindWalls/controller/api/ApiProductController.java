package nl.avans.ivh11.BlindWalls.controller.api;

import nl.avans.ivh11.BlindWalls.domain.mural.Mural;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/mural")
public class ApiProductController {

    private final Logger logger = LoggerFactory.getLogger(ApiProductController.class);

    private ArrayList<Mural> murals = new ArrayList<>();

    @GetMapping(value = "/")
    ArrayList<Mural> getProductsAsJSON() {
        logger.trace("getProducts was called.");
        return murals;
    }

    @PostMapping(value = "/")
    ArrayList<Mural> createProductAsJSON(@RequestBody Mural mural) {
        logger.trace("createProduct was called.");
        murals.add(mural);
        return murals;

    }

}
