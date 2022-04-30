package main.controller;


import main.beans.*;
import main.common.CommonConst;
import main.common.URLConst;
import main.config.vnpay.ConfigVnpay;
import main.entity.*;
import main.service.HouseHoldService;
import main.service.VnpayService;
import main.service.WaterMoneyService;
import main.service.WatterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class PaymentController {
    private static final Logger logger = LogManager.getLogger(PaymentController.class);
    @Autowired
    VnpayService vnpayService;

    @Autowired
    private ServletContext context;

    @GetMapping(value = URLConst.User.USER_PAY_MENT)
    @ResponseBody
    public ResponseEntity<?> vnpayment(@RequestParam("codeHouse") String codeHouse, @RequestParam("vnpOrderInfo") String vnpOrderInfo,
                                                @RequestParam("bankcode") String bankcode, HttpServletRequest httpServletRequest) {
        return vnpayService.vnpayment(codeHouse, vnpOrderInfo, bankcode, httpServletRequest, context);
    }

    @GetMapping(value = URLConst.User.VPN_RESPONSE_PAY_MENT_SERVER)
    @ResponseBody
    public ModelAndView responseVnPay(
            @RequestParam(value = "vnp_Amount", required = false) String vnpAmount,
            @RequestParam(value = "vnp_BankCode", required = false) String bankCode,
            @RequestParam(value = "vnp_BankTranNo", required = false) String bankTranNo,
            @RequestParam(value = "vnp_CardType", required = true) String cardType,
            @RequestParam(value = "vnp_OrderInfo", required = false) String codeHouse,
            @RequestParam(value = "vnp_PayDate", required = false) String vnpPayDate,
            @RequestParam(value = "vnp_ResponseCode", required = false) String responseCode,
            @RequestParam(value = "vnp_TmnCode", required = false) String tmnCode,
            @RequestParam(value = "vnp_TransactionNo", required = false) String transactionNo,
            @RequestParam(value = "vnp_TxnRef", required = false) String vnp_TxnRef,
            @RequestParam(value = "vnp_TransactionStatus", required = false) String vnpTransactionStatus,
            @RequestParam(value = "vnp_SecureHashType", required = false) String vnpSecureHashType,
            @RequestParam(value = "vnp_SecureHash", required = false) String vnp_SecureHash,
            HttpServletRequest request
            ) {
        return vnpayService.responseVnPay(codeHouse, responseCode, request);
    }

    @GetMapping(value = URLConst.User.GET_HOUSE_HOLD)
    @ResponseBody
    public WrapperResponse<HouseHoldeWatterSuplier> getHouseHold(@RequestParam("codeHouse") String codeHouse) {
        return vnpayService.getHouseHold(codeHouse);
    }


    @GetMapping(value = URLConst.User.GET_LIST_MEMBER_WATER)
    @ResponseBody
    public HashMap<String, Object> getListMemberMayPayment(@RequestParam("codeHouse") String codeHouse) {
        return vnpayService.getListMemberMayPayment(codeHouse);
    }
}
