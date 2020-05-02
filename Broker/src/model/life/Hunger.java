package model.life;


public class Hunger {

    int hunger;

    public Hunger(int h) {
        this.hunger = h;
    }

    public boolean starved() {
        return this.hunger <= 0;
    }

    @Override
    public String toString() {
        if (this.hunger > 90) {
            return "You feel full.";
        } else if (this.hunger > 70) {
            return "You are starting to feel peckish.";
        } else if (this.hunger > 50) {
            return "You are slightly hungry.";
        } else if (this.hunger > 30) {
            return "Your stomach hurts from the hunger.";
        } else if (this.hunger > 0) {
            return "You are starving.";
        } else return "lorem ipsum";
    }

    public void add(int m) {
        this.hunger += m;
        if (this.hunger > 100) {
            this.hunger = 100;
        }
    }
}
