package main.service.impl;

import main.beans.WaterMoneyUpdateBeans;
import main.common.CommonConst;
import main.entity.Bill;
import main.entity.WaterMoney;
import main.repository.BillRespository;
import main.repository.HouseHoldRepository;
import main.repository.WaterMoneyRepository;
import main.service.WaterMoneyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Repository
@Transactional(rollbackOn = Exception.class)
@EnableTransactionManagement(proxyTargetClass = true)
public class WaterMoneyServiceImpl implements WaterMoneyService {
    private static final Logger logger = LogManager.getLogger(WaterMoneyServiceImpl.class);

    @Autowired
    WaterMoneyRepository waterMoneyRepository;

    @Autowired
    HouseHoldRepository houseHoldRepository;

    @Autowired
    BillRespository billRespository;

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
    public String getMountOfcodeHouse(String codeHouse) {
        try {
            List<WaterMoney> waterMoneyList = listwaterMoneyNoPayMentByHouse(codeHouse);
            int mount = 0;
            for(WaterMoney waterMoney : waterMoneyList){
                    // cach tinh ho gia dinh
                    String checkHouseHold = houseHoldRepository.findByCodeHouse(waterMoney.getCodeHouse()).getTypeHouse();
                    if(checkHouseHold.equals("0")){
                        // ho gia dinh
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 1){
                            mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_1*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 2){
                            mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_2*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 3){
                            mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_3*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 4){
                            mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_4*waterMoney.getNumberWater();
                        }
                        mount = mount + mount*CommonConst.HOUSE_HOLD_VALID.THUE_VAT + mount*CommonConst.HOUSE_HOLD_VALID.THUE_MOI_TRUONG;
                    }

                    if(checkHouseHold.equals("1")){
                        // ho gia dinh
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 1){
                            mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 2){
                            mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_2*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 3){
                            mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_3*waterMoney.getNumberWater();
                        }
                        if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 4){
                            mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_4*waterMoney.getNumberWater();
                        }
                        mount = mount + mount*CommonConst.POOR_HOUSE_HOLD_VALID.THUE_VAT + mount*CommonConst.POOR_HOUSE_HOLD_VALID.THUE_MOI_TRUONG;
                    }
            }
            return String.valueOf(mount);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<WaterMoney> listwaterMoneyNoPayMentByHouse(String codeHouse) {
        try {
            List<WaterMoney> waterMoneyList = waterMoneyRepository.getWaterMoneyByCodeHouse(codeHouse);
            List<WaterMoney> result = new ArrayList<>();
            Collections.sort(waterMoneyList, new Comparator<WaterMoney>() {
                @Override
                public int compare(WaterMoney o1, WaterMoney o2) {
                    return o1.getDateWater().compareTo(o2.getDateWater());
                }
            });

            List<Bill> billList = billRespository.findAll();
            Integer sum = 0;
            for(WaterMoney waterMoney : waterMoneyList){
                boolean checkBill = false; // chưa in
                for(Bill bill: billList){
                    if(bill.getIdWaterMoney() == waterMoney.getId()){
                        checkBill = true;
                    }
                }
                if(!checkBill) result.add(waterMoney);
            }
            return result;
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
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
