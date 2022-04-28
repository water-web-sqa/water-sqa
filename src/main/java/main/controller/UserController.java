package main.controller;

import main.beans.*;
import main.common.URLConst;
import main.entity.Bill;
import main.entity.HouseHold;
import main.entity.WaterMoney;
import main.entity.WaterSupplier;
import main.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Transactional
public class UserController {

    private static final Logger logger = LogManager.getLogger(WaterController.class);

    @Autowired
    UserService userService;

    @PostMapping(value = URLConst.User.USER_SEARCH_HOUSEHOLD, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, Object> findHouseHoldWater(@RequestBody HouseHoldBeans houseHoldBeans) {
        return userService.findHouseHoldWater(houseHoldBeans);
    }

    @PostMapping(value = URLConst.User.USER_SEARCH_BILL, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public WrapperResponse<CustomerSearchBillResponse> findBillByUser(@RequestBody CustomerSearchBeans customerSearchBeans) {
        return userService.findBillByUser(customerSearchBeans);
    }
}
