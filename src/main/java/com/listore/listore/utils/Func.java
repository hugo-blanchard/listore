package com.listore.listore.utils;

public class Func {
	public static int clamp(int number, int min, int max) {
		return Math.max(min, Math.min(max, number));
	}
}
