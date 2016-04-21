package com.machinelearning;

import java.util.Random;

public class Utility {
	
	private static long ID = 0;
	
	public static Random random = new Random();
	
	public static void log(String string) {
		//TODO implement logging
		System.out.println(string);
	}
	
	public static long getID() {
		long id = ID;
		ID++;
		return id;
	}
	
	public static int sumNumbers(int x) {
		int result = 0;
		for(int i=0; i<x; i++) {
			result += i;
		}
		return result;
	}

}
