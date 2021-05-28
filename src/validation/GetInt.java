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
	public default int getInt (Scanner scan, int max, int min) {
		System.out.print("Your Answer: ");
		try {
			
			int temp = scan.nextInt();
			if (temp > max || temp < min) {
				System.out.println("Invalid input! Please type a number:");
				return getInt(scan, max, min);
			}
			return temp;
		}
		catch (Exception e) {
			System.out.println("Invalid input! Please type a number:");
			scan.next();
			return getInt(scan, max, min);
		}
	}
}
