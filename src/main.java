
public class main {

	public static int turn;
	public static Broker b;
	public static Market m;
	public static void main(String[] args) {
		b = new Broker();
		turn = 0;
		m = new Market();
		while(b.money >0) {
			showState();
			m.showState();
			m.opportunity();
			b.buy();
			b.refresh();
			b.showState();
			b.sell();
			turn++;
		}
		System.out.println("You went bankrupt and your addiction to cocaine made you go into rehab,\n you're now starting a new life in the fields of Maine.");
	}

	
	public static void showState() {
		System.out.println("Turn: " + turn + ".");
		System.out.println("Your portfolio amounts to: " + b.money + "$.");
	}
}
