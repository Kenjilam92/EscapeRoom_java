package game;
import fixtures.Room;
import java.util.Scanner; 
import tools.Print;

public class Main{		
	public static void main ( String[] args ) {
		RoomManager map = init();
		Scanner scan = new Scanner(System.in);
		Player p1 = start( scan, map.getStartingRoom() );
		p1.actionList(scan);
		
	}


	//method will print a prompt to the console for the player's current room, similar to the above image.
//	private static void printRoom (Player player) {
//		
//	}
	
	// method will use a Scanner object to collect console input from the user, 
	// and then will divide that input into multiple parts. 
	// Generally those parts will look like this:
	//	
//	private static String[] collectInput() {
//		
//	}
//	private static void parase (String[] command, Player player) {
//		
//	}
//	
	
	private static RoomManager init() {
		Room kitchen, livingRoom = null, office = null, gameRoom = null, bedroom = null, restRoom = null;
		kitchen = new Room("Kitchen"," Nice Kitchen", " Something is cooking",new Room[] {restRoom, livingRoom,null,null} );
		livingRoom = new Room ("Living Room", "Dark Living Room", "Tv is turned on", new Room[] {office, null, gameRoom, kitchen} );
		office = new Room ("Office", "There is a window", "The phone is ringing", new Room[] {null,null,livingRoom,null} );
		gameRoom = new Room ("Game Room", "There is a PS5", "You can play GT5 in this room", new Room[] {livingRoom,null,null,bedroom} );
		bedroom = new Room ("Bed Room", "The room smell badly","There is a Queen size bed at the corner", new Room[]{null, gameRoom, null,null} );
		restRoom = new Room ("Rest Room", "This is the rest room", "Water is every where", new Room[] {null,null,kitchen,null});
		RoomManager map = new RoomManager(livingRoom, new Room[] { livingRoom, kitchen, office, gameRoom, bedroom, restRoom} );
		return map;
	}
	private static Player start(Scanner scan, Room start) {
		printBorder();
		println("Welcome to the HomeTour Project");
		print("Please type in your name: ");
		print("");
		String name = scan.next();
		return new Player (name, start);
	}
	public static void println(String a ) {
		System.out.println(a);
	}
	public static void print(String a ) {
		System.out.print(a);
	}
	public static void printBorder() {
		System.out.println("***************************************************************************");
	} 
	
}
