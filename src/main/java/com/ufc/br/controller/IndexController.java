package com.ufc.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufc.br.service.PratoService;


@Controller
public class IndexController {

	@Autowired
	PratoService pratoService;
	
	@RequestMapping("/")
	public String showHomePage() {
		return "index";
	}
}
