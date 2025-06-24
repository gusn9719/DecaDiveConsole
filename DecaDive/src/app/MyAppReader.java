package app;

import java.util.Scanner;

public class MyAppReader {
	Scanner sc = new Scanner(System.in);

	public String readString(String message) {
		System.out.print(message);
		return sc.nextLine().strip();
	}

	public int readInt(String message) {
	    while (true) {
	        System.out.print(message);
	        String input = sc.nextLine().strip();
	        try {
	            return Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("정수만 입력하세요! ex) 1");
	        }
	    }
	}
}
