package main.controller;

import main.beans.WrapperResponse;
import main.common.URLConst;
import main.entity.CustomerRegister;
import main.entity.HouseHold;
import main.repository.HouseHoldRepository;
import main.service.CustomerRegisterService;
import main.service.HouseHoldService;
import main.service.impl.HouseHoldServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Controller
@Transactional
public class RegisterRequestController {
    private static final Logger logger = LogManager.getLogger(WaterController.class);

    @Autowired
    CustomerRegisterService customerRegisterService;

    @Autowired
    HouseHoldService houseHoldService;

    @GetMapping(value = URLConst.Water.GET_REQUEST_RESIGTER_WATER)
    @ResponseBody
    public HashMap<String, Object> getAllRegisterRequest() {
        return customerRegisterService.getAllRegisterRequest();
    }

    @GetMapping(value = URLConst.Water.SET_STATUS_REQUEST)
    @ResponseBody
    public WrapperResponse<Boolean> setStatusRequest(@RequestParam Integer type, @RequestParam Integer id) {
        return customerRegisterService.setStatusRequest(type, id);
    }

    @PostMapping(value = URLConst.Water.UPDATE_CUSTOMER_RESIGTER, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public WrapperResponse<Boolean> updateHouseHold(@RequestBody CustomerRegister customerRegister) {
        return customerRegisterService.updateHouseHold(customerRegister);
    }
}
