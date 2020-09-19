package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class 소문난칠공주 {
	static int N = 5;
	static char[][] map;
	static char[] longmap;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[] seven;
	static boolean[][] sevenxy;
	static boolean[][] check;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		map = new char[N + 1][N + 1];
		seven = new char[8];
		longmap = new char[N * N + 1];
		sevenxy = new boolean[N + 1][N + 1];
		check = new boolean[N + 1][N + 1];
		result = 0;
		String s = "";
		for (int i = 0; i < N; i++) {
			s = sc.next();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				longmap[(i * N) + j] = s.charAt(j);
			}
		}
		dfs(0, 0, 0);
		System.out.println(result);
	}

	static void dfs(int val, int cnt, int scnt) {
		if (cnt == 7) {
			if (scnt < 4)
				return;
			int realcnt = 0;
			sevenxy = new boolean[N + 1][N + 1];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (sevenxy[i][j])
						continue;
					if (check[i][j]) {
						Queue<Miro> q = new LinkedList<>();
						q.add(new Miro(i, j));
						while (!q.isEmpty()) {
							Miro miro = q.poll();
							if (sevenxy[miro.y][miro.x])
								continue;
							sevenxy[miro.y][miro.x] = true;
							for (int k = 0; k < 4; k++) {
								if (miro.y + dy[k] < 0 || miro.y + dy[k] > N - 1 || miro.x + dx[k] < 0
										|| miro.x + dx[k] > N - 1)
									continue;
								if (sevenxy[miro.y + dy[k]][miro.x + dx[k]])
									continue;
								if (check[miro.y + dy[k]][miro.x + dx[k]]) {
									q.add(new Miro(miro.y + dy[k], miro.x + dx[k]));
								}
							}
						}
						realcnt++;
					}
				}
			}
			if (realcnt == 1) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						System.out.print(check[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println();
				result++;
			}
			return;
		}
		check[val / 5][val % 5] = true;
		seven[cnt] = map[val / 5][val % 5];
		for (int i = val; i < N * N + 1; i++) {
			if (map[i / 5][i % 5] == 'S') {
				dfs(i + 1, cnt + 1, scnt + 1);
			} else {
				dfs(i + 1, cnt + 1, scnt);
			}
		}
		check[val / 5][val % 5] = false;
	}

	static class Miro {
		int y, x;

		public Miro(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
