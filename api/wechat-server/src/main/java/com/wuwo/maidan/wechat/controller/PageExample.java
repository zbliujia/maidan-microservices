package com.wuwo.maidan.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/wechat")
public class PageExample {

	@RequestMapping(method = RequestMethod.GET, path = "/page")
    String home(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model, HttpServletRequest req, HttpServletResponse res) {
		model.addAttribute("name", name);
		return "page";
    }

}
