package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Danji {
	static int[][] map;
	static boolean[][] b;
	static int N;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int danjicnt = 0;
	static int cnt = 1;

	public static void main(String[] args) throws FileNotFoundException {
		Stack<Integer> stack = new Stack<Integer>();
		PriorityQueue<Integer> q  = new PriorityQueue<Integer>();
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String[] s = new String[N + 2];
		map = new int[N + 2][N + 2];
		b = new boolean[N + 2][N + 2];

		for (int i = 1; i < N + 1; i++) {
			s[i] = sc.next();
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = s[i].charAt(j - 1) - 48;
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				cnt = 1;
				if (map[i][j] == 1) {
					if (b[i][j])
						continue;
//					System.out.print(i + " " + j);
					dfs(i, j);
					danjicnt++;
					q.offer(cnt);
				}
			}
		}
		System.out.println(danjicnt);
		int l = q.size();
		for (int i = 0; i < l; i++) {
			System.out.println(q.poll());
		}
	}

	public static void dfs(int i, int j) {
		if (b[i][j])
			return;

		b[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (b[i + dy[k]][j + dx[k]])
				continue;
			if (map[i + dy[k]][j + dx[k]] == 1) {
				cnt++;
				dfs(i + dy[k], j + dx[k]);
			}
		}

	}

}