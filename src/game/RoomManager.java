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
	public void setStartingRoom(int i) {
		startingRoom = rooms[i];
	}
	
	public RoomManager setRooms (int index, Room r) {
		rooms[index] = r;
		return this;
	}
	
	public Room getRoomByIndex (int i) {
		return rooms[i];
	}
	
//	public RoomManager addRome(Room newRoom ) {
//		rooms.add(newRoom);
//	}
}
