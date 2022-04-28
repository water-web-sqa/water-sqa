package main.service;

import main.beans.*;
import main.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public interface UserService {
	User getUserInfoByName(String username);

	HashMap<String, Object> findHouseHoldWater(HouseHoldBeans houseHoldBeans);
	WrapperResponse<CustomerSearchBillResponse> findBillByUser(CustomerSearchBeans customerSearchBeans);
}
