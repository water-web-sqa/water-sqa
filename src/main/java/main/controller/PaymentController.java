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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class PaymentController {
    private static final Logger logger = LogManager.getLogger(PaymentController.class);
    @Autowired
    VnpayService vnpayService;

    @Autowired
    WaterMoneyService waterMoneyService;

    @Autowired
    private ServletContext context;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private WatterService watterService;

    @GetMapping(value = URLConst.User.USER_PAY_MENT)
    @ResponseBody
    public ResponseEntity<?> vnpayment(@RequestParam("codeHouse") String codeHouse, @RequestParam("vnpOrderInfo") String vnpOrderInfo,
                                                @RequestParam("bankcode") String bankcode,
                                       HttpServletRequest httpServletRequest
                                       ) {
        try {
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(httpServletRequest)
                    .replacePath(null)
                    .build()
                    .toUriString();
            String return_url = baseUrl + context.getContextPath() + URLConst.User.VPN_RESPONSE_PAY_MENT_SERVER;
            return ResponseEntity.status(HttpStatus.OK).body(
                    vnpayService.paymentWater(codeHouse,vnpOrderInfo, bankcode, waterMoneyService.getMountOfcodeHouse(codeHouse), return_url));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("FAILED");
        }

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
        try {
            //String signValue = ConfigVnpay.hashAllFields(fields);
            HouseHold houseHold = houseHoldService.findByCodeHouse(codeHouse);
            if(responseCode.equals("00")){
                List<PaymentWaterResponse> list = waterMoneyService.listwaterMoneyResponseByHouse(codeHouse);
                String resultMsg = "";
                for(PaymentWaterResponse paymentWaterResponse: list){
                    Bill bill = new Bill();
                    bill.setIdStaff(1);
                    bill.setSumMoney(Float.valueOf(paymentWaterResponse.getSumPrice()));
                    bill.setIdWaterMoney(paymentWaterResponse.getIdWaterMoney());
                    resultMsg += paymentWaterResponse.getDateWater();
                    vnpayService.savePayMent(bill);
                }
                HttpSession session = request.getSession();
                session.setAttribute("codeHouse", codeHouse);
                session.setAttribute("nameHouse", houseHold.getNameHouse());
                session.setAttribute("address", houseHold.getAddress());
                session.setAttribute("resultMsg", resultMsg);
                return new ModelAndView("common/succsessPayment");
            }
            else{
                HttpSession session = request.getSession();
                session.setAttribute("resultMsg", "Lỗi giao dịch");
                return new ModelAndView("common/errorPayment");
            }
        } catch (Exception e) {
            return new ModelAndView("home");
        }

    }

    @GetMapping(value = URLConst.User.GET_HOUSE_HOLD)
    @ResponseBody
    public WrapperResponse<HouseHoldeWatterSuplier> getHouseHold(@RequestParam("codeHouse") String codeHouse) {
        WrapperResponse<HouseHoldeWatterSuplier> response = new WrapperResponse<>();
        try {
            HouseHold houseHold = houseHoldService.findByCodeHouse(codeHouse);
            HouseHoldeWatterSuplier houseHoldeWatterSuplier = new HouseHoldeWatterSuplier(houseHold, watterService.findById(Integer.valueOf(houseHold.getIdSupplier())));
            response.setStatus(200);
            response.setBody(houseHoldeWatterSuplier);
            response.setMsg("Success");
        } catch (Exception e) {
            response.setStatus(400);
            logger.error(e.getMessage(), e);
        }
        return response;
    }


    @GetMapping(value = URLConst.User.GET_LIST_MEMBER_WATER)
    @ResponseBody
    public HashMap<String, Object> getListMemberMayPayment(@RequestParam("codeHouse") String codeHouse) {
        HashMap<String, Object> result = new HashMap<>();
        List<PaymentWaterResponse> list = waterMoneyService.listwaterMoneyResponseByHouse(codeHouse);
        if(list.size() > 0){
            PaymentWaterResponse paymentWaterResponse = new PaymentWaterResponse();
            paymentWaterResponse.setPriceBybac("Tong");
            paymentWaterResponse.setSumPrice(Integer.parseInt(waterMoneyService.getMountOfcodeHouse(codeHouse)));
            list.add(paymentWaterResponse);
        }
        try {
            result.put("draw", 1);
            result.put("recordsTotal", list.size());
            result.put("recordsFiltered", list.size());
            result.put("data", list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }
}
