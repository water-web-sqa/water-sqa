package main.controller;

import main.beans.HouseHoldBeans;
import main.beans.HouseHoldWaterBeans;
import main.common.URLConst;
import main.entity.HouseHold;
import main.service.HouseHoldService;
import main.service.UserService;
import main.service.WaterMoneyService;
import main.service.WatterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    HouseHoldService houseHoldService;
    @Autowired
    WatterService watterService;
    @Autowired
    WaterMoneyService waterMoneyService;

    @PostMapping(value = URLConst.User.USER_SEARCH_HOUSEHOLD, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public HashMap<String, Object> findHouseHoldWater(@RequestBody HouseHoldBeans houseHoldBeans) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<HouseHold> houseHolds = new ArrayList<>();
            if(houseHoldBeans.getCodehouse().equals("")) {
                houseHolds = houseHoldService.findAllHousehouseByAddress(houseHoldBeans);
            } else {
                HouseHold item = houseHoldService.findByCodeHouse(houseHoldBeans.getCodehouse());
                if(item != null) {
                    houseHolds.add(item);
                }
            }
            List<HouseHoldWaterBeans> list = new ArrayList<>();
            houseHolds.forEach(houseHold -> {
                list.add(new HouseHoldWaterBeans(houseHold, waterMoneyService.findWaterMoneyByHouseHold(houseHold.getCodeHouse())));
            });

            result.put("draw", 1);
            result.put("recordsTotal", list.size());
            result.put("recordsFiltered", list.size());
            result.put("data", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
