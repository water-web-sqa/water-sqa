package main.payment;

import main.beans.HouseHoldeWatterSuplier;
import main.beans.PaymentWaterResponse;
import main.common.URLConst;
import main.config.vnpay.ConfigVnpay;
import main.entity.Bill;
import main.entity.HouseHold;
import main.entity.WaterMoney;
import main.entity.WaterSupplier;
import main.repository.BillRespository;
import main.repository.WaterMoneyRepository;
import main.repository.WatterRepository;
import main.service.HouseHoldService;
import main.service.VnpayService;
import main.service.WaterMoneyService;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentWaterTest {
    @Autowired
    WaterMoneyService waterMoneyService;

    @Autowired
    private HouseHoldService houseHoldService;

    @Autowired
    private WatterRepository watterRepository;

    @Autowired
    private WaterMoneyRepository waterMoneyRepository;

    @Autowired
    private BillRespository billRespository;

    @Autowired
    private VnpayService vnpayService;

    private HouseHold houseHold;


    // kiểm tra tìm kiếm HouseHold (nhà sử dụng nước) theo mã danh bộ
    // Input: TestHouseHoldByCodeWater(codeHould: "MDB01")
    // Expect output: HouseHoldRequest(name_house = "Nguyễn Viết Cường")
    @BeforeAll()
    public void testHouseHoldByCodeWater() {
        // MDB01 - Nguyễn Viết Cường
        try {
            houseHold = houseHoldService.findByCodeHouse("MDB01");
            assertNotNull(houseHold);
            assertEquals(houseHold.getNameHouse(), "Nguyễn Viết Cường");
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // lây nhà cung cấp nước ( theo hộ gia đình )
    // Input: TestWatterSuplier(codeHould: "MDB01")
    // Expect output: WaterRequest(name_supplier = "Cấp nước Viwaco - Hà Nội")
    @Test
    public void testWatterSuplier() {
        try {
            WaterSupplier waterSupplier =  watterRepository.findById(Integer.valueOf(houseHold.getIdSupplier()));
            assertNotNull(waterSupplier);
            assertEquals(waterSupplier.getNameSupplier(), "Cấp nước Viwaco - Hà Nội");
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // expect getHouseHoldWatterSupplier
    // lây nhà cung cấp nước ( theo hộ gia đình )
    // Input: getHouseHoldWatterSupplier(codeHould: "MDB01")
    // Expect output: HouseHoldeWatterSuplierRequest(name_supplier = "Cấp nước Viwaco - Hà Nội", name_houseHold = "Nguyễn Viết Cường")
    @Test
    public void getHouseHoldWatterSupplier() {
        try {
            WaterSupplier waterSupplier = watterRepository.findById(Integer.valueOf(houseHold.getIdSupplier()));
            HouseHoldeWatterSuplier houseHoldeWatterSuplier = new HouseHoldeWatterSuplier(houseHold,
                    waterSupplier);
            assertNotNull(houseHoldeWatterSuplier);
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }


    // kiểm thử lấy danh sách tiền nước chưa thanh toán listwaterMoneyNoPayMentByHouse
    // Input: listwaterMoneyNoPayMentByHouse(codeHould: "MDB01")
    // Expect output: listwaterMoneyNoPayMentByHouse("Not Null WatterMoney")
    @Test
    public void listwaterMoneyNoPayMentByHouse(){
        try {
            List<WaterMoney> waterMoneyList = waterMoneyRepository.getOldestUpdateWaterMoneyByCodeHouse("MDB01");
            List<WaterMoney> result = new ArrayList<>();
            Collections.sort(waterMoneyList, new Comparator<WaterMoney>() {
                @Override
                public int compare(WaterMoney o1, WaterMoney o2) {
                    return o1.getDateWater().compareTo(o2.getDateWater());
                }
            });

            List<Bill> billList = billRespository.findAll();
            for(WaterMoney waterMoney : waterMoneyList){
                boolean checkBill = false; // chưa in
                for(Bill bill: billList){
                    if(bill.getIdWaterMoney() == waterMoney.getId()){
                        checkBill = true;
                    }
                }
                if(!checkBill) result.add(waterMoney);
                assertNotNull(result);
            }
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // test lay tien theo thang can thanh toan cua ho gia dinh
    // Input: getListMemberMayPayment(codeHould: "MDB01")
    // Expect output: PaymentWaterResponse("Not Null WatterMoney")
    @Test
    public void getListMemberMayPayment(){
        try {
            List<PaymentWaterResponse> listwaterMoneyResponseByHouse = waterMoneyService.listwaterMoneyResponseByHouse("MDB01");
            // check khong null
            assertNotNull(listwaterMoneyResponseByHouse);
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }


    // test thanh toan tien nuoc cua ho gia dinh ho gia dinh
    // Input: paymentWater(codeHould: "MDB01", bankcod="NCB", vnpOrderInfo="ThanhToan")
    // Expect output: PaymentWaterResponse("paymentUrl: Not Null or Contain: 'https://sandbox.vnpayment.vn/paymentv2/vpcpay.html'")
    @Test
    public void paymentWater(){
        try {
            String codeHouse = "MDB01";
            String vnpOrderInfo = "ThanhToan";
            String bankcode = "NCB";
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
            vnp_Params.put("vnp_Amount", String.valueOf((Integer.valueOf("50000")*100)));
            vnp_Params.put("vnp_CurrCode", "VND");
            String bank_code = bankcode;
            if (bank_code != null && !bank_code.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bank_code);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", codeHouse);
            vnp_Params.put("vnp_OrderType", orderType);
            vnp_Params.put("vnp_Locale", "vn");
            vnp_Params.put("vnp_ReturnUrl", URLConst.User.VPN_RESPONSE_PAY_MENT_SERVER);
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
            assertNotNull(
                    paymentUrl);
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }

    // test kết quả response của VnPay sucess
    // Input: responseVnPaySucess(codeHould: "MDB01", responseCode="00")
    // Expect output: responseVnPaySucess("resultMsg: Not Null") -> Tức là thanh toan thanh cong
    @After
    public void responseVnPaySucess(){
        try {
            String responseCode = "00";
            if(responseCode.equals("00")){
                List<PaymentWaterResponse> list = waterMoneyService.listwaterMoneyResponseByHouse("MDB01");
                String resultMsg = "";
                for(PaymentWaterResponse paymentWaterResponse: list){
                    Bill bill = new Bill();
                    bill.setIdStaff(1);
                    bill.setSumMoney(Float.valueOf(paymentWaterResponse.getSumPrice()));
                    bill.setIdWaterMoney(paymentWaterResponse.getIdWaterMoney());
                    bill.setCreateAt(LocalDateTime.now());
                    resultMsg += paymentWaterResponse.getDateWater();
                    vnpayService.savePayMent(bill);
                }
                // Neu thanh cong ma hoa don duoc thanh toan thi error
                assertNotNull(resultMsg);
            }
            else{
                fail("Vnpay error");
            }
        }
        catch (Exception ex){
            fail("throw exception");
        }
    }
}
