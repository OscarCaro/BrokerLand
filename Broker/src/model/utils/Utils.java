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

	public static char randomConsonant() {

		char aux =  (char) ((int) (Math.random() * 25 + 65));
		while (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' ||aux == 'U'){
			aux =  (char) ((int) (Math.random() * 25 + 65));
		}
		return aux;
	}

	public static char randomVowel() {
		char aux =  (char) ((int) (Math.random() * 25 + 65));
		while (aux != 'A' && aux != 'O' && aux != 'I' && aux != 'E' && aux != 'U'){
			aux =  (char) ((int) (Math.random() * 25 + 65));
		}
		return aux;
	}

	public static String spaces(int n) {
		StringBuilder aux= new StringBuilder();
		for (int i=0;i<n;i++ ){
			aux.append(' ');
		}
		return aux.toString();
	}

	public static char randomLetter() {
		return (char) ((int) (Math.random() * 25 + 65));
	}
}
