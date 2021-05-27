package validation;
import java.util.Scanner;

public interface Direction {
	public default String getDirection( Scanner scan ){
		System.out.print("Your Answer: ");
		String input = scan.next();
		input = input.toLowerCase();
		boolean isValid = false;
		String[] condition = {"north","east","west","south"};
		for (String var : condition ) {
			if (input.equals(var)) {
				isValid = true;
				break;
			}
		}
//		return isValid? input : ( getDirection(scan) ) ; 
		
		if(isValid) {
			return input;
		}
		else {
			System.out.println("Invalid Input! Please try agian without spaces");
			return getDirection(scan);
		}
	}
}
