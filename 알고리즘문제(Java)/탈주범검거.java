package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 탈주범검거 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 세로
			int M = sc.nextInt(); // 가로
			int R = sc.nextInt(); // 맨홀뚜껑 세로
			int C = sc.nextInt(); // 맨홀뚜껑 가로
			int L = sc.nextInt(); // 탈출 소요시간
			int[] dx = { 0, 0, -1, 1 };
			int[] dy = { -1, 1, 0, 0 };
			boolean[][] check = new boolean[N][M];
			int map[][] = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			Queue<Prison> q = new LinkedList<>();
			q.add(new Prison(R, C, 0, 1));
			while (!q.isEmpty()) {
				Prison prison = q.poll();
				if (prison.cnt == L)
					break;
				if (check[prison.y][prison.x])
					continue;
				check[prison.y][prison.x] = true;
				if (map[prison.y][prison.x] == 1) {
					for (int k = 0; k < 4; k++) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 0 && (map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 7))
							continue;
						if (k == 1 && (map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 6))
							continue;
						if (k == 2 && (map[y][x] == 2 || map[y][x] == 6 || map[y][x] == 7))
							continue;
						if (k == 3 && (map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 5))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 2) {
					for (int k = 0; k < 2; k++) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 0 && (map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 7))
							continue;
						if (k == 1 && (map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 6))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 3) {
					for (int k = 2; k < 4; k++) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 2 && (map[y][x] == 2 || map[y][x] == 6 || map[y][x] == 7))
							continue;
						if (k == 3 && (map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 5))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 4) {
					for (int k = 0; k < 4; k += 3) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 0 && (map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 7))
							continue;
						if (k == 3 && (map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 5))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 5) {
					for (int k = 1; k < 4; k += 2) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 1 && (map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 6))
							continue;
						if (k == 3 && (map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 5))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 6) {
					for (int k = 1; k < 3; k++) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 1 && (map[y][x] == 3 || map[y][x] == 5 || map[y][x] == 6))
							continue;
						if (k == 2 && (map[y][x] == 2 || map[y][x] == 6 || map[y][x] == 7))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				} else if (map[prison.y][prison.x] == 7) {
					for (int k = 0; k < 4; k += 2) {
						int y = prison.y + dy[k];
						int x = prison.x + dx[k];
						if (y < 0 || y > N - 1 || x < 0 || x > M - 1)
							continue;
						if (check[y][x])
							continue;
						if (map[y][x] == 0)
							continue;
						if (k == 0 && (map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 7))
							continue;
						if (k == 2 && (map[y][x] == 2 || map[y][x] == 6 || map[y][x] == 7))
							continue;
						q.add(new Prison(y, x, prison.cnt + 1, map[prison.y][prison.x]));
					}
				}
			}
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j])
						result++;
				}
			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < M; j++) {
//					System.out.print(check[i][j]+" ");
//				}
//				System.out.println();
//			}
			System.out.println("#" + tc + " " + result);

		}
	}

	static class Prison {
		int y, x, cnt, type;

		public Prison(int y, int x, int cnt, int type) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
			this.type = type;
		}

	}
}
