package main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Controller
public class HomeController extends BaseController{
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController() {

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homeView(HttpServletRequest request) {

        return new ModelAndView("redirect:/water");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model, HttpServletRequest request, Principal principal) {
        if(principal != null){
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView user(Model model, HttpServletRequest request, Principal principal) {

        return new ModelAndView("main");
    }
}

