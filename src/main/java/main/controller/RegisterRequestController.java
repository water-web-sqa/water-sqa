//package main.controller;
//
//import main.beans.WrapperResponse;
//import main.common.URLConst;
//import main.entity.CustomerRegister;
//import main.entity.HouseHold;
//import main.repository.HouseHoldRepository;
//import main.service.CustomerRegisterService;
//import main.service.HouseHoldService;
//import main.service.impl.HouseHoldServiceImpl;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.transaction.Transactional;
//import java.util.HashMap;
//import java.util.List;
//
//@Controller
//@Transactional
//public class RegisterRequestController {
//    private static final Logger logger = LogManager.getLogger(WaterController.class);
//
//    @Autowired
//    CustomerRegisterService customerRegisterService;
//
//    @Autowired
//    HouseHoldService houseHoldService;
//
//    @GetMapping(value = URLConst.Water.GET_REQUEST_RESIGTER_WATER)
//    @ResponseBody
//    public HashMap<String, Object> getAllRegisterRequest() {
//        HashMap<String, Object> result = new HashMap<>();
//        try {
//            List<CustomerRegister> list = customerRegisterService.allCustomerRegister();
//            result.put("draw", 1);
//            result.put("recordsTotal", list.size());
//            result.put("recordsFiltered", list.size());
//            result.put("data", list);
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return result;
//    }
//
//    @GetMapping(value = URLConst.Water.SET_STATUS_REQUEST)
//    @ResponseBody
//    public WrapperResponse<Boolean> setStatusRequest(@RequestParam Integer type, @RequestParam Integer id) {
//        WrapperResponse<Boolean> result = new WrapperResponse<>();
//        try {
//            switch (type) {
//                case 1: {
//                    customerRegisterService.deleteCustomerRegister(id);
//                    break;
//                }
//                case 0: {
//                    customerRegisterService.updateStatus(1, id);
//                    break;
//                }
//                case 2: {
//                    CustomerRegister customerRegister = customerRegisterService.findById(id);
//                    String lastId = houseHoldService.lastId();
//                    HouseHold newHouseHold = new HouseHold("MDB" + Integer.parseInt(lastId.substring(3, lastId.length())),
//                            customerRegister.getNameHouse(), customerRegister.getAddress(), customerRegister.getDataBirth(),
//                            customerRegister.getIdSupplier(), customerRegister.getTypeHouse());
//                    houseHoldService.saveHouseHold(newHouseHold);
//                    customerRegisterService.deleteCustomerRegister(id);
//                    break;
//                }
//            }
//            result.setStatus(200);
//            result.setMsg("Chỉnh sửa thành công");
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//            result.setStatus(400);
//            result.setMsg("Yêu cầu thất bại");
//        }
//        return result;
//    }
//
//    @PostMapping(value = URLConst.Water.UPDATE_CUSTOMER_RESIGTER, consumes = "application/json", produces = "application/json")
//    @ResponseBody
//    public WrapperResponse<Boolean> updateHouseHold(@RequestBody CustomerRegister customerRegister) {
//        WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
//        try {
//            response.setStatus(200);
//            response.setBody(true);
//            customerRegisterService.save(customerRegister);
//            return response;
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        response.setStatus(200);
//        response.setBody(true);
//        return null;
//    }
//}
