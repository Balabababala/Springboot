package com.example.demo.controller;


import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BMI;
import com.example.demo.model.entity.*;
import com.example.demo.response.ApiResponse;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@GetMapping(value = "/bmi",produces ="application/json;charset=utf-8")
		public ResponseEntity<ApiResponse<BMI>> bmi(@RequestParam(required = false) Double h,
						  @RequestParam (required = false) Double w) {
			if(h == null || w == null) {
				return ResponseEntity.badRequest().body(ApiResponse.error("身高(h)體重(w)?"));
			}
			Double bmi= w/Math.pow(h/100, 2);
			return ResponseEntity.ok(ApiResponse.success("身高(h)體重(w)?",new BMI(h, w, bmi)));
			}
//	5.同名多筆資料
//	路徑:/age?age=17&age=21&age=20
//	網址:http://localhost:8080/api/age?age=17&age=21&age=20
//	計算平均年齡
	@GetMapping(value = "/age",produces ="application/json;charset=utf-8")
		public ResponseEntity<ApiResponse<Object>> getAverage(@RequestParam(name="age" ,required=false) List<String> ages){
			if(ages==null ||ages.size()==0) {
				return ResponseEntity.badRequest().body(ApiResponse.error("請輸入年齡"));
			}
			double avg= ages.stream().mapToInt(Integer::parseInt).average().orElseGet(()->0);
			Map map = Map.of ("平均年齡",String.format("%.1f", avg));
			return ResponseEntity.ok(ApiResponse.success("計算成功", map));
	}
//	6. Lab 練習: 得到多筆 score 資料
//	路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
//	網址: http://localhost:8080/api/exam?score=80&score=100&score=50&score=70&score=30
//	請自行設計一個方法，此方法可以
//	印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數列出=?、不及格分數列出=?
	@GetMapping(value = "/exam",produces ="application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getExamScore(@RequestParam(name="score" ,required=false) List<Integer> scores){
		if(scores==null ||scores.size()==0) {
			return ResponseEntity.badRequest().body(ApiResponse.error("請輸入分數"));
		}
		
		IntSummaryStatistics stat =scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		
		Map<Boolean, List<Integer>> resultMap=scores.stream()
				.collect(Collectors.partitioningBy(score->score>=60));
		
		Map map = Map.of ("最高分",stat.getMax(),
				"最低分",stat.getMin(),
				"平均分數",stat.getAverage(),
				"總分", stat.getSum(),
				"及格分數",resultMap.get(true),
				"不及格分數",resultMap.get(false));
		return ResponseEntity.ok(ApiResponse.success("計算成功", map));
	}
	
	/*
	 * 7. 多筆參數轉 Map
	 * name 書名(String), price 價格(Double), amount 數量(Integer), pub 出刊/停刊(Boolean) @RequestParam 對應 只能給單一參數用
	 * 路徑: /book?name=Math&price=12.5&amount=10&pub=true
	 * 路徑: /book?name=English&price=10.5&amount=20&pub=false
	 * 網址: http://localhost:8080/api/book?name=Math&price=12.5&amount=10&pub=true
	 * 網址: http://localhost:8080/api/book?name=English&price=10.5&amount=20&pub=false
	 * 讓參數自動轉成 key/value 的 Map 集合
	 * */
	@GetMapping(value = "/book",produces ="application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookInfo(@RequestParam Map<String,Object> bookMap	){
		return ResponseEntity.ok(ApiResponse.success("回應成功", bookMap));
	}
	/* 8. 多筆參數轉指定 model 物件
	 * 路徑: 承上
	 * 網址: 承上
	 * */
	@GetMapping(value = "/book2",produces ="application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookInfo2(Book book){
		book.setId(1);
		return ResponseEntity.ok(ApiResponse.success("回應成功", book));
	}
	
	
	/**
	 * 9. 路徑參數
	 * 早期設計風格:
	 * 路徑: /book?id=1 得到 id = 1 的書
	 * 路徑: /book?id=3 得到 id = 3 的書
	 * 
	 * 現代設計風格(Rest):
	 * GET    /books   查詢所有書籍
	 * GET    /book/1  查詢單筆書籍
	 * POST   /book    新增書籍
	 * PUT    /book/1  修改單筆書籍
	 * DELETE /book/1  刪除單筆書籍
	 * 
	 * 路徑: /book/1 得到 id = 1 的書
	 * 路徑: /book/3 得到 id = 3 的書
	 * 網址: http://localhost:8080/api/book/1
	 * 網址: http://localhost:8080/api/book/3
	 * 
	 * @PathVariable(name = "id") Integer id
	 * 等價於
	 * @PathVariable(value = "id") Integer id
	 * 也等價於
	 * @PathVariable("id") Integer id
	 * ps: 就只是符合不同開發者的需要 !
	 * 
	 * */
	@GetMapping(value = "/book/{id}",produces ="application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookById(@PathVariable (name="id") Integer id){
		List<Book> books=List.of(
				 new Book(1,"Doraemon",12.5,20,false),
				 new Book(2,"loufushi",10.5,30,false),
				 new Book(3,"Howshuth",8.5,40,true),
				 new Book(4,"niroherdernuerr",11.5,50,true)			
				);
		Optional<Book> optBook=books.stream().filter(book -> book.getId().equals(id)).findFirst();
		if(optBook.isEmpty()) {
			return ResponseEntity.badRequest().body(null);
		}
		Book book=optBook.get();
		return ResponseEntity.ok(ApiResponse.success("回應成功", book));
	}
	/**
	 * 請利用"路徑參數"設計出可以只顯示出刊或停刊的設計風格與方法
	 * 路徑: /book/Pub/true 得到 pub=true 的書
	 * 路徑: /book/Pub/false 得到 pub=false 的書
	 * 網址: http://localhost:8080/api/book/Pub/true
	 * 網址: http://localhost:8080/api/book/Pub/false
	 * */
	@GetMapping(value = "/book/Pub/{isPub}",produces ="application/json;charset=utf-8")
	public ResponseEntity<ApiResponse<Object>> getBookByPub(@PathVariable (name="isPub") Boolean isPub){
		List<Book> books=List.of(
				 new Book(1,"Doraemon",12.5,20,false),
				 new Book(2,"loufushi",10.5,30,false),
				 new Book(3,"Howshuth",8.5,40,true),
				 new Book(4,"niroherdernuerr",11.5,50,true)			
				);
		
		List<Book> reBooks= books.stream()
								 .filter(book -> book.getPub().equals(isPub))
								 .toList();
		if(reBooks.size()==0) {
			return ResponseEntity.badRequest().body(null);
		}
		return ResponseEntity.ok(ApiResponse.success("查詢成功:"+(isPub?"出刊有":"停刊有"), reBooks));
	}
	
}
