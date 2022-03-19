package main.service;

import main.beans.WaterMoneyUpdateBeans;
import main.entity.WaterMoney;

import java.util.Date;
import java.util.List;

public interface WaterMoneyService {
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Date date);
    public void updateWaterMoney(WaterMoneyUpdateBeans waterMoneyUpdateBeans);
}
