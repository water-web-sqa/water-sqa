package main.service;

import main.beans.PaymentWaterResponse;
import main.beans.WaterMoneyUpdateBeans;
import main.beans.WrapperResponse;
import main.entity.WaterMoney;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface WaterMoneyService {
    public String getMountOfcodeHouse(String codeHouse);
    public List<WaterMoney> listwaterMoneyNoPayMentByHouse(String codeHouse);
//    public void updateWaterMoney(Integer numberWater, String codeHouse);
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Date date);
    public List<PaymentWaterResponse> listwaterMoneyResponseByHouse(String codeHouse);
    WrapperResponse<Boolean> updateWaterMoney(WaterMoneyUpdateBeans waterMoneyUpdateBeans);
}
