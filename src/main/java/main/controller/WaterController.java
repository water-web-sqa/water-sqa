package main.controller;

import main.beans.HouseHoldBeans;
import main.beans.HouseHoldWaterBeans;
import main.beans.WaterMoneyUpdateBeans;
import main.beans.WrapperResponse;
import main.entity.HouseHold;
import main.entity.WaterSupplier;
import main.service.HouseHoldService;
import main.service.WaterMoneyService;
import main.service.WatterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import main.common.CommonConst;
import main.common.URLConst;
import main.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Transactional
public class WaterController extends BaseController{
	private static final Logger logger = LogManager.getLogger(WaterController.class);

	@Autowired
	UserService userService;
	@Autowired
	HouseHoldService houseHoldService;
	@Autowired
	WatterService watterService;
	@Autowired
	WaterMoneyService waterMoneyService;

	@RequestMapping(value = URLConst.Water.WATER_HOME , method = RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("pageActive", CommonConst.COMMON_PAGE.WATER);
		session.setAttribute("nameUser", "Admin");
		return new ModelAndView("water");
	}

	@RequestMapping(value = URLConst.Water.WATER_HOME_1 , method = RequestMethod.GET)
	public ModelAndView view2(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		session.setAttribute("pageActive", CommonConst.COMMON_PAGE.WATER1);
		session.setAttribute("nameUser", "Admin");
		return new ModelAndView("water1");
	}

	@PostMapping(value = URLConst.Water.WATER_SEARCH_HOUSEHOLD, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public HashMap<String, Object> findHouseHoldWater(@RequestBody HouseHoldBeans houseHoldBeans) {
		HashMap<String, Object> result = new HashMap<>();
		try {
			List<HouseHold> houseHolds = new ArrayList<>();
			if(houseHoldBeans.getCodehouse().equals("")) {
				houseHolds = houseHoldService.findAllHousehouseByAddress(houseHoldBeans);
			} else {
				HouseHold item = houseHoldService.findByCodeHouse(houseHoldBeans.getCodehouse());
				if(item != null) {
					houseHolds.add(item);
				}
			}
			List<HouseHoldWaterBeans> list = new ArrayList<>();
			houseHolds.forEach(houseHold -> {
				list.add(new HouseHoldWaterBeans(houseHold, waterMoneyService.findWaterMoneyByHouseHold(houseHold.getCodeHouse())));
			});

			result.put("draw", 1);
			result.put("recordsTotal", list.size());
			result.put("recordsFiltered", list.size());
			result.put("data", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	@GetMapping(value = URLConst.Water.GET_WATER_SUPPLIER)
	@ResponseBody
	public List<WaterSupplier> getListWater() {
		try {
			return watterService.getListSupplier();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@PostMapping(value = URLConst.Water.UPDATE_HOUSE_HOLD, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public WrapperResponse<Boolean> updateHouseHold(@RequestBody HouseHold houseHold) {
		WrapperResponse<Boolean> response = new WrapperResponse<Boolean>();
		try {
			response.setStatus(200);
			response.setBody(true);
			houseHoldService.saveHouseHold(houseHold);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		response.setStatus(200);
		response.setBody(true);
		return null;
	}

	@PostMapping(value = URLConst.Water.UPDATE_WATER_MONEY, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public WrapperResponse<Boolean> updateWaterMoney(@RequestBody WaterMoneyUpdateBeans waterMoneyUpdateBeans) {
		WrapperResponse<Boolean> response = new WrapperResponse<>();
		try {
			waterMoneyService.updateWaterMoney(waterMoneyUpdateBeans.getNumberWater(), waterMoneyUpdateBeans.getCodeHouse());
			response.setStatus(200);
			response.setBody(true);
			response.setMsg("Success");
		} catch (Exception e) {
			response.setStatus(400);
			logger.error(e.getMessage(), e);
		}
		return response;
	}
}
