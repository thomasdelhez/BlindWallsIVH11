package nl.avans.ivh11.BlindWalls.controller;

/**
 * Created by thomasdelhez on 12-03-18.
 */
import nl.avans.ivh11.BlindWalls.domain.user.User;
import nl.avans.ivh11.BlindWalls.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/login")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private ArrayList<User> users = new ArrayList<>();

    //Views Constants
    private final String VIEW_CREATE_USER = "views/login/newUser";
    private final String VIEW_LIST_USERS = "views/login/list";
    private final String VIEW_LOGIN_USER = "views/login/userLogin";

    @Autowired
    private final UserRepository userRepository;

    // Constructor with Dependency Injection
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public String listUsers(
            @RequestParam(value="category", required=false, defaultValue="all") String category,
            @RequestParam(value="size", required=false, defaultValue="10") String size,
            Model model) {

        logger.debug("listUsers called.");
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("category", category);
        model.addAttribute("size", size);
        model.addAttribute("users", users);
        return VIEW_LIST_USERS;
    }

    @RequestMapping(value = "/userLogin")
    public String login(Model model) {
        logger.info("login method was called.");

        model.addAttribute("title", "Hier de titel");
        logger.debug("returning views/login/login.");
        return VIEW_LOGIN_USER;
    }

    @RequestMapping(value="/newUser", method = RequestMethod.GET)
    public String showNewUserForm(final User user, final ModelMap model) {
        logger.debug("showNewUserForm");
        return VIEW_CREATE_USER;
    }

    @RequestMapping(value="/newUser", method = RequestMethod.POST)
    public ModelAndView validateAndSaveUser(
            @Valid User user,
            final BindingResult bindingResult,
            RedirectAttributes redirect) {

        logger.debug("validateAndSaveMural - adding mural " + user.getFirstname());
        if (bindingResult.hasErrors()) {
            logger.debug("validateAndSaveMural - not added, bindingResult.hasErrors");
            return new ModelAndView(VIEW_CREATE_USER, "formErrors", bindingResult.getAllErrors());
        }

        //
        // ToDo: volgende acties naar de servicelaag verplaatsen.
        //

        user = this.userRepository.save(user);

//        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
//        return new ModelAndView("redirect:/mural/{mural.id}", "mural.id", mural.getId());

        users = (ArrayList<User>) this.userRepository.findAll();
        return new ModelAndView(VIEW_LIST_USERS, "users", users);
    }

}