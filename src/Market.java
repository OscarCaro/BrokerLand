
public class Market {
	
	public static Asset assets[];
	public static int size;
	public Market() {
		assets = new Asset[20];
		size=0;
		assets[size]= new Asset();
		assets[size + 1]= new Asset();
		assets[size+ 2]= new Asset();
	 size = 3;
	}
	
	public void opportunity() {
		assets[size]= new Asset();
		size++;
	}
	
	public void showState() {
		System.out.println("The market sits currently at: ");
		for(int i = 0; i < size; i++) {
			System.out.println(assets[i] );
		}
	}
	
	public static int getOwned () {
		int a = 0;
		for(int i = 0; i < Market.size; i++) {
			if(Market.assets[i].owned) {
				 a++;
			}
		}
		return a;
	}
}
