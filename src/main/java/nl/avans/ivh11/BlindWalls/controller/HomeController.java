package nl.avans.ivh11.BlindWalls.controller;

import nl.avans.ivh11.BlindWalls.domain.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String index(Model model) {
        logger.info("index method was called.");

        model.addAttribute("title", "Hier de titel");
        logger.debug("returning views/home/index.");
        return "views/home/index";
    }

    Configuration configurationObject = Configuration.getConfiguration();
    @RequestMapping("/config")
    public String[] configInfo() {
        String name = configurationObject.getName();
        String version = configurationObject.getVersion();
        int usersint = configurationObject.getUsers();
        String users = Integer.toString(usersint);
        String fullinfo[] = new String[2];
        fullinfo[0] = name;
        fullinfo[1] = version;
        fullinfo[2] = users;
        return fullinfo;
    }
}