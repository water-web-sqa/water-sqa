package main;

import main.beans.WrapperResponse;
import main.entity.CustomerRegister;
import main.service.CustomerRegisterService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaterRegisterTests {
    @Autowired
    CustomerRegisterService customerRegisterService;
    Date date = new Date(16/01/2000);
    CustomerRegister customerRegister = new CustomerRegister(1, "nameHouse", "address" ,date, "email", "0932200011", 1, "typeHouse", 1);

    /**
     * test kiểu dữ liệu được thể hiện đúng định dạng
     */

    // Kiểm tra đúng định dạng nameHouse người dùng nhập
    // Input: CustomerRegister(nameHouse: "nam nguyen")
    // Expect output: CustomerRequest(nameHouse = "nam nguyen")
    @Test
    public void testTypeNameHouse() {
        assertEquals("nameHouse", customerRegister.getNameHouse());
    }

    // Kiểm tra đúng định dạng address người dùng nhập
    // Input: CustomerRegister(address: "Hà nội")
    // Expect output: CustomerRequest(address = "Hà nội")
    @Test
    public void testTypeAddress() {
        assertEquals("address", customerRegister.getAddress());
    }

    // Kiểm tra đúng định dạng dataBirth người dùng nhập
    // Input: CustomerRegister(dataBirth: "16-01-2000")
    // Expect output: CustomerRequest(dataBirth = "16-01-2000")
    @Test
    public void testTypeDate() {
        assertEquals(date, customerRegister.getDataBirth());
    }

    // Kiểm tra đúng định dạng email người dùng nhập
    // Input: CustomerRegister(email: "namnguyen@gmail.com")
    // Expect output: CustomerRequest(email = "namnguyen@gmail.com")
    @Test
    public void testTypeEmail() {
        assertEquals("email", customerRegister.getEmail());
    }

    // Kiểm tra đúng định dạng phone người dùng nhập
    // Input: CustomerRegister(phone: "0932200011")
    // Expect output: CustomerRequest(phone = "0932200011")
    @Test
    public void testTypePhone() {
        assertEquals("0932200011", customerRegister.getPhone());
    }

    // Kiểm tra đúng định dạng IdSupplier người dùng nhập
    // Input: CustomerRegister(idSupplier: 1)
    // Expect output: CustomerRequest(idSupplier = 1)
    @Test
    public void testIdSupplier() {
        assertEquals(1, customerRegister.getIdSupplier());
    }

    // Kiểm tra đúng định dạng typeHouse người dùng nhập
    // Input: CustomerRegister(typeHouse: "typeHouse")
    // Expect output: CustomerRequest(typeHouse = "typeHouse")
    @Test
    public void testTypeHouse() {
        assertEquals("typeHouse", customerRegister.getTypeHouse());
    }

    // Kiểm tra đúng định dạng status người dùng nhập
    // Input: CustomerRegister(status: 1)
    // Expect output: CustomerRequest(status = 1)
    @Test
    public void testTypeStatus() {
        assertEquals(1, customerRegister.getStatus());
    }

    /**
     * test các trường dữ liệu khi bị null
     */

    // Kiểm tra đúng định dạng nameHouse người dùng nhập
    // Input: CustomerRegister(nameHouse: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullNameHouse() {
        try {
            assertNotNull(customerRegister.getNameHouse());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng address người dùng nhập
    // Input: CustomerRegister(address: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullAddress() {
        try {
            assertNotNull(customerRegister.getAddress());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng dataBirth người dùng nhập
    // Input: CustomerRegister(dataBirth: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullDate() {
        try {
            assertNotNull(customerRegister.getDataBirth());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng email người dùng nhập
    // Input: CustomerRegister(email: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullEmail() {
        try {
            assertNotNull(customerRegister.getEmail());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng phone người dùng nhập
    // Input: CustomerRegister(phone: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullPhone() {
        try {
            assertNotNull(customerRegister.getPhone());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng IdSupplier người dùng nhập
    // Input: CustomerRegister(idSupplier: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullIdSupplier() {
        try {
            assertNotNull(customerRegister.getIdSupplier());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng typeHouse người dùng nhập
    // Input: CustomerRegister(typeHouse: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullTypeHouse() {
        try {
            assertNotNull(customerRegister.getTypeHouse());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đúng định dạng status người dùng nhập
    // Input: CustomerRegister(status: null)
    // Expect output: fail("throw exception")
    @Test
    public void testNullStatus() {
        try {
            assertNotNull(customerRegister.getStatus());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // Kiểm tra đăng kí sử dụng nước thành công
    // Input: customerRegister(1, "nameHouse", "address" ,date, "email", "0932200011", 1, "typeHouse", 1)
    // Expect output: customerRegister(1, "nameHouse", "address" ,date, "email", "0932200011", 1, "typeHouse", 1)
    @After
    public void customerRegisterSuccess(){
        try {
            String responseCode = "00";
            if(responseCode.equals("00")){
                WrapperResponse<Boolean> isSuccess =customerRegisterService.addCustomerResigter(customerRegister);
                assertNotNull(customerRegister);
                if(!isSuccess.getBody()) {
                    fail("Service error");
                }
            }
            else{
                fail("Service error");
            }
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
}
