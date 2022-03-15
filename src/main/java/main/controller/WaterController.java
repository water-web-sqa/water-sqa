package main.controller;

import main.beans.AddressBeans;
import main.beans.WrapperResponse;
import main.entity.HouseHold;
import main.service.HouseHoldService;
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
import javax.xml.ws.Response;
import java.util.List;

@Controller
@Transactional
public class WaterController extends BaseController{
	private static final Logger logger = LogManager.getLogger(WaterController.class);

	@Autowired
	UserService userService;
	@Autowired
	HouseHoldService houseHoldService;

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
	public WrapperResponse<List<HouseHold>> updatePerson(@RequestBody AddressBeans addressBeans) {
		WrapperResponse<List<HouseHold>> response = new WrapperResponse<>();
		try {
			String address = addressBeans.getWard() + ", " + addressBeans.getDistrinct() + ", " + addressBeans.getCity();
			List<HouseHold> list = houseHoldService.findAllHousehouseByAddress(address);
			response.setBody(list);
			response.setMsg("Success");
			response.setStatus(200);
		} catch (Exception e) {
			response.setMsg("Error");
			response.setStatus(400);
		}
		return response;
	}
}
