package main;

import main.entity.HouseHold;
import main.service.HouseHoldService;
import main.service.WaterMoneyService;
import main.service.WatterService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentWaterTest {
    @Autowired
    WaterMoneyService waterMoneyService;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private WatterService watterService;

    @Test
    public void testHouseHoldByCodeWater() {
        // MDB01 - Nguyễn Viết Cường
        HouseHold houseHold = houseHoldService.findByCodeHouse("MDB01");
        assertNotNull(houseHold);
    }
}
