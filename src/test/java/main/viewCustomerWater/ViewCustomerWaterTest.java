package main.viewCustomerWater;

import main.beans.HouseHoldBeans;
import main.beans.WaterMoneyUpdateBeans;
import main.entity.CustomerRegister;
import main.entity.HouseHold;
import main.entity.WaterMoney;
import main.service.CustomerRegisterService;
import main.service.HouseHoldService;
import main.service.WaterMoneyService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewCustomerWaterTest {
    @Autowired
    HouseHoldService houseHoldService;
    @Autowired
    WaterMoneyService waterMoneyService;
    @Test
    // Lấy tất cả hộ sử dụng nước
    // Input: null
    // Expect output: Có dữ liệu trong db - giá trị trả về không được null
    public void getAllHouseHold() {
        try {
            HouseHoldBeans houseHoldBeans = new HouseHoldBeans();
            houseHoldBeans.setCity("Hà Nội");
            List<HouseHold> houseHoldList = houseHoldService.findAllHousehouseByAddress(houseHoldBeans);
            assertNotNull(houseHoldList);
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được danh sách hộ gia đình đang sử dụng nước");
        }
    }

    @Test
    // Cập nhật số nước
    // Input: codeHouse = "MDB01", dateWater = "01/03/2022", numberWater = 3;
    // Expect Output: WaterMoney(numberWater = 3)
    public void updateWater() {
        try {
            Date date = new Date(2022-01-03);
            WaterMoneyUpdateBeans waterMoneyUpdateBeans = new WaterMoneyUpdateBeans("MDB01", 3, date);
            waterMoneyService.updateWaterMoney(waterMoneyUpdateBeans);
            WaterMoney waterMoney = waterMoneyService.findWaterMoneyByHouseHold("MDB01", date);
            assertEquals(3, waterMoney.getNumberWater());
        } catch (Exception e) {
            Assert.fail("Lỗi không chỉnh được số nước cho hộ");
        }
    }

}
