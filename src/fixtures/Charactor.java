package fixtures;
import java.util.*;

public abstract class Charactor {
	public String name;
	public Room currentRoom;
	protected ArrayList<Item> inventory;
	public Charactor(String n, Room r) {
		name = n;
		currentRoom = r;
		inventory = new ArrayList<Item>();
	};
	public Charactor addItemToInventory(Item it) {
		inventory.add(it);
		return this;
	}
}
