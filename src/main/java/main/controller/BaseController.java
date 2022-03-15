package main.controller;

import lombok.extern.log4j.Log4j2;
import main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import main.common.CommonConst;
import main.service.UserService;

@Log4j2
public class BaseController {

    @Autowired
    UserService userService;

    public final User getUser(String username) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                String userCd = auth.getName();
                return userService.getUserInfoByName(userCd);
            }
            return new User();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new User();
        }
    }

    @ModelAttribute("userLogin")
    public User userLogin(String username) {
        return this.getUser(username);
    }

    @ModelAttribute("version")
    public String getServerVersion() {
        return CommonConst.SERVER_VERSION;
    }

}
