package model.players;

import model.utils.Utils;

public enum Sex {
    MALE, FEMALE;

    public static Sex randomSex() {
        if (Utils.randomNum(10) < 5) {
            return MALE;
        }
        return FEMALE;
    }
    public static String objectPronoun(Sex sex){
        if (sex.equals(MALE)){
            return "his";
        }
        return "her";
    }
    public static String subjectPronoun(Sex sex, boolean caps){
        if(caps) {
            if (sex.equals(MALE)) {
                return "He";
            }
            return "She";
        }
        if (sex.equals(MALE)) {
            return "he";
        }
        return "she";
    }
}
