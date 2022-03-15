package main.service.impl;

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
    public List<HouseHold> findAllHousehouseByAddress(String address) {
        return houseHoldRepository.findAllByAddressLike(address);
    }

    @Override
    public void saveHouseHold(HouseHold houseHold) {
        houseHoldRepository.save(houseHold);
    }
}
