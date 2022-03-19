package main.service;

import main.beans.WaterMoneyUpdateBeans;
import main.entity.WaterMoney;

import java.util.Date;
import java.util.List;

public interface WaterMoneyService {
    public String getMountOfcodeHouse(String codeHouse);
    public List<WaterMoney> listwaterMoneyNoPayMentByHouse(String codeHouse);
//    public void updateWaterMoney(Integer numberWater, String codeHouse);
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Date date);
    public void updateWaterMoney(WaterMoneyUpdateBeans waterMoneyUpdateBeans);
}
