package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	static int[] parents;
	static int[] rank;
	static int result = 0;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			edges[i][0] = sc.nextInt(); // 간선1
			edges[i][1] = sc.nextInt(); // 간선2
			edges[i][2] = sc.nextInt(); // 가중치
		}
		Arrays.sort(edges, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {

				return Integer.compare(o1[2], o2[2]);
			}

		});

		for (int i = 0; i < V; i++) {
			makeSet(i);
		}
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			int a = findSet(edges[i][0]);
			int b = findSet(edges[i][1]);
			if(a==b)
				continue;
			union(a,b);
			result+=edges[i][2];
			cnt++;
			if(cnt==V-1)
				break;
		}
	}

	static void makeSet(int x) {
		parents[x] = x;
	}

	static int findSet(int x) {
		if (x == parents[x])
			return x;
		else {
			return parents[x] = findSet(parents[x]);
		}
	}

	static void union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		if (rank[px] > rank[py]) {
			parents[py] = px;
		} else {
			parents[px] = py;
			if (rank[px] == rank[py]) {
				rank[py]++;
			}
		}
	}
}
