
public class Asset {

	int price;
	public String name;
	public boolean owned;
	Asset() {
		price = (int) (Math.random() * (1000 + (main.turn * 100))) + 37;
		name = AssetStringGen();
		owned = false;
	}
	
	public String toString () {
		return "Name: " + this.name + ". Price " + this.price;
	}



	
	
private String  AssetStringGen() {
	String name ="";
	int length = (int) (Math.random() * 5);
	for (int i = -2; i<length; i++) {
		name += (char)((int) (Math.random() * 25 + 65));
	}
	return name;

}
}