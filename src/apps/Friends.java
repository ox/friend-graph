package apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friends {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scstdin = new Scanner(System.in);
		
		System.out.println("graph file: ");
		String line = scstdin.next();

		Scanner scfile = new Scanner(new File(line));
	}
}