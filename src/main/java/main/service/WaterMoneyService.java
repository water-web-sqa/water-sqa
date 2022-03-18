package main.service;

import main.entity.WaterMoney;

import java.util.List;

public interface WaterMoneyService {
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Integer month, Integer year);
//    public void updateWaterMoney(Integer numberWater, String codeHouse);
}
