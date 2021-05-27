package fixtures;
import tools.Print;

public class Room extends Fixture implements Print{
	private Room[] exits;
	public Room(String name, String shortDescription, String longDescription, Room[] esc) {
		super(name, shortDescription, longDescription);
		exits = esc;
	}
	public Room(String n, String s, String l) {
		this(n,s,l,null);
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
}
