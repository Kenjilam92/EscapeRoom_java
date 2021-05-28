package fixtures;
import tools.Print;
import java.util.ArrayList;

public class Room extends Fixture implements Print{
	private Room[] exits;
	public ArrayList<Item> container;
	public ArrayList<NPC> charactors; 
	public Room(String name, String shortDescription, String longDescription, Room[] esc) {
		super(name, shortDescription, longDescription);
		exits = esc;
	}
	public Room(String n, String s, String l) {
		this(n,s,l,null);
		container= new ArrayList<Item>();
		charactors= new ArrayList<NPC>();
	}
	public Room[] getExit() {
		return exits;
	}
	public Room getExit( String direction) {
		Room result = null;
		if ( direction.equals("north") ) {
			result = exits[0];
		}
		else if ( direction.equals("east") ) {
			result = exits[1];
		}
		else if ( direction.equals("south") ) {
			result = exits[2];
		}
		else if ( direction.equals("west") ) {
			result = exits[3];
		}
		else {
			println("Error!!!!!!!!!!!!!!!!!!!!");
		}
//		switch (direction) {
//			case "north": result = exits[0];
//			case "east" : result = exits[1];
//			case "south": result = exits[2];
//			case "west" : result = exits[3];
//		}		
		return result; 
	}
	
	public void setExits (Room[] rooms) {
		exits = rooms;
	}
	public Room addItem( Item it) {
		container.add(it);
		return this;
	}
	public Room addCharactor(NPC pp ) {
		charactors.add(pp);
		return this;
	}
	
	public boolean showItems() {
		printBorder();
		if (container.size()==0) {
			println("there is nothing here to pick up");
			return false;
		}
		println("Which item do you want to pick up?");
		for (int i=0; i < container.size(); i++) {
			print((i+1) + ". ");
			println(container.get(i).name);
		}
		return true;
	}
	public boolean showCharactors() {
		printBorder();
		if (charactors.size()==0) {
			println("there is nobody here");
			return false;
		}
		println("Who do you want to talk to?");
		for (int i=0; i < charactors.size(); i++) {
			print((i+1)+ ". ");
			println(charactors.get(i).name);
		}
		return true;
	}
	
}
