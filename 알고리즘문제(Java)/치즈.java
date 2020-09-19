package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 치즈 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		int[][] count = new int[N][M];
		boolean[][] check = new boolean[N][M];
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		Queue<Cheese> q = new LinkedList<>();
		while (true) {
			boolean zero = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						zero = true;
					}
				}
			}
			if (!zero)
				break;
			q.add(new Cheese(0, 0));
			while (!q.isEmpty()) {
				Cheese c = q.poll();
				if (check[c.y][c.x])
					continue;
				check[c.y][c.x] = true;
				for (int k = 0; k < 4; k++) {
					if (c.y + dy[k] < 0 || c.y + dy[k] > N - 1 || c.x + dx[k] < 0 || c.x + dx[k] > M - 1)
						continue;
					if (check[c.y + dy[k]][c.x + dx[k]])
						continue;
					if (map[c.y + dy[k]][c.x + dx[k]] == 1) {
						count[c.y + dy[k]][c.x + dx[k]]++;
					} else {
						q.add(new Cheese(c.y + dy[k], c.x + dx[k]));
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (count[i][j] > 1) {
						map[i][j] = 0;
					}
				}
			}

			q.clear();
			count = new int[N][M];
			check = new boolean[N][M];
			result++;
		}
		System.out.println(result);
	}

	static class Cheese {
		int y, x;

		public Cheese() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Cheese(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
