package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Prim {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt();
		int E = sc.nextInt();
		int[][] adj = new int[V][V];
//		간선의 정보를 받는다.
		for (int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			adj[a][b] = c;
			adj[b][a] = c;
		}
		boolean[] check = new boolean[V];
		int[] key = new int[V]; // 현저 선택된 정점으로부터 도달할 수 있는 최소거리
		int[] p = new int[V]; // 최소신장트리 배열
//		key 초기값은 무한대
		Arrays.fill(key, Integer.MAX_VALUE);

		p[0] = -1; // 아무거나 선택 부모x 거리0
		key[0] = 0; // 이미 하나 고름
		for (int i = 0; i < V - 1; i++) {
			int min = Integer.MAX_VALUE;
//			안골라진것중 key가 가장 작은것
			int index = -1; // 찾으면 위치를 여기에 지정
			for (int j = 0; j < V; j++) {
				if (!check[j] && key[j] < min) {
					index = j;
					min = key[j];
				}
			}
			// index : 선택이 안 된 정점 중에 key가 젤 작은 친구
			check[index] = true;
			// index정점에서 출발하는 모든 간선에 대해서 key를 업데이트
			for (int j = 0; j < V; j++) {
				// check가 안되어있으면서, 간선은 존재하고, 그 간선이 key값보다 작다면, key값을 업데이트
				if (!check[j] && adj[index][j] != 0 && key[j] > adj[index][j]) {
					p[j] = index;
					key[j] = adj[index][j];
				}
			}
		}
		int result = 0;
		for (int i = 0; i < V; i++) {
			result +=key[i];
		}
		System.out.println(result);
		System.out.println(Arrays.toString(p));
	}
}
