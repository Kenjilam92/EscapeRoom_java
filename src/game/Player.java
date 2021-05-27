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
		println(name + " is in " + currentRoom.name);
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
				lookAround();
				actionList(scan);
			}
			case 2:{
				move(scan);
			}
		}
	}
	
	public void lookAround() {
		printBorder();
		println(name + " is looking around and see:");
		println("working on the list");
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
		}
		checkStatus();
	}
	
	public void listDirection() {
		println("Where do you want to go?");
		String[] direction = {"North","East","West","South"};
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
