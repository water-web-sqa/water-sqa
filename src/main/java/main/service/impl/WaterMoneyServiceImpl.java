package main.service.impl;

import main.beans.PaymentWaterResponse;
import main.beans.WaterMoneyUpdateBeans;
import main.beans.WrapperResponse;
import main.common.CommonConst;
import main.entity.Bill;
import main.entity.User;
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
        try {
            SimpleDateFormat  sdf = new SimpleDateFormat("dd/MM/yyyy");
            String str = sdf.format(date);
            WaterMoney result = waterMoneyRepository.findDateWaterLast(codeHouse, Integer.parseInt(str.substring(3,5)),
                    Integer.parseInt(str.substring(6, str.length())));
            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
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
            List<WaterMoney> waterMoneyList = waterMoneyRepository.getOldestUpdateWaterMoneyByCodeHouse(codeHouse);
            List<WaterMoney> result = new ArrayList<>();
            Collections.sort(waterMoneyList, new Comparator<WaterMoney>() {
                @Override
                public int compare(WaterMoney o1, WaterMoney o2) {
                    return o1.getDateWater().compareTo(o2.getDateWater());
                }
            });

            List<Bill> billList = billRespository.findAll();
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

    @Override
    public WrapperResponse<Boolean> updateWaterMoney(WaterMoneyUpdateBeans waterMoneyUpdateBeans) {
        WrapperResponse<Boolean> response = new WrapperResponse<>();
        try {
            WaterMoney waterMoney = new WaterMoney();
            waterMoney.setCodeHouse(waterMoneyUpdateBeans.getCodeHouse());
            waterMoney.setNumberWater(waterMoneyUpdateBeans.getNumberWater());
            waterMoney.setDateWater(waterMoneyUpdateBeans.getDateWater());
            waterMoney.setCreatedAt(LocalDateTime.now());
            waterMoneyRepository.save(waterMoney);
            response.setStatus(200);
            response.setBody(true);
            response.setMsg("Success");
        } catch (Exception e) {
            response.setStatus(400);
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @Override
    public List<PaymentWaterResponse> listwaterMoneyResponseByHouse(String codeHouse) {
        List<PaymentWaterResponse> listReSult = new ArrayList<>();
        try {
            List<WaterMoney> listwaterMoneyNoPayMentByHouse = listwaterMoneyNoPayMentByHouse(codeHouse);
            for(WaterMoney waterMoney: listwaterMoneyNoPayMentByHouse){
                PaymentWaterResponse paymentWaterResponse = new PaymentWaterResponse();
                paymentWaterResponse.setIdWaterMoney(waterMoney.getId());
                paymentWaterResponse.setNumberWater(waterMoney.getNumberWater());
                paymentWaterResponse.setDateWater(CommonConst.DATE_FORMAT.formatterYYYY_MM.format(waterMoney.getDateWater()));
                String checkHouseHold = houseHoldRepository.findByCodeHouse(waterMoney.getCodeHouse()).getTypeHouse();
                int mount = 0;
                if(checkHouseHold.equals("0")){
                    // ho gia dinh
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 1){
                        mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_1*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_1.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 2){
                        mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_2*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_2.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 3){
                        mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_3*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_3.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 4){
                        mount += CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_4*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.HOUSE_HOLD_VALID.HOUSE_HOLD_VALID_BAC_4.toString());
                    }
                    mount = mount + mount*CommonConst.HOUSE_HOLD_VALID.THUE_VAT + mount*CommonConst.HOUSE_HOLD_VALID.THUE_MOI_TRUONG;
                }

                if(checkHouseHold.equals("1")){
                    // ho gia dinh
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 1){
                        mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 2){
                        mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_2*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 3){
                        mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_3*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1.toString());
                    }
                    if(CommonConst.getBacHouseHold(waterMoney.getNumberWater()) == 4){
                        mount += CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_4*waterMoney.getNumberWater();
                        paymentWaterResponse.setPriceBybac(CommonConst.POOR_HOUSE_HOLD_VALID.POOR_HOUSE_HOLD_VALID_BAC_1.toString());
                    }
                    mount = mount + mount*CommonConst.POOR_HOUSE_HOLD_VALID.THUE_VAT + mount*CommonConst.POOR_HOUSE_HOLD_VALID.THUE_MOI_TRUONG;
                }

                paymentWaterResponse.setSumPrice(mount);
                listReSult.add(paymentWaterResponse);
            }
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
        return listReSult;
    }
}
