package com.example.demo.proxy;

public class CalcProxy implements Calc {
	
	private Calc calc;
	
	public CalcProxy(Calc calc) {
		this.calc = calc;
	}
	
	@Override
	public Integer add(Integer x, Integer y) {
		// 前置通知: 驗證 x, y 不可以是 null
		if(x == null || y == null) {
			System.out.println("x, y 請放入數字");
			return null;
		}
		// 調用業務邏輯
		Integer result = calc.add(x, y);
		
		return result;
	}

	@Override
	public Integer div(Integer x, Integer y) {
		// 前置通知: 驗證 x, y 不可以是 null
		Integer result =null;
		if(x == null || y == null) {
			System.out.println("x, y 請放入數字");
			return null;
		}

		try {
			result = calc.div(x, y);
			return result;
		} catch (Exception e) {
			System.out.println("y 不可為0" + e.getMessage());
			return result;
		}
		
	}

}