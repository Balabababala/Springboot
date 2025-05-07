package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BMI {
	private Double height;
	private Double weight;
	private Double bmi;

}
