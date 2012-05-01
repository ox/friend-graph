package apps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import structures.Graph;

public class Friends {
	
	static Scanner stdin = new Scanner(System.in);
	
	public static void main(String[] args) 
	throws FileNotFoundException, IOException{
		System.out.print("Name of graph file: ");
		String line = stdin.next();
		Graph graph = new Graph(line);
		//Graph graph = new Graph("ft.txt");
		graph.printFriends();

		char option;
		while((option = getOption()) != 'q') {
			switch(option) {
				case 's':
					System.out.println("Students at school");
					System.out.print("\tName of school: ");
					String school = stdin.next();
					graph.studentsAtSchool(school);
					break;
				case 'h':
					System.out.println("Shortest path");
					System.out.print("\tName of person who wants the intro: ");
					String source = stdin.next();
					System.out.print("\tName of target who will be introduced to " + source + ": ");
					String target = stdin.next();
					graph.shortestPath(source, target);
					break;
				case 'i':
					System.out.println("Connected Islands");
					System.out.println("\tName of school for which cliques are to be found: ");
					school = stdin.next();
					graph.connectedIslands(school);
					break;					
				case 'c':
					System.out.println("Connectors");
					graph.connectors();
					break;
				case 'd':
					graph.dfs();
					break;
			}
		}
	}
	
	static char getOption() {
		System.out.println("Menu:");
		String[] options = {"[S]tudents at school", "s[H]ortest path", "connected [I]slands", "[C]onnectors", "[Q]uit"};
		for(int i = 0; i < options.length; i++) System.out.println(options[i]);
		System.out.print("=> ");
		char response = stdin.next().toLowerCase().charAt(0);
		while (response!='s'&&response!='h'&&response!='i'&&response!='c'&&response!='q') {
			System.out.print("\tEnter S, H, I, C, or Q => ");
			response = stdin.next().toLowerCase().charAt(0);
		}
		return response;
	}
}