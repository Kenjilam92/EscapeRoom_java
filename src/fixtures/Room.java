package fixtures;

public class Room extends Fixture{
	private Room[] exits;
	public Room(String name, String shortDescription, String longDescription, Room[] esc) {
		super(name, shortDescription, longDescription);
		exits = esc;
	}
	public Room[] getExit() {
		return exits;
	}
	public Room getExit( String direction) {
		Room result = null;		
		switch (direction) {
			case "north": result = exits[0];
			case "east" : result = exits[1];
			case "west" : result = exits[2];
			case "south": result = exits[3];
		}		
		return result;
	}
	
	
}
