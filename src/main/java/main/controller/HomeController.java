package main.controller;

import main.common.CommonConst;
import main.common.URLConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
public class HomeController extends BaseController{
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    public HomeController() {

    }
    @RequestMapping(value = "/" , method = RequestMethod.GET)
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

    @RequestMapping(value = URLConst.User.WATER_HOME_USER, method = RequestMethod.GET)
    public ModelAndView home(Model model, HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        session.setAttribute("pageActive", CommonConst.COMMON_USER_PAGE.WATER);
        return new ModelAndView("home");
    }

    @RequestMapping(value = URLConst.User.WATER_HOME_USER_1, method = RequestMethod.GET)
    public ModelAndView register(Model model, HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        session.setAttribute("pageActive", CommonConst.COMMON_USER_PAGE.WATER1);
        return new ModelAndView("registerwatercustomer");
    }

    @RequestMapping(value = URLConst.User.WATER_HOME_USER_2, method = RequestMethod.GET)
    public ModelAndView payment(Model model, HttpServletRequest request, Principal principal) {
        HttpSession session = request.getSession();
        session.setAttribute("pageActive", CommonConst.COMMON_USER_PAGE.WATER2);
        return new ModelAndView("payment");
    }
}

