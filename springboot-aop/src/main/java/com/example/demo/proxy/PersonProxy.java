package com.example.demo.proxy;

//靜態代理
//用來執行 被代理者的 "業務" 邏輯與實現 "公用" 邏輯
public class PersonProxy implements Person{

	//代理者
	private Person person;
	
	public PersonProxy(Person person) {
		this.person=person;
	}

	@Override
	public void work() {
		//實現公用邏輯
		System.out.println("出門前帶套");
		System.out.println("量體溫");
		//調用業務邏輯
		try {
			person.work();
		} catch (Exception e) {
			System.out.println("做事發生例外" + e);
		} finally {
			//實現公用邏輯
			System.out.println("回家後拔套");
		}	
	}

}
