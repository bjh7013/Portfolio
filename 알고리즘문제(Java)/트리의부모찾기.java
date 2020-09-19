package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 백준 15666번 N과M
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class 트리의부모찾기 {
	static int[] root;
	static boolean[] check;
	static Queue<Integer>[] q = new LinkedList[100001];

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		check = new boolean[100001];
		for (int i = 0; i < 100001; i++) {
			q[i] = new LinkedList<>();
		}
		int N = sc.nextInt();
		root = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			root[i] = i;
		}
		for (int i = 0; i < N - 1; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();
			q[left].add(right);
			q[right].add(left);
		}
		check[1] = true;
		dfs(1);
		for (int i = 2; i < N + 1; i++) {
			System.out.println(root[i]);
		}
	}

	static void dfs(int n) {
		int size = q[n].size();
		for (int i = 0; i < size; i++) {
			int temp = q[n].poll();
			if (check[temp])
				continue;
			check[temp] = true;
			root[temp] = n;
			dfs(temp);
		}
	}

}
