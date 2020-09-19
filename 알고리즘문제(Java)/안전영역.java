package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 안전영역 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		int result = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		Queue<Safe> q = new LinkedList<>();

		for (int l = 0; l < 101; l++) {
			q.clear();
			int tempcnt=0;
			boolean[][] check = new boolean[N + 2][N + 2];
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if(check[i][j])
						continue;
					if (map[i][j] > l) {
						q.add(new Safe(i, j));
						tempcnt++;
						while (!q.isEmpty()) {
							Safe safe = q.poll();
							if (check[safe.y][safe.x])
								continue;
							check[safe.y][safe.x] = true;
							for (int k = 0; k < 4; k++) {
								if(safe.y+dy[k] < 1 || safe.y+dy[k] > N || safe.x+dx[k] < 1 || safe.x+dx[k] > N)
									continue;
								if (map[safe.y + dy[k]][safe.x + dx[k]] > l)
									q.add(new Safe(safe.y + dy[k], safe.x + dx[k]));
							}
						}
					}
				}
			}
			result = Math.max(result, tempcnt);

		}
		System.out.println(result);

	}

	static class Safe {
		int y, x;

		public Safe(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
}
