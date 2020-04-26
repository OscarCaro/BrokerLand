package model.life;

public class MentalHealth {
	
	int health;
	
	public MentalHealth(int m) {
		this.health = m;
	}
	
	public boolean insane() {
		return this.health <= 0;
	}
	@Override
	public String toString() {
		if (this.health>80) {
			return "You are in a stoic state of mind.";
		}
		else if (this.health>60) {
			return "You are feeling mindful.";
		}
		else if (this.health>40) {
			return "You are feeling numb.";
		}
		else if (this.health>20) {
			return "You are mentally dissociating.";
		}
		else if (this.health > 0) {
			return "You are feeling very pessimistic and considering suicide.";
		}
		else return "lorem ipsum";
	}
	public void add(int m) {
		this.health += m;
		if (this.health>100) {
			this.health = 100;
		}
	}
}
