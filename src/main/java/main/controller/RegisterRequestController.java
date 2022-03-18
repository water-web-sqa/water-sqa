package main.controller;

import main.common.URLConst;
import main.entity.CustomerRegister;
import main.service.CustomerRegisterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Controller
@Transactional
public class RegisterRequestController {
//    private static final Logger logger = LogManager.getLogger(WaterController.class);
//
//    @Autowired
//    CustomerRegisterService customerRegisterService;
//
//    @GetMapping(value = URLConst.Water.GET_WATER_SUPPLIER)
//    @ResponseBody
//    public HashMap<String, Object> getAllRegisterRequest() {
//        HashMap<String, Object> result = new HashMap<>();
//        try {
//            List<CustomerRegister> list = customerRegisterService.allCustomerRegister();
//            result.put("draw", 1);
//            result.put("recordsTotal", list.size());
//            result.put("recordsFiltered", list.size());
//            result.put("data", list);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return result;
//    }
}
