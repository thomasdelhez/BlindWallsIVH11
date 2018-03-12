package nl.avans.ivh11.BlindWalls.controller;

/**
 * Created by thomasdelhez on 12-03-18.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/login")
    public String login(Model model) {
        logger.info("login method was called.");

        model.addAttribute("title", "Hier de titel");
        logger.debug("returning views/login/login.");
        return "views/login/login";
    }

}