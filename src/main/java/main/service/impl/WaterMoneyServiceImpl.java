package main.service.impl;

import main.entity.WaterMoney;
import main.repository.WaterMoneyRepository;
import main.service.WaterMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class WaterMoneyServiceImpl implements WaterMoneyService {

    @Autowired
    WaterMoneyRepository waterMoneyRepository;

    @Override
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Integer month, Integer year) {
        WaterMoney result = waterMoneyRepository.findDateWaterLast(codeHouse, month, year);
        return result;
    }

//    @Override
//    public void updateWaterMoney(Integer numberWater, String codeHouse) {
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            WaterMoney waterMoney = waterMoneyRepository.findDateWaterLast(codeHouse);
//            if(waterMoney != null && formatter.format(waterMoney.getDateWater()).compareTo(formatter.format(new Date())) == 0) {
//                waterMoneyRepository.deleteDateWaterNow(codeHouse);
//            }
//            waterMoneyRepository.saveWaterMoney(numberWater, codeHouse);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
}
