package fixtures;
import tools.*;
import game.*;
import java.util.Scanner;
import validation.*;

public class NPC extends Charactor implements Print,GetInt {
	private String image;
	private String[][] regularDialog;
	private String[] specialDialog;
	private String neededItemName;
	private boolean condition;
	public void setCondition(boolean cd) {
		condition =cd;
	}
	
	public NPC(String n, Room r) {
		super(n,r);
		condition = false;
	}
	
	public void setImage( String img) {
		image = img;
	}
	public void setRegularDialog ( String[][] opt) {
		regularDialog = opt;
	}
	public void setSpecialDialog( String[] dia ) {
		specialDialog = dia;
	}
	public void setNeedItemName (String i) {
		neededItemName= i;
	}
	
	public void playRegularDialog (Player p , Scanner scan) {
		printBorder();
		println (image);
		boolean isEnd = false;
		do {
			printBorder();
			println ("How do you want to react with?");
			for( int i = 0 ; i < regularDialog.length+1; i++) {
				print((i+1)+". ");
				if(i == regularDialog.length) {
					println("Go back");
				}
				else {
					println(regularDialog[i][0]);
				}
			}
			int choice = getInt(scan, regularDialog.length+1, 1) - 1 ;
			if (choice > regularDialog.length+1 || choice < 0) {
				println("Please choose availuable option!");
				playRegularDialog(p,scan);
			}
			else if( choice == regularDialog.length){
				isEnd = true;
			}
			else {
				printBorder();
				for(int i =0; i< regularDialog[choice].length; i++) {
					if( i%2 == 0 ) {
						print(p.name + ": ");
					}
					else {
						print(name+ ": ");
					}
					println(regularDialog[choice][i]);
				}
			}
		} while(!isEnd);
	}
	
	public void playSpecialDialog(Player p ) {
		for(int i =0; i< specialDialog.length; i++) {
			if( i%2 == 0 ) {
				print(name+ ": ");
			}
			else {
				print(p.name + ": ");
			}
			println(specialDialog[i]);
		}
	}
	
	public Item receiveItem(Item it, Player p ) {
		inventory.add(it);
		if(it.name.equals(neededItemName)) {
			Item temp = inventory.get(0);
			inventory.remove(0);
			playSpecialDialog(p);
			return temp;
		}
		return null;
	}
	public void addItem(Item it) {
		inventory.add(it);
	}
}
