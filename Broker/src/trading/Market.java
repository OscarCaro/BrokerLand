package trading;

public class Market {
	public final int maxM= 1000;
	public static Asset assets[];
	public static int size;
	public Market() {
	
		assets = new Asset[maxM];
	}
	 public void initMarket() {
		size = 0;
		assets[size]= new Asset();
		assets[size + 1]= new Asset();
		assets[size+ 2]= new Asset();
		size = 3;
	}
	
	boolean addAsset(Asset a) {
		if(Market.size<maxM) {
			Market.assets[size] = a;
			size++;
			return true;
		}
		return false;
	}
	
	public void opportunity() {
		assets[size]= new Asset();
		size++;
	}
	
	public static void showState() {
		System.out.println("The market sits currently at: ");
		for(int i = 0; i < size; i++) {
			System.out.println(i + ": " + assets[i] );
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
