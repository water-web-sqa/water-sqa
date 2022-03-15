package main.service;

import main.entity.HouseHold;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HouseHoldService {
    List<HouseHold> findAllHousehouseByAddress(String address);
    void saveHouseHold(HouseHold houseHold);
}
