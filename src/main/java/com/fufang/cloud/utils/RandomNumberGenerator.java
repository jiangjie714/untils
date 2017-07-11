package com.fufang.cloud.utils;

import java.util.Random;

public class RandomNumberGenerator {

	public static String randomNumber(int length) {
		StringBuilder no = new StringBuilder("");
		int num[] = new int[length];
		Random random = new Random();
		for (int i = 0; i < length-1; i++) {
			num[i] = random.nextInt(10);
		}
		if (num.length > 0) {
			for (int i = 0; i < num.length; i++) {
				no.append(num[i]);
			}
		}
		return no.toString();
	}
	
	public static void main(String[] args) {
		for (int i = 0;i<20;i++) {
			System.out.println(randomNumber(6));
		}
	}
}
