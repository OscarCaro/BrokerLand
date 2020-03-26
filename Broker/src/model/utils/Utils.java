package model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	public static int randomNum(int upperBound) {
		return (int) (Math.random() * upperBound);			//Number from 0 to upperBound - 1
	}
	
	public static String generateName() {
		int size = 16;
		String[] names = {
				"Liam", "Noah", "Mason", "Logan ", "James", "Ethan",
				"Lucas", "Jacob", "Michael", "Matthew", "Alexander", "William",
				"Daniel", "Oliver", "Sebastian", "David" 
		};
		return names[randomNum(size)];
 	}
	
	public static String generateSurname() {
		int size = 8;
		String[] surnames = {
				"Smith", "Johnson", "Williams", "Jones",
				"Miller", "Taylor", "Martin", "Anderson"
		};
		return surnames[randomNum(size)];
	}
}
