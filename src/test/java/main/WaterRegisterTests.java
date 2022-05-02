package main;

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

    //test kieu du lieu duoc the hien dung dinh dang
    @Test
    public void testTypeNameHouse() {
        assertEquals("nameHouse", customerRegister.getNameHouse());
    }
    @Test
    public void testTypeAddress() {
        assertEquals("address", customerRegister.getAddress());
    }
    @Test
    public void testTypeDate() {
        assertEquals(date, customerRegister.getDataBirth());
    }
    @Test
    public void testTypeEmail() {
        assertEquals("email", customerRegister.getEmail());
    }
    @Test
    public void testTypePhone() {
        assertEquals("0932200011", customerRegister.getPhone());
    }
    @Test
    public void testIdSupplier() {
        assertEquals(1, customerRegister.getIdSupplier());
    }
    @Test
    public void testTypeHouse() {
        assertEquals("typeHouse", customerRegister.getTypeHouse());
    }
    @Test
    public void testTypeStatus() {
        assertEquals(1, customerRegister.getStatus());
    }

    //test null cho các field
    @Test
    public void testNullNameHouse() {
        try {
            assertNotNull(customerRegister.getNameHouse());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullAddress() {
        try {
            assertNotNull(customerRegister.getAddress());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullDate() {
        try {
            assertNotNull(customerRegister.getDataBirth());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullEmail() {
        try {
            assertNotNull(customerRegister.getEmail());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullPhone() {
        try {
            assertNotNull(customerRegister.getPhone());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullIdSupplier() {
        try {
            assertNotNull(customerRegister.getIdSupplier());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullTypeHouse() {
        try {
            assertNotNull(customerRegister.getTypeHouse());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
    @Test
    public void testNullStatus() {
        try {
            assertNotNull(customerRegister.getStatus());
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    @After
    public void customerRegisterSuccess(){
        try {
            String responseCode = "00";
            if(responseCode.equals("00")){
                CustomerRegister customer =customerRegisterService.findById(1);
                assertNotNull(customer);
                assertEquals(customer ,customerRegister);
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
