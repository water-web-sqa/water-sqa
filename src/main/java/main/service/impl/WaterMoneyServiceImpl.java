package main.service.impl;

import main.beans.WaterMoneyUpdateBeans;
import main.entity.WaterMoney;
import main.repository.WaterMoneyRepository;
import main.service.WaterMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    public WaterMoney findWaterMoneyByHouseHold(String codeHouse, Date date) {
        SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
        String str = sdf.format(date);
        System.out.println(str);
        WaterMoney result = waterMoneyRepository.findDateWaterLast(codeHouse, Integer.parseInt(str.substring(3,5)),
                Integer.parseInt(str.substring(6, str.length())));
        return result;
    }

    @Override
    public void updateWaterMoney(WaterMoneyUpdateBeans waterMoneyUpdateBeans) {
        try {
            WaterMoney waterMoney = new WaterMoney();
            waterMoney.setCodeHouse(waterMoneyUpdateBeans.getCodeHouse());
            waterMoney.setNumberWater(waterMoneyUpdateBeans.getNumberWater());
            waterMoney.setDateWater(waterMoneyUpdateBeans.getDateWater());
            waterMoney.setCreatedAt(LocalDateTime.now());
            waterMoneyRepository.save(waterMoney);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
