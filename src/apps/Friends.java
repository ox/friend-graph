package apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import structures.Graph;

public class Friends {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner stdin = new Scanner(System.in);
		
		//System.out.print("graph file: ");
		//String line = stdin.nextLine();

		Graph graph = new Graph("ft.txt");
		
		System.out.println("Menu:");
		String[] options = {"[S]tudents at school", "s[H]ortest path", "connected [I]slands", "[C]onnectors", "[Q]uit"};
		for(int i = 0; i < options.length; i++) System.out.println(options[i]);
		
		char option = stdin.next().charAt(0);
		while(option != 'q') {
			switch(option) {
				case 's':
					System.out.println("Students at school");
					String school = stdin.nextLine();
					
					Graph.studentsAtSchool(school);
					break;
				case 'h':
					System.out.println("Shortest path");
					break;
				case 'i':
					System.out.println("Connected Islands");
					break;
				case 'c':
					System.out.println("Connectors");
					break;
			}
			option = stdin.next().charAt(0);
		}
	}
}