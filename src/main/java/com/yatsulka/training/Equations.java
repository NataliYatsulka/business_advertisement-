package com.yatsulka.training;

public class Equations {

	public static double resolveEquation(int a, int b, int c) {
		int d = b * b - 4 * a * c;

		if (d < 0) {
			throw new IllegalArgumentException();
		}

		double x1 = (-b + Math.sqrt(d)) / (a * 2);
		double x2 = (-b - Math.sqrt(d)) / (a * 2);
		
		return Math.min(x1, x2);
	}
}
