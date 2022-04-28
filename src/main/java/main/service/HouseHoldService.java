package main.service;

import main.beans.HouseHoldBeans;
import main.beans.WrapperResponse;
import main.entity.HouseHold;

import java.util.HashMap;
import java.util.List;

public interface HouseHoldService {
    List<HouseHold> findAllHousehouseByAddress(HouseHoldBeans houseHoldBeans);
    void saveHouseHold(HouseHold houseHold);
    HouseHold findByCodeHouse(String codeHouse);
    String lastId();

    HashMap<String, Object> findHouseHoldWater(HouseHoldBeans houseHoldBeans);
    WrapperResponse<Boolean> updateHouseHold(HouseHold houseHold);
}
