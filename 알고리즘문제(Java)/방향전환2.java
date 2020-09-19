package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 방향전환2 {
	static boolean[][] b;
	static int[][] map;
	static int[] dx = { -1, 1 };
	static int[] dy = { -1, 1 };
	static int y2;
	static int x2;
	static int result = 987654321;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int y1 = sc.nextInt() + 100;
			int x1 = sc.nextInt() + 100;
			y2 = sc.nextInt() + 100;
			x2 = sc.nextInt() + 100;
			b = new boolean[202][202];
			result = 987654321;
			dfs(y1, x1, 0, 1);
			dfs(y1, x1, 0, 0); // move가 0인게 가로 1인게 세로
		}
	}

	public static void dfs(int i, int j, int cnt, int move) {
		if (i == y2 && j == x2) {
			result = Math.min(cnt, result);
			return;
		}
		if (b[i][j])
			return;
		b[i][j] = true;
		for (int k = 0; k < 2; k++) {
			if (i + dy[k] < 0 || i + dy[k] > 200 || j + dx[k] < 0 || j + dx[k] > 200)
				continue;
			if (move == 0) {
				dfs(i + dy[k], j, cnt + 1, 1);
			}
			if (move == 1) {
				dfs(i, j + dx[k], cnt + 1, 0);
			}
		}
		b[i][j] = false;
	}
}
