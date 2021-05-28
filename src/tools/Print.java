package tools;

public interface Print {
	public default void println(String a ) {
		System.out.println(a);
	}
	public default void print(String i ) {
		System.out.print(i);
	}
	public default void printBorder() {
		System.out.println("***************************************************************************");
	}
}
