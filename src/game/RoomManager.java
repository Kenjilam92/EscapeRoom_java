package game;
import fixtures.Room;

public class RoomManager {
	//the room that a player should start
	private Room startingRoom;
	// all rooms
	private Room [] rooms;
	public RoomManager(Room s, Room[] rs) {
		startingRoom = s;
		rooms= rs;
	}
	public Room getStartingRoom() {
		return startingRoom;
	}
	public Room[] getRooms() {
		return rooms;
	}
//	public RoomManager addRome(Room newRoom ) {
//		rooms.add(newRoom);
//	}
}
