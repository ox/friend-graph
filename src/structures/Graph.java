package structures;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {

	public class Neighbor {
		public int vnum;
		public Neighbor next;
		public Neighbor(int vnum, Neighbor nbr) {
			this.vnum = vnum;
			next = nbr;
		}
	}

	class Vertex {
		String name;
		String school;
		Neighbor neighbors;
		Vertex(String name, String school, Neighbor neighbors) {
			this.name = name;
			this.school = school;
			this.neighbors = neighbors;
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
		Scanner sc = new Scanner(new File(file)).useDelimiter("\\n"); //we'll break the new lines ourselves
		adjLists = new Vertex[sc.nextInt()];
		sc.nextLine(); //empty line. damn you java
		System.out.println(adjLists.length + " friends");
		
		// read vertices
		for (int v=0; v < adjLists.length; v++) {
			String in = sc.nextLine();
			
			System.out.println(in);
			
			int first_pipe = in.indexOf('|');
			
			String name = in.substring(0, first_pipe);
			Boolean student = false;
			String school = "";
			
			if(in.charAt(first_pipe + 1) == 'y') {
				student = true;
				school = in.substring(first_pipe + 3);
			}
			
			if(student) {
				adjLists[v] = new Vertex(name, school, null);
			} else {
				adjLists[v] = new Vertex(name, null, null);
			}
		}
		
		// read edges
		while (sc.hasNext()) {
			String line = sc.nextLine();
			int first_pipe = line.indexOf('|');
			
			int v1 = indexForName(line.substring(0, first_pipe));
			int v2 = indexForName(line.substring(first_pipe + 1));
			
			adjLists[v1].neighbors = new Neighbor(v2, adjLists[v1].neighbors);
			adjLists[v2].neighbors = new Neighbor(v1, adjLists[v2].neighbors);
		}
	}
	
	public void dfs() {
		boolean[] visited = new boolean[adjLists.length];
		for (int v=0; v < visited.length; v++) {
			visited[v] = false;
		}
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
		for (Neighbor e=adjLists[v].neighbors; e != null; e=e.next) {
			if (!visited[e.vnum]) {
				System.out.println(adjLists[v].name + "--" + adjLists[e.vnum].name);
				dfs(e.vnum, visited);
			}
		}
	}
	
	int indexForName(String name) {
		for (int v=0; v < adjLists.length; v++) {
			if (adjLists[v].name.equals(name)) {
				return v;
			}
		}
		return -1;
	}
}
