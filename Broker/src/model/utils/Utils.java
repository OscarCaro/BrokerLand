package model.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

	public static int randomNum(int upperBound) {
		return (int) (Math.random() * upperBound);			//Number from 0 to upperBound - 1
	}

	public static void minusWall(){
		System.out.println("---------------------------------------------------------------------------------------");
	}
	public static void equalsWall(){
		System.out.println("=======================================================================================");
	}
	public static String generateName() {
		int size = 16;
		String[] names = {
				"Liam", "Noah", "Mason", "Logan", "James", "Ethan",
				"Lucas", "Jacob", "Michael", "Matthew", "Alexander", "William",
				"Daniel", "Oliver", "Sebastian", "David", "Thomas", "Oscar"
		};
		return names[randomNum(size)];
 	}
	
	public static String generateSurname() {
		int size = 8;
		String[] surnames = {
				"Smith", "Johnson", "Williams", "Jones",
				"Miller", "Taylor", "Martin", "Anderson", "Moore", "Caro"
		};
		return surnames[randomNum(size)];
	}

	public static char randomConsonant() {

		char aux =  randomLetter();
		while (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' ||aux == 'U'){
			aux =  randomLetter();
		}
		return aux;
	}

	public static char randomVowel() {
		char aux =  randomLetter();
		while (aux != 'A' && aux != 'O' && aux != 'I' && aux != 'E' && aux != 'U'){
			aux =  randomLetter();
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
		return (char) (65 + randomNum(25));
	}
	
    public static String assetNameGen() {
        int type = Utils.randomNum(6);
        StringBuilder name = new StringBuilder("" + Utils.randomLetter()); /*first an Upper case letter.*/
        if (type == 0) {
            int length = Utils.randomNum(2) + 1;
            char aux;
            aux = Utils.randomLetter();
            if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                name.append(Character.toLowerCase(aux));
                name.append(Character.toLowerCase(Utils.randomConsonant()));
            } else {
                name.append(Character.toLowerCase(Utils.randomVowel()));
                name.append(Character.toLowerCase(aux));
            }
            name.append(" Electricity");
        } else if (type == 1) { //Incorporated companies
            int length = Utils.randomNum(3) + 2;
            for (int i = 0; i < length; i++) {
                name.append(Utils.randomLetter());
            }
            name.append(" Inc");
        } else if (type == 2) { //Limited Liability Company asset
            int length = Utils.randomNum(3) + 1;
            for (int i = 0; i < length; i++) {
                name.append(Utils.randomLetter());
            }
            name.append(" LLC");
        } else if (type == 3) { //Crypto Assets
            int length = Utils.randomNum(2);
            char aux;
            for (int i = 0; i < length; i++) {
                aux = Utils.randomLetter();
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                }
            }
            if (name.toString().length() == 1) {
                StringBuilder auxS = new StringBuilder("The ");
                auxS.append(name);
                name = auxS;
            }
            name.append(" Coin");
        } else if (type == 4) {//Cooperatives
            int oneMore, length = Utils.randomNum(2) + 1;
            char aux;
            aux = Utils.randomLetter();
            for (int i = 0; i < length; i++) {
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                    oneMore = Utils.randomNum(2);
                    if (oneMore == 0) {
                        name.append(Character.toLowerCase(Utils.randomConsonant()));
                    }
                }
            }
            name.append(" Co");
        } else { //random assets or resources
            int oneMore, length = Utils.randomNum(3) + 1;
            char aux;
            for (int i = 0; i < length; i++) {
                aux = Utils.randomLetter();
                if (aux == 'A' || aux == 'O' || aux == 'I' || aux == 'E' || aux == 'U') {
                    name.append(Character.toLowerCase(aux));
                    name.append(Character.toLowerCase(Utils.randomConsonant()));
                } else {
                    name.append(Character.toLowerCase(Utils.randomVowel()));
                    name.append(Character.toLowerCase(aux));
                    oneMore = Utils.randomNum(5);
                    if (oneMore == 0) {
                        name.append(Character.toLowerCase(Utils.randomConsonant()));
                    }
                }
            }
        }
        return name.toString();
    }
}
