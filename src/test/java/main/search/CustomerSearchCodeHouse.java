package main.search;

import main.beans.CustomerSearchBeans;
import main.beans.CustomerSearchBillResponse;
import main.beans.WrapperResponse;
import main.entity.Bill;
import main.entity.HouseHold;
import main.entity.WaterMoney;
import main.service.BillService;
import main.service.HouseHoldService;
import main.service.UserService;
import main.service.WaterMoneyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerSearchCodeHouse {
    @Autowired
    UserService userService;
    @Autowired
    HouseHoldService houseHoldService;
    @Autowired
    WaterMoneyService waterMoneyService;
    @Autowired
    BillService billService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    // Lấy Household theo id tương ứng
    // Input: id = MDB01
    // Expect output: Household(name_house = "Nguyễn Viết Cường", data_birth = "1999-12-14")
    public void findHouseHoldById() {
        try {
            HouseHold houseHold = houseHoldService.findByCodeHouse("MDB01");
            assertNotNull(houseHold);
            assertEquals(houseHold.getNameHouse(), "Nguyễn Viết Cường");
            assertEquals(houseHold.getDataBirth(), sdf.parse("1999-12-14"));
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được Household theo id = MDB01");
        }
    }

    @Test
    // Lấy Số nước tương ứng theo mã danh bộ và thời gian
    // Input: codehouse = "MDB01", date = "01/03/2022"
    // Expect output: WaterMoney(id = 19, numberWater = 20, codeHouse = "MDB01")
    public void findWaterMoneyByHouseHold() {
        try {
            WaterMoney waterMoney = waterMoneyService.findWaterMoneyByHouseHold("MDB01", sdf.parse("2022-03-01"));
            waterMoney.getCodeHouse();
            assertNotNull(waterMoney);
            assertEquals(waterMoney.getCodeHouse(), "MDB01");
            assertEquals(waterMoney.getNumberWater(), 20);
            assertEquals(waterMoney.getId(), 19);
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được WaterMoney tương ứng");
        }
    }

    @Test
    // Lấy hóa đơn tương ứng theo id của water money
    // Input: id = 19
    // Expect output: Bill = null (Do chưa thanh toán)
    public void getBillByWaterMoney() {
        try {
            Bill bill = billService.getBillByWaterNumber(88);
            assertNull(bill);
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được Bill tương ứng");
        }
    }

    @Test
    // Lấy hóa đơn tương ứng theo id của water money
    // Input: id = 19
    // Expect output: Bill = (id = 50, idStaff = 1)
    public void getBillByWaterMoneyDone() {
        try {
            Bill bill = billService.getBillByWaterNumber(19);
            assertEquals((int) bill.getId(), 50);
            assertEquals((int) bill.getIdStaff(), 1);
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được Bill tương ứng");
        }
    }

    @Test
    // Lấy hóa đơn tương ứng theo mã danh bộ và thời gian
    // Input: codehouse = "MDB01", date = "01/03/2022"
    // Expect output: WrapperResponse(msg = "Success", status = 200, body = {houseHold{nameHouse = Nguyễn Viết Cường},
    // waterMoney{numberWater = 20}, bill{idStaff = 1}, nameSupplier = "Công ty Nước sạch Hà Nội (HAWACOM)"}
    public void findBillByUser() {
        try {
            CustomerSearchBeans beans = new CustomerSearchBeans("MDB01", sdf.parse("2022-03-01"));
            WrapperResponse<CustomerSearchBillResponse> result = userService.findBillByUser(beans);
            result.getMsg();
            assertNotNull(result);
            assertEquals(result.getMsg(), "Success");
            assertEquals((int) result.getStatus(), 200);
            assertEquals(result.getBody().getHouseHold().getNameHouse(), "Nguyễn Viết Cường");
            assertEquals(result.getBody().getWaterMoney().getNumberWater(), 20);
            assertEquals((int) result.getBody().getBill().getIdStaff(), 1);
            assertEquals(result.getBody().getNameSupplier(), "Công ty Nước sạch Hà Nội (HAWACOM)");

        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được Bill tương ứng");
        }
    }
}
