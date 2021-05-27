package tools;

public interface Print {
	public default void println(String a ) {
		System.out.println(a);
	}
	public default void print(String a ) {
		System.out.print(a);
	}
	public default void printBorder() {
		System.out.println("***************************************************************************");
	}
}
