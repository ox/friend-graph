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
		Scanner scfile = new Scanner(new File(line));
		Graph graph = new Graph();
		graph.build(scfile);
		char option;
		while ((option = getOption()) != '5') {
			if (option=='1') { //Subgraph
				System.out.print("\tName of school: ");
			} else if (option=='2') { // Shortest Path
				System.out.print("\tName of person who wants the intro: ");
				String oneName = stdin.next();
				System.out.print("\tName of person who will be introduced to " + oneName + ": ");
				String twoName = stdin.next();
			} else if (option=='3') { // Connected Islands
				System.out.println("\tName of school for which cliques are to be found: ");
			} else if (option=='4') { // Connectors
				
			}
		}
	}
	
	static char getOption() {
		System.out.println("\tChoose algorithm ");
		System.out.println("\t1--Subgraph (Students at School)");
		System.out.println("\t2--Shortest Path (Intro Chain)");
		System.out.println("\t3--Connected Islands (Cliques at Schools)");
		System.out.println("\t4--Connectors");
		System.out.print("\t5--quit => ");
		char response = stdin.next().charAt(0);
		while (response!='1'&&response!='2'&&response!='3'&&response!='4'&&response!='5') {
			System.out.print("\tEnter 1, 2, 3, 4, or 5 => ");
			response = stdin.next().charAt(0);
		}
		return response;
	}
}