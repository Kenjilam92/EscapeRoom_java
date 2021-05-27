package validation;
import java.util.Scanner;

public interface GetInt {
	public default int getInt( Scanner scan) {
		System.out.print("Your Answer: ");
		try {
			return scan.nextInt();
		}
		catch (Exception e) {
			System.out.println("Invalid input! Please type a number:");
			scan.next();
			return getInt(scan);
		}
	}
}
