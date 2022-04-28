package main.service.impl;

import main.beans.HouseHoldeWatterSuplier;
import main.beans.PaymentWaterResponse;
import main.beans.WrapperResponse;
import main.common.StringConst;
import main.common.URLConst;
import main.config.vnpay.ConfigVnpay;
import main.entity.Bill;
import main.entity.HouseHold;
import main.entity.User;
import main.repository.BillRespository;
import main.repository.HouseHoldRepository;
import main.repository.WatterRepository;
import main.repository.entityManager.UserEntityManager;
import main.service.VnpayService;
import main.service.WaterMoneyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VnpayServiceImpl implements VnpayService {
    private static final Logger logger = LogManager.getLogger(VnpayServiceImpl.class);

    @Autowired
    HouseHoldRepository houseHoldRepository;

    @Autowired
    BillRespository billRespository;

    @Autowired
    WaterMoneyService waterMoneyService;

    @Autowired
    WatterRepository watterRepository;

    @Autowired
    UserEntityManager userEntityManager;

    @Override
    public String paymentWater(String codeHouse, String vnpOrderInfo, String bankcode,  String mountBill, String return_url) {
        try {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_OrderInfo = vnpOrderInfo;
            String orderType = "other";
            String vnp_TxnRef = ConfigVnpay.getRandomNumber(8);
            String vnp_IpAddr = ConfigVnpay.ip_config;
            String vnp_TmnCode = ConfigVnpay.vnp_TmnCode;

            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf((Integer.valueOf(mountBill)*100)));
            vnp_Params.put("vnp_CurrCode", "VND");
            String bank_code = bankcode;
            if (bank_code != null && !bank_code.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bank_code);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", codeHouse);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", return_url);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());

            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());
            //Add Params of 2.0.1 Version
            vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = ConfigVnpay.hmacSHA512(ConfigVnpay.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = ConfigVnpay.vnp_PayUrl + "?" + queryUrl;
            return paymentUrl;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void savePayMent(Bill bill) {
        try {
            billRespository.saveAndFlush(bill);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResponseEntity<?> vnpayment(String codeHouse, String vnpOrderInfo, String bankcode
            , HttpServletRequest httpServletRequest, ServletContext context) {
        try {
            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(httpServletRequest)
                    .replacePath(null)
                    .build()
                    .toUriString();
            String return_url = baseUrl + context.getContextPath() + URLConst.User.VPN_RESPONSE_PAY_MENT_SERVER;
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.paymentWater(codeHouse,vnpOrderInfo, bankcode, waterMoneyService.getMountOfcodeHouse(codeHouse), return_url));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("FAILED");
        }
    }

    @Override
    public ModelAndView responseVnPay(String codeHouse, String responseCode, HttpServletRequest request) {
        try {
            //String signValue = ConfigVnpay.hashAllFields(fields);
            HouseHold houseHold = houseHoldRepository.findByCodeHouse(codeHouse);
            if(responseCode.equals("00")){
                List<PaymentWaterResponse> list = waterMoneyService.listwaterMoneyResponseByHouse(codeHouse);
                String resultMsg = "";
                for(PaymentWaterResponse paymentWaterResponse: list){
                    Bill bill = new Bill();
                    bill.setIdStaff(1);
                    bill.setSumMoney(Float.valueOf(paymentWaterResponse.getSumPrice()));
                    bill.setIdWaterMoney(paymentWaterResponse.getIdWaterMoney());
                    bill.setCreateAt(LocalDateTime.now());
                    resultMsg += paymentWaterResponse.getDateWater();
                    savePayMent(bill);
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

    @Override
    public WrapperResponse<HouseHoldeWatterSuplier> getHouseHold(String codeHouse) {
        WrapperResponse<HouseHoldeWatterSuplier> response = new WrapperResponse<>();
        try {
            HouseHold houseHold = houseHoldRepository.findByCodeHouse(codeHouse);
            HouseHoldeWatterSuplier houseHoldeWatterSuplier = new HouseHoldeWatterSuplier(houseHold,
                    watterRepository.findById(Integer.valueOf(houseHold.getIdSupplier())));
            response.setStatus(200);
            response.setBody(houseHoldeWatterSuplier);
            response.setMsg("Success");
        } catch (Exception e) {
            response.setStatus(400);
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @Override
    public HashMap<String, Object> getListMemberMayPayment(String codeHouse) {
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
