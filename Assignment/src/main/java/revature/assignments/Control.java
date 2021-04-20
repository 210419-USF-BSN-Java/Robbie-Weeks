package revature.assignments;

public class Control {
	
	public static void main(String[] args) {
		printNumberInWord(5);
		printNumberInWord(66);
		
		String s = "abc";
		System.out.println(reverseString(s));
		
	}
	
	public static void printNumberInWord(int i) {
		switch (i) {
		case 0 : 
			System.out.println("Zero");
			break;
		case 1 : 
			System.out.println("One");
			break;
		case 2 : 
			System.out.println("Two");
			break;
		case 3 : 
			System.out.println("Three");
			break;
		case 4 : 
			System.out.println("Four");
			break;
		case 5 : 
			System.out.println("Five");
			break;
		case 6 : 
			System.out.println("Six");
			break;
		case 7 : 
			System.out.println("Seven");
			break;
		case 8 : 
			System.out.println("Eight");
			break;
		case 9 : 
			System.out.println("Nine");
			break;
		default :
			System.out.println(i);
			break;
		}
	}
	
	//Reverse String
	public static String reverseString(String s) {
		String reversed = "";
		for(int i = s.length() - 1; i >= 0; i--) {
			
			reversed = reversed + s.charAt(i);
		}
		
		return reversed;
	}

}