package main.controller;


import main.beans.HouseHoldBeans;
import main.beans.HouseHoldWaterBeans;
import main.common.URLConst;
import main.config.vnpay.ConfigVnpay;
import main.entity.HouseHold;
import main.service.VnpayService;
import main.service.WaterMoneyService;
import main.service.WatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    VnpayService vnpayService;

    @Autowired
    WaterMoneyService waterMoneyService;

    @GetMapping(value = URLConst.User.USER_PAY_MENT)
    @ResponseBody
    public ResponseEntity<?> vnpayment(@RequestParam("codeHouse") String codeHouse, @RequestParam("vnpOrderInfo") String vnpOrderInfo,
                                                @RequestParam("bankcode") String bankcode) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(vnpayService.paymentWater(codeHouse,vnpOrderInfo, bankcode, waterMoneyService.getMountOfcodeHouse(codeHouse)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("FAILED");
        }

    }

    @GetMapping(value = URLConst.User.VPN_RESPONSE_PAY_MENT)
    @ResponseBody
    public ResponseEntity<?> responseVnPay(
            @RequestParam(value = "vnp_Amount", required = false) String amound,
            @RequestParam("vnp_BankCode") String bankCode,
            @RequestParam("vnp_BankTranNo") String bankTranNo,
            @RequestParam("vnp_CardType") String cardType,
            @RequestParam("vnp_OrderInfo") String orderInfo,
            @RequestParam("vnp_PayDate") String vnpPayDate,
            @RequestParam("vnp_ResponseCode") String responseCode,
            @RequestParam("vnp_TmnCode") String tmnCode,
            @RequestParam("vnp_TransactionNo") String transactionNo,
            @RequestParam("vnp_TxnRef") String vnp_TxnRef,
            @RequestParam("vnp_SecureHashType") String vnpSecureHashType,
            @RequestParam("vnp_SecureHash") String vnp_SecureHash
            ) {
        try {
            //String signValue = ConfigVnpay.hashAllFields(fields);
            return ResponseEntity.status(HttpStatus.OK).body("Ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("FAILED");
        }

    }

}
