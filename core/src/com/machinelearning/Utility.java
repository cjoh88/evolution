package com.machinelearning;

public class Utility {
	
	private static long ID = 0;
	
	public static void log(String string) {
		//TODO implement logging
		System.out.println(string);
	}
	
	public static long getID() {
		long id = ID;
		ID++;
		return id;
	}

}
