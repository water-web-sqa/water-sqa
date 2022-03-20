package main.service.impl;

import main.beans.HouseHoldBeans;
import main.entity.HouseHold;
import main.repository.HouseHoldRepository;
import main.service.HouseHoldService;
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
    @Autowired
    HouseHoldRepository houseHoldRepository;

    @Override
    public List<HouseHold> findAllHousehouseByAddress(HouseHoldBeans houseHoldBeans) {
        return houseHoldRepository.findByAddressAndName("%" + houseHoldBeans.getCity() + "%", "%" + houseHoldBeans.getDistrinct() + "%",
                "%" + houseHoldBeans.getWard() + "%", "%" + houseHoldBeans.getNamehouse() + "%");
    }

    @Override
    public void saveHouseHold(HouseHold houseHold) {
        houseHoldRepository.save(houseHold);
    }

    @Override
    public HouseHold findByCodeHouse(String codeHouse) {
        return houseHoldRepository.findByCodeHouse(codeHouse);
    }

    @Override
    public String lastId() {
        return houseHoldRepository.lastId().getCodeHouse();
    }
}
