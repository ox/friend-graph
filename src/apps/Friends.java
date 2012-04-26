package apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Friends {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner stdin = new Scanner(System.in);
		
		System.out.println("graph file: ");
		String line = stdin.next();

		Scanner scfile = new Scanner(new File(line));

		System.out.print("\tChoose action: ");
		System.out.print("students at school");
		System.out.print("shortest intro chain");
		System.out.print("cliques at school");
		System.out.print("connectors");
		System.out.print("(q)uit=>");
		char response = stdin.next().toLowerCase().charAt(0);
		while (response=='s') {
			
		}
	}
}