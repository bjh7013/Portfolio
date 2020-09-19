package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class SafetyArea {
	static int N;
	static int[][] map;
	static boolean[][] b;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

//	static int N;
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 2][N + 2];
		int max = -1;
		int cnt = 0;
		int result = -1;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > max)
					max = map[i][j];
			}
		}
		for (int k = 0; k <= max; k++) {
			b = new boolean[N + 2][N + 2];
			cnt = 0;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] > k) {
						if (b[i][j])
							continue;
						dfs(i, j, k);
						cnt++;
					}
				}
			}
			if (cnt > result)
				result = cnt;
		}
		System.out.println(result);
	}

	public static void dfs(int i, int j, int k) {
		if (b[i][j])
			return;

		b[i][j] = true;
		for (int l = 0; l < 4; l++) {
			if (b[i + dy[l]][j + dx[l]])
				continue;
			if (map[i + dy[l]][j + dx[l]] > k) {
				dfs(i + dy[l], j + dx[l], k);
			}
		}

	}
}