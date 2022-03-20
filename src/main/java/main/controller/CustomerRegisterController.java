package main.controller;

import main.beans.WrapperResponse;
import main.common.URLConst;
import main.entity.CustomerRegister;
import main.service.CustomerRegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomerRegisterController {
    private static final Logger logger = LogManager.getLogger(CustomerRegisterController.class);

    @Autowired
    CustomerRegisterService customerRegisterService;

    @PostMapping(value = URLConst.User.ADD_CUSTOMER_REGISTER, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public WrapperResponse<Boolean> updateHouseHold(@RequestBody CustomerRegister customerRegister) {
        WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
        try {
            response.setStatus(200);
            response.setBody(true);
            customerRegisterService.save(customerRegister);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        response.setStatus(200);
        response.setBody(true);
        return null;
    }
}
