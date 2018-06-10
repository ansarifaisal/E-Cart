package com.fsl.ecart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping(value = "/")
	public ModelAndView displayHome() {
		ModelAndView modelAndView = new ModelAndView("page");
		modelAndView.addObject("message", "this is home page.");
		modelAndView.addObject("ifUserClickedHome", true);
		return modelAndView;
	}

}
