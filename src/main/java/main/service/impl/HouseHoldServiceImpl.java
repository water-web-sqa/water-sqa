package main.service.impl;

import main.beans.HouseHoldBeans;
import main.beans.HouseHoldWaterBeans;
import main.beans.WrapperResponse;
import main.entity.HouseHold;
import main.repository.HouseHoldRepository;
import main.service.HouseHoldService;
import main.service.WaterMoneyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class HouseHoldServiceImpl implements HouseHoldService {
    private static final Logger logger = LogManager.getLogger(HouseHoldServiceImpl.class);

    @Autowired
    HouseHoldRepository houseHoldRepository;
    @Autowired
    WaterMoneyService waterMoneyService;

    @Override
    public List<HouseHold> findAllHousehouseByAddress(HouseHoldBeans houseHoldBeans) {
        try {
            return houseHoldRepository.findByAddressAndName("%" + houseHoldBeans.getCity() + "%", "%" + houseHoldBeans.getDistrinct() + "%",
                    "%" + houseHoldBeans.getWard() + "%", "%" + houseHoldBeans.getNamehouse() + "%");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void saveHouseHold(HouseHold houseHold) {
        try {
            houseHoldRepository.save(houseHold);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public HouseHold findByCodeHouse(String codeHouse) {
        try {
            return houseHoldRepository.findByCodeHouse(codeHouse);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String lastId() {
        try {
            return houseHoldRepository.lastId().getCodeHouse();
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
                houseHolds = findAllHousehouseByAddress(houseHoldBeans);
            } else {
                HouseHold item = findByCodeHouse(houseHoldBeans.getCodehouse());
                if(item != null) {
                    houseHolds.add(item);
                }
            }
            List<HouseHoldWaterBeans> list = new ArrayList<>();
            houseHolds.forEach(houseHold -> {
                list.add(new HouseHoldWaterBeans(houseHold, waterMoneyService.findWaterMoneyByHouseHold(houseHold.getCodeHouse(),
                        houseHoldBeans.getTimesearch())));
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
    public WrapperResponse<Boolean> updateHouseHold(HouseHold houseHold) {
        WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
        try {
            response.setStatus(200);
            response.setBody(true);
            saveHouseHold(houseHold);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        response.setStatus(200);
        response.setBody(true);
        return null;
    }
}
