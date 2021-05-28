package fixtures;
import game.Player;
import tools.*;

public abstract class Item implements Print {
	public String name;
	public String desc;
	public Item (String n, String d) {
		name = n;
		desc = d;
	}
	
	public boolean use ( Player target ) {
		printBorder();
		println("target can not use this item");
		return false;
	}
	
}
