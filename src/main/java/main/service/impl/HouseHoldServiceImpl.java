package main.service.impl;

import main.beans.HouseHoldBeans;
import main.entity.HouseHold;
import main.repository.HouseHoldRepository;
import main.service.HouseHoldService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class HouseHoldServiceImpl implements HouseHoldService {
    private static final Logger logger = LogManager.getLogger(HouseHoldServiceImpl.class);

    @Autowired
    HouseHoldRepository houseHoldRepository;

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
}
