package main.service;

import main.entity.WaterMoney;

import java.util.List;

public interface WaterMoneyService {
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse);
    public void updateWaterMoney(Integer numberWater, String codeHouse);
}
