package main.service;

import main.beans.HouseHoldBeans;
import main.entity.HouseHold;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HouseHoldService {
    List<HouseHold> findAllHousehouseByAddress(HouseHoldBeans houseHoldBeans);
    void saveHouseHold(HouseHold houseHold);
    HouseHold findByCodeHouse(String codeHouse);
}
