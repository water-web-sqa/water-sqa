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
    public WrapperResponse<Boolean> addCustomerResigter(@RequestBody CustomerRegister customerRegister) {
        return customerRegisterService.addCustomerResigter(customerRegister);
    }
}
