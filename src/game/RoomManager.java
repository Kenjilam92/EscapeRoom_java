package game;
import fixtures.Room;
import java.util.List;

public class RoomManager {
	//the room that a player should start
	private Room startingRoom;
	// all rooms
	private List<Room> rooms;
	public RoomManager(Room s, List<Room> rs) {
		startingRoom = s;
		rooms= rs;
	}
	public Room getStartingRoom() {
		return startingRoom;
	}
	public List<Room> getRooms() {
		return rooms;
	}
//	public RoomManager addRome(Room newRoom ) {
//		rooms.add(newRoom);
//	}
}
