package game;
import fixtures.*;
import java.util.Scanner; 
import tools.Print;

public class Main{		
	public static void main ( String[] args ) {
		RoomManager map = init();
		Scanner scan = new Scanner(System.in);
		boolean isEnd = false;
		do {
			Player p1 = start( scan, map.getStartingRoom() );
			isEnd = p1.actionList(scan);
		}
		while(!isEnd);
		printBorder();
		println("Thank you for playing this game! See you next time");
		println("");
		println(
				"创创创创创创创创创创创抖抖抖抖禱r\n"
				+ "创创创创创创创创创创抖创创创创创抖\r\n"
				+ "创创创抖抖洞创创创抖创创创创创创创抖\r\n"
				+ "创创炊创创炊创创抖创创炊洞创炊洞创创抖\r\n"
				+ "创创炊创创炊创炊洞创创炊洞创炊洞创创创抖\r\n"
				+ "创创炊创创洞炊洞创创创炊洞创炊洞创创创炊禱r\n"
				+ "创创创洞创洞创洞创创创创创创创创创创创创抖\r\n"
				+ "创创抖抖抖抖抖抖创创创创创创创创创创创创抖\r\n"
				+ "创炊创创创创创创洞抖创创创创创创炊洞创创抖\r\n"
				+ "创抖创创创创创创洞炊洞创创创创创炊洞创创抖\r\n"
				+ "炊洞创抖抖抖抖抖洞创炊洞创创创炊洞创创创抖\r\n"
				+ "炊创创创创创创创炊创创炊抖抖抖创创创创炊禱r\n"
				+ "炊洞创创创创创创炊创创创创创创创创创创抖\r\n"
				+ "创洞创抖抖抖抖抖抖创创创创创创创创创炊禱r\n"
				+ "创抖创创创创创炊创抖创创创创创创创创抖\r\n"
				+ "创炊抖抖抖抖抖洞创创抖创创创创创创抖\r\n"
				+ "创创创创创创创创创创创炊抖抖抖抖抖\r\n"
		);
		printBorder();
		scan.close();
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
		
		RoomManager map = new RoomManager(null, new Room[6] );
		Object[][] data = new Object[][] {
			{ 
				"Bed Room", 
				"This is your bedroom",
				"Because you are busy web developer, you don't have time to clean up your room. \n"
				+ "Your stuffs are everywhere. On your bed, you can see your computer and a pen rolling around."
				+ "A cookie is under the table next to your shoes.", 
				new int[] {0,0,4,0} 
			},
			{ 	"Kitchen",
				"Jane is here",
				"Jane is cooking a big pot of chicken curry. She wants to imporve her recipe so she is writing down all details \n"
				+ "She keep shaking her pen. It may be out of ink. Hope she didn't add any ink to our dinner.",
				new int[] {0,3,5,0}
			},
			{ 	"Rest Room", 
				"This is the rest room", 
				"The sink is broken! Water is every where, Ew!!! \n"
				+ "I really don't have time for this", 
				new int[] {0,0,0,2} 
			},
			{ 
				"Game Room", 
				"My favourite room in the house", 
				"There is a PS5 in the room. Billy usually spend hours for playing GT5 in this room. \n"
				+ "On the table, there are some peice of cross word puzzles and pens. ", 
				new int[] {1,5,0,0} 
			},
			{ 
				"Living Room", 
				"Bill is here", 
				"Tv is turned on. Bill is watching his favourite game show. \n "
				+ "From this room you can exit to kitchen, office and game room", 
				new int[] {2,6,0,4} 
			},
			{ 
				"Office", 
				"this is your office", 
				"The phone is ringing. It must be my boss calling for my absent. \n"
				+ "Let's double check if I missed anything else.", 
				new int[] {0,0,0,5} 
			}
		};
	
		/////// Set up room;
		
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
		
		///// Set up NPC
		
		String[][] kid_rD = new String[][] {
			{
				"How are you?", 
				"Hello Stranger!"
			},
			{
				"Do you see any key around here?",
				"Yes! I saw it, but do you have anything to trade?"
			},
			{
				"Do you want some cookie?",
				"Yes! Do you have some?"
			}
		};
		
		String[] kid_sD= new String[] {
				"Thank you for the cookies! Here is your reward!",
				"This is my key"
		};
		
		String kid_img =
				"______________________抖抖抖禱n"+
				"__________________抖抖抖抖抖抖抖禱n"+
				"________________抖禵__抖抖抖禵__抖禱n"+
				"_______________抖____抖__禵_抖____抖\n"+
				"______________抖____抖___禵__抖____抖\n"+
				"_____________抖____抖____禵___抖____抖\n"+
				"___抖抖抖抖抖禵___抖_____禵____禵____抖\n"+
				"__抖________禵____抖_____禵____抖_____禱n"+
				"__抖抖禵____禵____禵_____禵_____禵____禱n"+
				"______抖抖抖抖抖_抖______禵_____抖__抖禱n"+
				"____________抖抖抖抖禵___禵____抖抖禵禱n"+
				"_____________禵____抖抖抖抖抖抖禵____禱n"+
				"_____________禵___抖抖_______抖抖____禱n"+
				"_____________禵__抖__抖_____抖__抖___禱n"+
				"_____________禵__禵抖_禵____禵抖_禵__禱n"+
				"_____________禵__禵抖抖_____抖抖_禵__禱n"+
				"_____________禵__抖抖抖_____抖抖抖___禱n"+
				"_____________禵_________抖___________禱n"+
				"_____________禵__禵_____抖禵_____禵__禱n"+
				"_____________抖__抖_____________抖__抖\n"+
				"______________禵___抖禵______抖抖___禱n"+
				"______________抖_____抖抖抖抖禵____禱n"+
				"_______________抖禵______________抖\n"+
				"_________________抖禵__________抖禱n"+
				"____________________抖抖抖抖抖禱n"
				+"**************************************************\n"
				+ "This is Billy. Billy is Jane's brother";
		
		NPC kid = new NPC("Billy", map.getRoomByIndex(5));
		kid.setImage(kid_img);
		kid.setRegularDialog(kid_rD);
		kid.setNeedItemName("Cookies");
		kid.setSpecialDialog(kid_sD);
		
		
		NPC jane = new NPC("Jane", map.getRoomByIndex(1));
		String jane_img = "_________ad88888888888888888a,\r\n"
				+ "________a88888\"888888888888888888,\r\n"
				+ "______,8888\"__\"P8888888888888888888b,\r\n"
				+ "______d88_________`\"\"P888888888888888,\r\n"
				+ "_____,8888b_______________\"\"888888888888,\r\n"
				+ "_____d8P'''__,aa,______________\"\"888888888b\r\n"
				+ "_____888bbdd888888ba,__,I_________\"88888888,\r\n"
				+ "_____8888888888888888ba8\"_________,8888888b\r\n"
				+ "____,888888888888888888b,________,8888888888\r\n"
				+ "____(88888888888888888888,______,88888888888,\r\n"
				+ "____d888888888888888888888,____,8___\"888888b\r\n"
				+ "____88888888888888888888888__.;8'\"\"\"__(888888\r\n"
				+ "____8888888888888I\"8888888P_,8\"_,aaa,__888888\r\n"
				+ "____888888888888I:8888888\"_,8\"__`b8d'__(88888\r\n"
				+ "____(8888888888I'888888P'_,8)__________888088\r\n"
				+ "_____88888888I\"__8888P'__,8\")__________880888\r\n"
				+ "_____8888888I'___888\"___,8\"_(._.)_______808888\r\n"
				+ "_____(8888I\"_____\"88,__,8\"_____________,8888P\r\n"
				+ "______888I'_______\"P8_,8\"_____________,88808)\r\n"
				+ "_____(88I'__________\",8\"__M\"\"\"\"\"\"M___,8888988'\r\n"
				+ "____,8I\"____________,8(____\"aaaa\"___,888888\r\n"
				+ "___,8I'____________,888a___________,888888)\r\n"
				+ "__,8I'____________,888888,_______,888888888\r\n"
				+ "_,8I'____________,8888888'`-===-'888888888'\r\n"
				+ ",8I'____________,8888888\"________88888888\"\r\n"
				+ "8I'____________,8\"____88_________\"888888P\r\n"
				+ "8I____________,8'_____88__________`P888\"\r\n"
				+ "8I___________,8I______88____________\"8ba,.\r\n"
				+ "(8,_________,8P'______88______________88\"\"8bma,.\r\n"
				+ "_8I________,8P'_______88,______________\"8b___\"\"P8ma,\r\n"
				+ "_(8,______,8d\"________`88,_______________\"8b_____`\"8a\r\n"
				+ "__8I_____,8dP_________,8X8,________________\"8b.____:8b\r\n"
				+ "__(8____,8dP'__,I____,8XXX8,________________`88,____8)\r\n"
				+ "___8,___8dP'__,I____,8XxxxX8,_____I,_________8X8,__,8\r\n"
				+ "___8I___8P'__,I____,8XxxxxxX8,_____I,________`8X88,I8\r\n"
				+ "___I8,__\"___,I____,8XxxxxxxxX8b,____I,________8XXX88I,\r\n"
				+ "___`8I______I'__,8XxxxxxxxxxxxXX8____I________8XXxxXX8,\r\n"
				+ "*******************************************************************\n"
				+ "            This is Jane. Jane is the your friend";
		
		
		String[][] jane_rD = new String[][] {
			{
				"Hi, Jane", 
				"Hello!"
			},
			{
				"Do you see my key around here?",
				"No! Have you check your office"
			},
			{
				"Do have any cookie?",
				"Yes! I will give you as a reward if you bring me the pan in the game room",
				"Ok! No problem"
			},
			{
				"OMG! I am gonna be late",
				"Relax! Use your eyes not you mouth!"
			}
		};
		String[] jane_sD = {
			"Thank you! Here is your cookies"	
		};
		jane.setImage(jane_img);
		jane.setRegularDialog(jane_rD);
		jane.setNeedItemName("pen");
		jane.setSpecialDialog(jane_sD);
		
		
		
		NPC cat = new NPC("cat",map.getRoomByIndex(3));
		String cat_img = "____________________________________$$$$\r\n"
				+ "__________________________________$$____$\r\n"
				+ "_________________________________$_______$\r\n"
				+ "___$$$$_________________________$_________$\r\n"
				+ "__$____$$______________________$____$_____$\r\n"
				+ "_$_______$$___________________$____$$$____$\r\n"
				+ "_$_________$$_____$$$$_$$$___$____$$$$$___$\r\n"
				+ "$____________$_$$$__$$$__$$_$____$$$$$$___$\r\n"
				+ "$____$$_______$_$$__$$$__$$$____$$$$$$$___$\r\n"
				+ "$___$$$$_______$$$$_$$$___$$_____$$$$$$___$\r\n"
				+ "$___$$$$$$_____$$$$_$$$$__$$$______$$$$$__$\r\n"
				+ "$___$$$$$$$____$$$$_$$$$__$$$________$$$__$\r\n"
				+ "$___$$$$$______$$$$_$$$$__$$$$____________$\r\n"
				+ "$___$$$$_______$$$$_$$$$__$$$$____________$\r\n"
				+ "$___$$$________$$$$_$$$$__$$$$____________$\r\n"
				+ "$___$$_________$$$$__$$$__$$$$____________$\r\n"
				+ "$______________$$$___$$$___$$$____________$\r\n"
				+ "$_______________$_____$_____$_____________$\r\n"
				+ "_$_________________________________________$\r\n"
				+ "$________________________________$$$$_______$\r\n"
				+ "$_______________________________$__$$$______$\r\n"
				+ "$__________$$$$________________$$__$$$$_____$\r\n"
				+ "$_________$$$__$_______________$$$$$$$$_____$\r\n"
				+ "$________$$$$__$_______________$$$$$$$$_____$\r\n"
				+ "$________$$$$$$$$______________$$$$$$$$_____$\r\n"
				+ "$________$$$$$$$$_______________$$$$$$______$\r\n"
				+ "$________$$$$$$$$_______$___$____$$$$_______$\r\n"
				+ "$_________$$$$$$_____$__$$_$________________$\r\n"
				+ "$__________$$$$_______$$__$________________$\r\n"
				+ "_$_____________________$__$_______________$\r\n"
				+ "__$_____________________$$_______________$\r\n"
				+ "___$______________________________$$$___$\r\n"
				+ "____$$___________________________$___$_$\r\n"
				+ "______$$$_______________________$_____$\r\n"
				+ "_________$$$$_____________$$$$$$______$\r\n"
				+ "___$$________$$$$$$$$$$$$$____________$\r\n"
				+ "__$__$________$___$_________________$$\r\n"
				+ "__$___$_______$____$_____________$$$\r\n"
				+ "__$___$________$____$___________$$\r\n"
				+ "__$$$$$$_______$____$__________$__$\r\n"
				+ "___$___$$_______$___$_________$____$\r\n"
				+ "___$$$$$$$___$$$_$$$_________$_____$\r\n"
				+ "____$___$$$$$__$_____$_______$_____$\r\n"
				+ "_____$__$______$_____$_______$_____$\r\n"
				+ "______$$$$$$$$$$_____$_______$____$\r\n"
				+ "________________$$$$$_________$$$$\r\n"
				+ "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
				+ "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
				+ "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
				+ "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\r\n"
				+ "*******************************************************\n"
				+ "         This is Kitty, your pet";
		
		String[][] cat_rD = new String[][] {
			{
				"Hey, kidy, kidy, kidy!", 
				"Get lost! Hooman. I am having a nap here.",
				"Aw...! You sassy cat"
			}
		};
		cat.setImage(cat_img);
		cat.setRegularDialog(cat_rD);
		//// Set up Items
		
		Item cookies = new Food("Cookies","It's delicious",50);
		
		Item key = new regularItem ("key","This is what I need");
		
		Item pen = new regularItem ("pen", "This is a pen");
		
		Item document = new regularItem ("document", "This is an important document");
		
		Item toothBrush = new regularItem ("tooth brush", "this is a tooth brush");
		
		Item computer = new regularItem ("computer", "This is a surface pro 3");
		
		////placing Items and Characters
		kid.addItemToInventory(key);
		jane.addItemToInventory(cookies).addItemToInventory(cookies);
		
		map.getRoomByIndex(0).addItem(computer).addItem(pen).addItem(cookies);
		map.getRoomByIndex(1).addCharactor(jane);
		map.getRoomByIndex(2).addItem(toothBrush);
		map.getRoomByIndex(3).addCharactor(cat).addItem(pen);
		map.getRoomByIndex(4).addCharactor(kid);
		map.getRoomByIndex(5).addItem(document);
		
		
		return map;
	}
	
	
	private static Player start(Scanner scan, Room start) {
		printBorder();
		println("Welcome to the Escape Room Game");
		println("");
		print("Please type in your name: ");
		print("");
		String name = scan.next();
		printBorder();
		println("Hello, "+ name + "! "
				+ "You are about to go to work but can not leave because you can not find your car key.\n"
				+ "Let's walk around the house and ask every one about your key"
		);
		
		
		return new Player (name, start, "key");
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
