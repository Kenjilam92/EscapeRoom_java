package game;
import  fixtures.Room;
import java.util.Scanner;
import validation.Direction;
import validation.GetInt;
import tools.Print;

public class Player implements Direction,Print,GetInt  {
	private String name;
	public Room currentRoom;
//	public int hp;
//	public Items[] inventory;
	public Player (String n, Room r) {
		name = n;
		currentRoom = r;
	}
	
	public void checkStatus() {
		printBorder();
		println(name + " is in " + currentRoom.name);
		println(currentRoom.shortDescription);
	}
	
	public void actionList(Scanner scan) {
		printBorder();
		println("What do you want to do?");
		String[] actions = {"Look Around","Move"};
		for (int i=0; i<actions.length ; i++) {
			println((i+1)+". "+actions[i]);
		}
		int choice = getInt(scan);
		switch (choice) {
			case 1: {
				lookAround(scan);
				break;
			}
			case 2:{
				move(scan);
				break;
			}
			default:{
				println("Invalid Input! Please try again");
				actionList(scan);
			}
			
		}
	}
	
	public void lookAround(Scanner scan) {
		printBorder();
		println(name + " is looking around and see:");
		println(currentRoom.longDescription);
		actionList(scan);
	}
	
	public void move(Scanner scan) {
		printBorder();
		listDirection();
		String input = getDirection(scan);
		Room temp = currentRoom.getExit(input);
		if(temp == null) {
			println(name+ " hitted a wall. Please try again");
			move(scan);
		}
		else {
			currentRoom = temp;
			checkStatus();
			actionList(scan);
		}
	}
	
	public void listDirection() {
		println("Where do you want to go?");
		String[] direction = {"North","East","South","West"};
		int i=0;
		for( Room path : currentRoom.getExit() ) {
			String text="";
			if (path == null) {
				text = "Wall"; 
			}
			else {
				text = path.name;
			}
			println(direction[i] + " : " + text );
			i++;
		}
	}
}
