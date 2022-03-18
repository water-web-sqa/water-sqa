package main.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;

@Controller
@Transactional
public class RegisterRequestController {
    private static final Logger logger = LogManager.getLogger(WaterController.class);
}
