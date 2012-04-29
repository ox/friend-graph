package structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph {

	class Vertex {
		String name;
		String school;
		ArrayList<Vertex> friends;
		Vertex(String name, String school) {
			this.name = name;
			this.school = school;
			this.friends = new ArrayList<Vertex>();
		}
	}

	Vertex[] adjLists;
	
	/**
	 * Initialize graph with input from file
	 * 
	 * @param file File that contains graph input
	 * @throws FileNotFoundException If file is not found
	 */
	public Graph(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(file));//.useDelimiter("\\n"); we'll break the new lines ourselves
		int num = Integer.parseInt(sc.nextLine());
		adjLists = new Vertex[num];
		//sc.nextLine(); empty line. damn you java
		System.out.println(adjLists.length + " friends");
		
		// read vertices
		for (int v=0; v < adjLists.length; v++) {
			String in = sc.nextLine();
			int first_pipe = in.indexOf('|');
			
			String name = in.substring(0, first_pipe);
			Boolean student = false;
			String school = "";
			
			System.out.print(name);
			
			if(in.charAt(first_pipe + 1) == 'y') {
				student = true;
				school = in.substring(first_pipe + 3);
				System.out.print(" (" + school + ")");
			}
			
			if(student) {
				adjLists[v] = new Vertex(name, school);
			} else {
				adjLists[v] = new Vertex(name, null);
			}
			System.out.print("\n");
		}
		
		// read edges
		while (sc.hasNext()) {
			String line = sc.nextLine();
			int first_pipe = line.indexOf('|');
			
			int v1 = indexForName(line.substring(0, first_pipe));
			int v2 = indexForName(line.substring(first_pipe + 1));
			
			adjLists[v1].friends.add(adjLists[v2]); // = new Friend(v2, adjLists[v1].friends);
			adjLists[v2].friends.add(adjLists[v1]); // = new Friend(v1, adjLists[v2].friends);
			
			System.out.println(nameForIndex(v1) + " -> " + nameForIndex(v2));
		}
	}
	
	public void dfs() {
		boolean[] visited = new boolean[adjLists.length];
		for (int v=0; v < visited.length; v++) visited[v] = false;
		for (int v=0; v < visited.length; v++) {
			if (!visited[v]) {
				System.out.println("Starting at " + adjLists[v].name);
				dfs(v, visited);
			}
		}
	}
	
	// recursive dfs
	private void dfs(int v, boolean[] visited) {
		visited[v] = true;
		System.out.println("visiting " + adjLists[v].name);
		for (int e = 0; e < adjLists.length; e++) {
			if (!visited[e]) {
				System.out.println(adjLists[v].name + "--" + adjLists[e].name);
				dfs(e, visited);
			}
		}
	}
	
	public int indexForName(String name) {
		for (int v=0; v < adjLists.length; v++) {
			if (adjLists[v].name.equals(name)) {
				return v;
			}
		}
		return -1;
	}
	
	public String nameForIndex(int index) {
		if(adjLists[index] != null) {
			return adjLists[index].name;
		}
		return null;
	}
	
	/*
	 * Subgraph, students are a school
	 */
	
	public Vertex[] studentsAtSchool(String school) {
		ArrayList<Vertex> students = new ArrayList<Graph.Vertex>();
		for(int i = 0; i < adjLists.length; i++) students.add(adjLists[i]);
		ArrayList<Vertex> enemies = new ArrayList<Graph.Vertex>();

		for(int i = 0; i < students.size(); i++) {
			if(students.get(i).school == null || !students.get(i).school.equals(school)) {
				Vertex enemy = students.get(i);
				System.out.println(enemy.name + " (" + i + ") doesnt belong");
				System.out.println(enemy.name + " has " + enemy.friends.size() + " friends");
				unfriend(enemy.friends, enemy);
				enemies.add(students.get(i));
			}
		}
		students.removeAll(enemies);
		return students.toArray(new Vertex[0]);
	}
	
	private void unfriend(Vertex source, Vertex enemy) {
		System.out.println(source.name + " is unfriending " + enemy.name);
		source.friends.remove(enemy);
		enemy.friends.remove(source);
	}
	
	private void unfriend(Vertex source, ArrayList<Vertex> enemies) {
		System.out.print(source.name + " is unfriending ");
		for(int i = 0; i < enemies.size(); i++) {
			System.out.println(enemies.get(i).name + " ");
			enemies.get(i).friends.remove(source);
		}
		source.friends.removeAll(enemies);
	}
	
	private void unfriend(ArrayList<Vertex> sources, Vertex enemy) {
		for(int i = 0; i < sources.size(); i++) {
			System.out.print(sources.get(i).name + " ");
			sources.get(i).friends.remove(enemy);
		}
		System.out.println(" are unfriending " + enemy.name);
		enemy.friends.removeAll(sources);
	}
	
	public void printFriends() {
		for(int i = 0; i < adjLists.length; i++) {
			System.out.println(i + ": " + adjLists[i].name);
			System.out.print("\t[");
			for(int k = 0; k < adjLists[i].friends.size(); k++)
				System.out.print(adjLists[i].friends.get(k).name + " ");
			
			System.out.println("]");
		}
	}
	/*
	 * Connected Islands
	 */
	
	public void connectedIslands(String school) {
		Vertex[] students = studentsAtSchool(school);
		HashMap<Vertex, Boolean>listed = new HashMap<Graph.Vertex, Boolean>();
		
		System.out.println(students.length);
		for(int i = 0; i < students.length; i++) {
			System.out.print(students[i].name);
			listed.put(students[i], false);
			if(students[i].school != null) {
				System.out.println("|y|" + students[i].school);
			} else {
				System.out.println("|n");
			}
		}
		
		for(int i = 0; i < students.length; i++) {
			if(!listed.get(students[i])) {
				for(int f = 0; f < students[i].friends.size(); f++) {
					if(!listed.get(students[i].friends.get(f))) {
						System.out.println(students[i].name + "|" + students[i].friends.get(f).name);
						listed.put(students[f], true);
					}
				}
			}
		}
	}
	
	public void shortestPath(String source, String target) {
		Vertex[] path = new Vertex[adjLists.length];
		
		HashMap<Graph.Vertex, Integer> distances = new HashMap<Graph.Vertex, Integer>();
		HashMap<Graph.Vertex, Integer> prev = new HashMap<Graph.Vertex, Integer>();
		PriorityQueue<Graph.Vertex> q = new PriorityQueue<Graph.Vertex>();
		for(int i = 0; i < adjLists.length; i++) {
			distances.put(adjLists[i], (int)Float.POSITIVE_INFINITY);
			prev.put(adjLists[i], -1);
			q.add(adjLists[i]);
		}
		
		distances.put(adjLists[indexForName(source)], 0);
		
			
	}
}
