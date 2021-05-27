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
//		Do not work because the map will be null
//		Room kitchen, livingRoom, office, gameRoom, bedroom, restRoom;
//		kitchen = new Room("Kitchen"," Nice Kitchen", " Something is cooking",new Room[] {restRoom, livingRoom,null,null} );
//		livingRoom = new Room ("Living Room", "Dark Living Room", "Tv is turned on", new Room[] {office, null, gameRoom, kitchen} );
//		office = new Room ("Office", "There is a window", "The phone is ringing", new Room[] {null,null,livingRoom,null} );
//		gameRoom = new Room ("Game Room", "There is a PS5", "You can play GT5 in this room", new Room[] {livingRoom,null,null,bedroom} );
//		bedroom = new Room ("Bed Room", "The room smell badly","There is a Queen size bed at the corner", new Room[]{null, gameRoom, null,null} );
//		restRoom = new Room ("Rest Room", "This is the rest room", "Water is every where", new Room[] {null,null,kitchen,null});
//		RoomManager map = new RoomManager(livingRoom, new Room[] { livingRoom, kitchen, office, gameRoom, bedroom, restRoom} );
		
		RoomManager map = new RoomManager(null, new Room[6] );
		Object[][] data = new Object[][] {
			{ 
				"Bed Room", 
				"The room smell badly",
				"There is a Queen size bed at the corner", 
				new int[] {0,0,4,0} 
			},
			{ 	"Kitchen",
				"Nice Kitchen",
				"Something is cooking",
				new int[] {0,3,5,0}
			},
			{ 	"Rest Room", 
				"This is the rest room", 
				"Water is every where, Ew!!!", 
				new int[] {0,0,0,2} 
			},
			{ 
				"Game Room", 
				"There is a PS5", 
				"You can play GT5 in this room", 
				new int[] {1,5,0,0} 
			},
			{ 
				"Living Room", 
				"Dark Living Room", 
				"Tv is turned on", 
				new int[] {2,6,0,4} 
			},
			{ 
				"Office", 
				"There is a window", 
				"The phone is ringing", 
				new int[] {0,0,0,5} 
			}
		};
		
		for ( int i = 0; i < data.length; i++ ) {			
			Room r = new Room( (String)data[i][0], (String)data[i][1] , (String)data[i][2] );
			map.setRooms(i, r);
		}
		for ( int i =0; i < data.length; i++) {
			int[] location = (int[])data[i][3];
			Room[] temp = new Room[4];
			for ( int j = 0; j < location.length; j++ ) {
				int exit = location[j];
				if (exit == 0 ) {
					temp[j]=null;   
				}
				else {
					temp[j]= map.getRoomByIndex(location[j]-1);
				}
			}
			map.getRoomByIndex(i).setExits(temp);
			
		}
		map.setStartingRoom(4);
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
