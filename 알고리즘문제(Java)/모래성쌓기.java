package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 모래성쌓기 {
	static int H;
	static int W;
	static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 }; // 상 하 좌 우 좌상 우상 좌하 우하
	static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		Queue<BFS> q = new LinkedList<>();
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			H = sc.nextInt();
			W = sc.nextInt();
			char[][] map = new char[H + 2][W + 2];
			String[] s = new String[H];
			q.clear();
			for (int i = 0; i < H; i++) {
				s[i] = sc.next();
				for (int j = 0; j < W; j++) {
					map[i + 1][j + 1] = s[i].charAt(j);
				}
			}

			int tempcnt = 0;
			for (int i = 2; i < H; i++) {
				for (int j = 2; j < W; j++) {
					tempcnt = 0;
					if (map[i][j] == '9')
						continue;
					if (map[i][j] == '.')
						continue;
					for (int k = 0; k < 8; k++) {
						if (map[i + dy[k]][j + dx[k]] == '.') {
							map[i][j]--;
						}
						if (map[i][j] == '0') {
							q.add(new BFS(i, j, 1));
							break;
						}
					}
				}
			}
			BFS bfs = null;
			while (!q.isEmpty()) {
				bfs = q.poll();
				for (int k = 0; k < 8; k++) {
					if (map[bfs.i + dy[k]][bfs.j + dx[k]] == '.')
						continue;
					if (map[bfs.i + dy[k]][bfs.j + dx[k]] == '9')
						continue;
					if (map[bfs.i + dy[k]][bfs.j + dx[k]] == '0')
						continue;
					map[bfs.i + dy[k]][bfs.j + dx[k]]--;
					if (map[bfs.i + dy[k]][bfs.j + dx[k]] == '0')
						q.add(new BFS(bfs.i + dy[k], bfs.j + dx[k], bfs.cnt + 1));
				}
			}
//			for (int i = 1; i <= H; i++) {
//				for (int j = 1; j <= W; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			System.out.println("#"+tc+" "+bfs.cnt);
		}
	}

	static class BFS {
		int i, j, cnt;

		public BFS(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}
