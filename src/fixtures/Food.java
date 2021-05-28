package fixtures;
import game.Player;

public class Food extends Item{
	int healthPoint;
	public Food(String name, String desc, int hp) {
		super(name,desc);
		healthPoint = hp;
	}
	@Override 
	public boolean use ( Player p) {
		p.healthPoint+= healthPoint;
		return true;
	}
	
}
