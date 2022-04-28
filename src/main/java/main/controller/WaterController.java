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
import java.util.Date;
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
		return houseHoldService.findHouseHoldWater(houseHoldBeans);
	}

	@GetMapping(value = URLConst.Water.GET_WATER_SUPPLIER)
	@ResponseBody
	public List<WaterSupplier> getListWater() {
		return watterService.getListSupplier();
	}

	@PostMapping(value = URLConst.Water.UPDATE_HOUSE_HOLD, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public WrapperResponse<Boolean> updateHouseHold(@RequestBody HouseHold houseHold) {
		return houseHoldService.updateHouseHold(houseHold);
	}

	@PostMapping(value = URLConst.Water.UPDATE_WATER_MONEY, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public WrapperResponse<Boolean> updateWaterMoney(@RequestBody WaterMoneyUpdateBeans waterMoneyUpdateBeans) {
		return waterMoneyService.updateWaterMoney(waterMoneyUpdateBeans);
	}
}
