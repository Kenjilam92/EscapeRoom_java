package fixtures;

public abstract class Fixture {
	public String name;
	public String shortDescription;
	public String longDescription;
	Fixture(String n, String s, String l){
		name= n;
		shortDescription = s;
		longDescription = l;
	}
}
