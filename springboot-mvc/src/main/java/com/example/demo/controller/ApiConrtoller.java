package com.example.demo.controller;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;




@RestController //免@ResponseBody ,用了 jsp渲染就不能用 
@RequestMapping("/api")


public class ApiConrtoller {
//	1.首頁
//	路徑 /api/home
//	路徑 /api/
//	網址 http://localhost:8080/welcome/api/home
//	網址 http://localhost:8080/welcome/api/
	@GetMapping(value={"/home","/"})
	public String home() {
		return "我是誰";
	}
//	2.?帶參數
//	路徑:/greet?name=John&age=18
//	路徑:/greet?name=mart
//	網址:http://localhost:8080/api/greet?name=John&age=18
//	結果:	hi John ,18(成年)
//	網址:http://localhost:8080/api/greet?name=mary
//  結果:	hi mary ,0(未成年)
	@GetMapping("/greet")
	public String greet(@RequestParam (value="name" ,required=true) String username,
						@RequestParam (value="age" ,required=false,defaultValue = "0") Integer userage) {
		return String.format("Hi %s %d (%s)",
				username ,userage , userage>=18? "成年" : "未成年");
	}
//	3. 2的精簡寫法
	@GetMapping("/greet2")
	public String greet2(@RequestParam String name,
						@RequestParam (defaultValue = "0")Integer age) {
		return String.format("Hi %s %d (%s)",
				name , age , age>=18? "成年" : "未成年");
	}
//	4.Lab 練習
//	網址:http://localhost:8080/api/bmi?h=170&w=60
//	結果:bmi=20.76
	@GetMapping("/bmi")
	public String bmi(@RequestParam(defaultValue = "170") Integer h,
					  @RequestParam (defaultValue = "60") Integer w) {
		double result= w/Math.pow(h/100.0, 2);
		return String.format("Bmi = %05.2f ",
				result);
	}

}
