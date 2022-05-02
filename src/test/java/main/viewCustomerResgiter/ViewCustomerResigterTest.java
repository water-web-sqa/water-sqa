package main.viewCustomerResgiter;

import main.entity.CustomerRegister;
import main.service.CustomerRegisterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewCustomerResigterTest {
    @Autowired
    CustomerRegisterService customerRegisterService;

    @Test
    @Before
    // Lấy CustomerRequest theo id tương ứng
    // Input: id = 54
    // Expect output: CustomerRequest(name_house = "Tien Dat", data_birth = "2000-10-12")
    public void findResigterRequestById() {
        try {
            CustomerRegister customerRegister = customerRegisterService.findById(54);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            assertNotNull(customerRegister);
            assertEquals(customerRegister.getNameHouse(), "Tien Dat");
            assertEquals(customerRegister.getDataBirth(), sdf.parse("2000-10-12"));
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được CustomerRequest theo id = 54");
        }
    }

    @Test
    // Lấy tất cả nhà danh sách đăng ký nước có trong db
    // Input: null
    // Expect output: Có dữ liệu trong db - giá trị trả về không được null
    public void getAllResigterRequest() {
        try {
            List<CustomerRegister> customerRegisterList = customerRegisterService.allCustomerRegister();
            assertNotNull(customerRegisterList);
        } catch (Exception e) {
            Assert.fail("Lỗi không lấy được danh sách đăng ký");
        }
    }

    @Test
    // Cập nhật trạng thái cho request
    // Input: id = 54, status = 0
    // Expect Output: CustomerRequest(status = 21)
    public void updateStatus() {
        try {
            Integer status = 1;
            customerRegisterService.updateStatus(status, 54);
            CustomerRegister customerRegister = customerRegisterService.findById(54);
            assertEquals(1, customerRegister.getStatus());
        } catch (Exception e) {
            Assert.fail("Lỗi không chỉnh được status cho customer request");
        }
    }

    @Test
    // Update giá trị cho request
    // Input: id = 54, CustomerRequest(type_house = 0)
    // Expect Output: CustomerRequest(type_house = 0)
    public void updateHouseHold() {
        try {
            CustomerRegister customerRegister =  customerRegisterService.findById(54);
            customerRegister.setTypeHouse("0");
            customerRegisterService.updateHouseHold(customerRegister);
            customerRegister =  customerRegisterService.findById(54);
            assertEquals(customerRegister.getTypeHouse(), "0");
        } catch (Exception e) {
            Assert.fail("Lỗi không cập nhật được giá trị cho customer request");
        }
    }
}
