package main.service;

import main.beans.HouseHoldeWatterSuplier;
import main.beans.WrapperResponse;
import main.entity.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface VnpayService {
    String paymentWater(String codeHouse,String vnpOrderInfo, String bankcode, String mountBill, String return_url);
    void savePayMent(Bill bill);

    ResponseEntity<?> vnpayment(String codeHouse, String vnpOrderInfo, String bankcode,
                                       HttpServletRequest httpServletRequest, ServletContext context);
    ModelAndView responseVnPay(String codeHouse, String responseCode, HttpServletRequest request);
    WrapperResponse<HouseHoldeWatterSuplier> getHouseHold(String codeHouse);
    HashMap<String, Object> getListMemberMayPayment(String codeHouse);
}
