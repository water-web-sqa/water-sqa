package main.service.impl;

import javax.transaction.Transactional;

import main.beans.*;
import main.common.StringConst;
import main.entity.*;
import main.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import main.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HouseHoldService houseHoldService;
    @Autowired
    WatterService watterService;
    @Autowired
    WaterMoneyService waterMoneyService;
    @Autowired
    BillService billService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public User getUserInfoByName(String username) {
        try {
            User result = userRepository.findByUserNameAndDeleteFlg(username, StringConst.DELETE_FLG.NON_DELETE);
            if (result == null || null == result.getUserName()) {
                throw new RuntimeException("username not exist!");
            } else {
                return result;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public HashMap<String, Object> findHouseHoldWater(HouseHoldBeans houseHoldBeans) {
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

    @Override
    public WrapperResponse<CustomerSearchBillResponse> findBillByUser(CustomerSearchBeans customerSearchBeans) {
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
