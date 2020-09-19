package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// BOJ 1197
public class 최소스패닝트리프림 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		List<Prim>[] list = new ArrayList[V];
		for (int i = 0; i < V; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			int c = sc.nextInt();
			list[a].add(new Prim(b, c));
			list[b].add(new Prim(a, c));
		}

		boolean[] check = new boolean[V];
		int[] key = new int[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		key[0] = 0;
		int min;
		int index;
		for (int i = 0; i < V - 1; i++) {
			index = -1;
			min = Integer.MAX_VALUE;
			for (int j = 0; j < V; j++) {
				if (!check[j] && key[j] < min) {
					index = j;
					min = key[j];
				}
			}
			for (int j = 0; j < list[index].size(); j++) {
				if (!check[list[index].get(j).y]  && key[list[index].get(j).y] > list[index].get(j).val) {
					key[list[index].get(j).y] = list[index].get(j).val;
				}
			}

			check[index] = true;
		}
		long result = 0;
		for (int i = 0; i < V; i++) {
			result += key[i];
		}
		System.out.println(result);
	}

	static class Prim {
		int y, val;

		public Prim(int y, int val) {
			super();
			this.y = y;
			this.val = val;
		}
	}
}
