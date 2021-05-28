package game;
import  fixtures.*;
import java.util.Scanner;
import validation.*;
import tools.*;
import fixtures.Item;

public class Player extends Charactor implements Direction,Print,GetInt  {
	public int healthPoint;
	public String keyItemName;
	public boolean isWin;
	public boolean isLose;
	
	public Player (String n, Room r,String k) {
		super(n,r);
		healthPoint=100;
		isWin = false;
		isLose = false;
		keyItemName=k;
	}
	
	public void checkStatus() {
		printBorder();
		println(name + " is in " + currentRoom.name);
		println(currentRoom.shortDescription);
	}
	
	public boolean actionList(Scanner scan) {
		boolean isPlaying = true;
		do {
			printBorder();
			lose();
			win();
			if (isWin || isLose) {
				break;
			}
			println("What do you want to do?");
			String[] actions = {"Look Around","Move", "Check status","Use Items","Quit Game"};
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
				case 3:{
					printBorder();
					println("HP: " + healthPoint);
					checkInventory();
					actionList(scan);
					break;
				}
				case 4:{
					printBorder();
					if (inventory.size() == 0) {
						println("Your inventory is empty");
						actionList(scan);
					}
					else {
						useOrGiveItems(scan);
					}
					break;
				}
				case 5:{
					return true;
				}
				default:{
					println("Invalid Input! Please try again");
					actionList(scan);
				}
				
			}
		}while(isPlaying);
		return playAgain(scan);
	}
	/// see room descriptions, checkout items and NPC 
 	
	
	public void lookAround(Scanner scan) {
		printBorder();
		println(currentRoom.longDescription);
		boolean isValid = false;
		printBorder();
		do {
			println("what do you want to do?");
			println("1. Pick up items");
			println("2. Talk to someone");
			println("3. Back");
			int choice = getInt(scan);
			switch(choice) {
				case 1:{
					if(currentRoom.showItems()) {
						int opt = getInt(scan, currentRoom.container.size(), 1);
						pickupItem(opt);
						isValid = true;
					}
					printBorder();
					break;
				}
				case 2: {
					if (currentRoom.showCharactors()) {
						int opt = getInt(scan, currentRoom.charactors.size(),1);
						talkToNPC(scan, currentRoom.charactors.get(opt-1));
						isValid = true;
					}
					printBorder();
					break;
				}
				case 3: {
					actionList(scan);
					break;
				}
				default:{
					println("Please choose valid input!");
				}
			}
			
		}while(!isValid);
		lookAround(scan);
	}
	
	public void useOrGiveItems (Scanner scan) {
		println("Please select an item!");
		checkInventory();
		int itemNumber = getInt(scan, inventory.size(), 1) - 1;
		printBorder();
		println(inventory.get(itemNumber).name + " is selected");
		printBorder();
		boolean isUsed = false;
		do {
			println("How do you use it?");
			println("1. On yourself");
			println("2. Give to other");
			println("3. Back");
			int chooseTarget = getInt(scan,2,1);
			switch (chooseTarget) {
				case 1:{
					useItem(itemNumber);
					actionList(scan);
					isUsed = true;
					break;
				}
				case 2:{
					println("Who do you want to give to");
					for (int i=0; i < currentRoom.charactors.size() + 1; i++ ) {
						if(i == currentRoom.charactors.size()) {
							println((i+1) + ". Back");
						}
						else {
							String name =  currentRoom.charactors.get(i).name;
							println((i+1)+". "+ name);
						}
					}
					int select = getInt(scan, currentRoom.charactors.size() + 1, 1)-1;
					NPC target = currentRoom.charactors.get(select);
					giveItemToNPC(itemNumber,target);
					actionList(scan);
					isUsed= true;
					break;
				}
				case 3:
					useOrGiveItems(scan);	
					break;
				default:{
					println("Invalid input! Please try again!");
				}
			}
		}
		while(!isUsed);
	}
	
	public void move(Scanner scan) {
		printBorder();
		listDirection();
		String input = getDirection(scan);
		Room temp = currentRoom.getExit(input);
		if(temp == null) {
			printBorder();
			println(name+ " hitted a wall. Please try again");
			healthPoint -= 20;
			println("-20 hp");
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
	
	public void checkInventory() {
//		printBorder();
		if(inventory.size()==0) {
			println("Your Inventory is empty");
			return;
		}
		println("Here is your inventory");
		for(int i = 0; i<inventory.size(); i++) {
			println((i+1) + ". " + inventory.get(i).name );
		}
	}
	
	public void useItem (int i){
		try {
			Food temp = (Food) inventory.get(i);
			if(temp.use(this)) {
				inventory.remove(i);
			};
		}
		catch (Exception e) {
			printBorder();
			println("cannot use this item");
		}
	}
	
	public void talkToNPC(Scanner scan, NPC target ) {
		target.playRegularDialog(this, scan);
	}
	
	public void lose() {
		if (healthPoint<=0) {
			isLose = true;
			println (name + "'s health point is 0");
		}
		
		if(isLose) {
			printBorder();
			println(
					"________¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶__________ \r\n"
					+ "______¶¶¶¶¶¶_____________¶¶¶¶¶¶________ \r\n"
					+ "_____¶¶¶¶¶_________________¶¶¶¶¶¶______ \r\n"
					+ "____¶¶¶¶_____________________¶¶¶¶¶_____ \r\n"
					+ "___¶¶¶¶_______________________¶¶¶¶¶____ \r\n"
					+ "__¶¶¶¶_____¶¶¶¶_______¶¶¶¶______¶¶¶____ \r\n"
					+ "__¶¶¶_____¶¶¶¶¶¶_____¶¶¶¶¶¶_____¶¶¶¶___ \r\n"
					+ "_¶¶¶¶_____¶¶¶¶¶¶_____¶¶¶¶¶¶______¶¶¶___ \r\n"
					+ "_¶¶¶_______¶¶¶¶_______¶¶¶¶_______¶¶¶¶__ \r\n"
					+ "_¶¶¶______________________________¶¶¶__ \r\n"
					+ "_¶¶¶______________________________¶¶¶__ \r\n"
					+ "_¶¶¶______________________________¶¶¶__ \r\n"
					+ "_¶¶¶____________¶¶¶¶¶____________¶¶¶¶__ \r\n"
					+ "_¶¶¶¶________¶¶¶¶¶¶¶¶¶¶¶_________¶¶¶___ \r\n"
					+ "__¶¶¶______¶¶¶¶¶_____¶¶¶¶¶______¶¶¶¶___ \r\n"
					+ "__¶¶¶¶____¶¶¶___________¶¶¶____¶¶¶¶____ \r\n"
					+ "___¶¶¶¶___¶¶_____________¶¶___¶¶¶¶_____ \r\n"
					+ "____¶¶¶¶____________________¶¶¶¶¶______ \r\n"
					+ "_____¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_______ \r\n"
					+ "_______¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶¶_________ "
			);
			println("YOU LOSE");
		}
	}
	public void win() {
		if (isWin) {
			printBorder();
			println("888888888888888888888888888888888\r\n"
					+ "88888888888888888____88888____888\r\n"
					+ "8888888888888888______888_____888\r\n"
					+ "8888888888888888______888_____888\r\n"
					+ "8888888888888888______888_____888\r\n"
					+ "8888888888888888______88_____8888\r\n"
					+ "8888888888888888______88_____8888\r\n"
					+ "8888888888888888______88_____8888\r\n"
					+ "8888888888_____8______88_____8888\r\n"
					+ "8888___88______8______8_____88888\r\n"
					+ "888_____8______8______8_____88888\r\n"
					+ "888_____8______8______8_____88888\r\n"
					+ "888_____8______8______8_____88888\r\n"
					+ "888_____8____88888888888888888888\r\n"
					+ "8_8_____8___88________________888\r\n"
					+ "8_8_____8__88__________________88\r\n"
					+ "8__888888_888_________888_______8\r\n"
					+ "8_________88________8___________8\r\n"
					+ "8____________8888888____________8\r\n"
					+ "88_____________88_______________8\r\n"
					+ "88_______________88_____________8\r\n"
					+ "888______________8_____________88\r\n"
					+ "888_______________8___________888\r\n"
					+ "88888_____________8__________8888\r\n"
					+ "888888_____________________888888\r\n"
					+ "888888____________________8888888\r\n"
			);
			println("YOU WIN! YOU FOUND YOUR KEY");
		}
	}
	
	public boolean playAgain(Scanner scan) {
		printBorder();
		println("Do you want to play again");
		println("1. Yes");
		println("2. No");
		int choice = getInt(scan);
		switch(choice) {
			case 1:{
				isWin = false;
				isLose = false;
				return false;
			}
			case 2:{
				
				return true;
			}
			default:{
				break;
			}
		}
		return playAgain(scan);
	}
	
	public void giveItemToNPC(int i, NPC target) {
		Item temp = inventory.get(i);
		inventory.remove(i);
		Item feedBack = target.receiveItem(temp, this);
		if(feedBack==null) {
			println("Nothing return");
		};
		inventory.add(feedBack);
		if(feedBack.name.equals(keyItemName)){
			isWin = true;
		}
	}

	
	public void pickupItem (int i) {
		Item temp = currentRoom.container.get(i-1);
		if(temp.name.equals(keyItemName)) {
			isWin = true;
		}
		inventory.add(temp);
		currentRoom.container.remove(i-1);
	}
}
