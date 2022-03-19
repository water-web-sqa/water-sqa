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
    HouseHoldService houseHoldService;
    @Autowired
    WatterService watterService;
    @Autowired
    WaterMoneyService waterMoneyService;
    @Autowired
    BillService billService;

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
//                list.add(new HouseHoldWaterBeans(houseHold, waterMoneyService.findWaterMoneyByHouseHold(houseHold.getCodeHouse())));
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

    @PostMapping(value = URLConst.User.USER_SEARCH_BILL, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public WrapperResponse<CustomerSearchBillResponse> findBillByUser(@RequestBody CustomerSearchBeans customerSearchBeans) {
        WrapperResponse<CustomerSearchBillResponse> result = new WrapperResponse<>();
        try {
            HouseHold houseHold = houseHoldService.findByCodeHouse(customerSearchBeans.getCodeHouse());
            WaterMoney waterMoney = waterMoneyService.findWaterMoneyByHouseHold(customerSearchBeans.getCodeHouse(), customerSearchBeans.getDate());
            Bill bill;
            if (waterMoney == null) {
                bill = null;
            } else {
                bill = billService.getBillByWaterNumber(waterMoney.getId());
            }
            WaterSupplier supplier = watterService.findById(houseHold.getIdSupplier());
            result.setStatus(200);
            result.setMsg("Success");
            result.setBody(new CustomerSearchBillResponse(houseHold, waterMoney, bill, supplier.getNameSupplier()));
        } catch (Exception e) {
            result.setStatus(400);
            result.setMsg("Error");
        }
        return result;
    }
}
