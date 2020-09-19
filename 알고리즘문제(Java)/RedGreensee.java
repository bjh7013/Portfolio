package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RedGreensee {
	static int N;
	static char[][] map;
	static char[][] mapL;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[][] b;
	static boolean[][] bL;
	static int sol = 0;
	static int solL = 0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		String[] s = new String[N];
		map = new char[N + 2][N + 2];
		mapL = new char[N + 2][N + 2];
		b = new boolean[N + 2][N + 2];
		bL = new boolean[N + 2][N + 2];

		for (int i = 0; i < N; i++) {
			s[i] = sc.next();
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = s[i - 1].charAt(j - 1);
			}
		}

		for (int i = 1; i < N + 1; i++) {
			mapL[i] = map[i].clone();
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (mapL[i][j] == 'G') {
					mapL[i][j] = 'R';
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (b[i][j])
					continue;
				dfs(i, j);
				sol++;
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (bL[i][j])
					continue;
				dfsL(i, j);
				solL++;
			}
		}
		System.out.println(sol + " " + solL);
	}

	public static void dfs(int i, int j) {
		if (b[i][j])
			return;
		b[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (b[i + dy[k]][j + dx[k]])
				continue;
			if (map[i + dy[k]][j + dx[k]] == map[i][j]) {
				dfs(i + dy[k], j + dx[k]);
			}
		}

	}

	public static void dfsL(int i, int j) {
		if (bL[i][j])
			return;
		bL[i][j] = true;
		for (int k = 0; k < 4; k++) {
			if (bL[i + dy[k]][j + dx[k]])
				continue;
			if (mapL[i + dy[k]][j + dx[k]] == mapL[i][j]) {
				dfsL(i + dy[k], j + dx[k]);
			}
		}
	}
}
