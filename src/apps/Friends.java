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
		
		printMenu();
		
		graph.printFriends();
		
		char option = stdin.next().charAt(0);
		while(option != 'q') {
			switch(option) {
				case 's':
					System.out.println("Students at school");
					String school = stdin.next().toLowerCase();
					
					graph.studentsAtSchool(school);
					break;
				case 'h':
					System.out.println("Shortest path");
					break;
				case 'i':
					System.out.println("Connected Islands");
					school = stdin.next().toLowerCase();
					
					graph.connectedIslands(school);
					break;
				case 'd':
					graph.dfs();
					break;
				case 'c':
					System.out.println("Connectors");
					break;
			}
			printMenu();
			option = stdin.next().charAt(0);
		}
	}
	
	private static void printMenu() {
		System.out.println("Menu:");
		String[] options = {"[S]tudents at school", "s[H]ortest path", "connected [I]slands", "[C]onnectors", "[D]fs", "[Q]uit"};
		for(int i = 0; i < options.length; i++) System.out.println(options[i]);
	}
}