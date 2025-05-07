package com.example.demo.controller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
	
		@GetMapping("/hello")
		@ResponseBody
		public String hello() {
			return "hello"+ new Date();
		}
		
		@GetMapping("/welcome")
		public String welcome(Model model) {
			model.addAttribute("name","阿");
			model.addAttribute("now",new Date());
			return "welcome";//welcome.jsp 
		}

		
		
}
