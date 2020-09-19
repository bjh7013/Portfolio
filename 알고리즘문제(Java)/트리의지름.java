package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 트리의지름 {
	static boolean[] check;
	static List<Tree>[] list;
	static int maxnode;
	static int maxdist;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();
		list = new ArrayList[V + 1];
		check = new boolean[V + 1];
		maxnode = 0;
		maxdist = 0;
		for (int i = 0; i < V + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < V; i++) {
			int node = sc.nextInt();
			for (int j = 0; j < V * 2 + 1; j += 2) {
				int cnode = sc.nextInt();
				if (cnode == -1) {
					break;
				}
				int dist = sc.nextInt();
				list[node].add(new Tree(cnode, dist));
				list[cnode].add(new Tree(node, dist));
			}
		}
		dfs(1, 0);
		check = new boolean[V + 1];
		
		dfs(maxnode, 0);
		System.out.println(maxdist);
	}

	static void dfs(int node, int dist) {
		check[node] = true;

		for (int i = 0; i < list[node].size(); i++) {
			if (!check[list[node].get(i).node]) {
				dfs(list[node].get(i).node, dist + list[node].get(i).dist);
			}
		}
		if (maxdist < dist) {
			maxdist = dist;
			maxnode = node;
		}

	}

	static class Tree {
		int node, dist;

		public Tree(int node, int dist) {
			super();
			this.node = node;
			this.dist = dist;
		}

		public Tree() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
}
