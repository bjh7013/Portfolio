package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 파핑파핑지뢰찾기 {
	static int N;
	static char[][] map;
	static char[][] mapcopy;
	static boolean[][] b;
	static boolean[][] btwo;
	static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 }; // 상 하 좌 우 상좌 상우 하좌 하우
	static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int realcnt = 0;
//	static Queue<BFS> q = new LinkedList<>();

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			realcnt = 0;
			map = new char[N + 2][N + 2];
			b = new boolean[N + 2][N + 2];
			btwo = new boolean[N + 2][N + 2];
			String s = "";
			for (int i = 1; i < N + 1; i++) {
				s = sc.next();
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = s.charAt(j - 1);
				}
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] == '.') {
						int temp = 0;
						for (int k = 0; k < 8; k++) {
							if (map[i + dy[k]][j + dx[k]] != '*')
								continue;
							temp++;
						}
						map[i][j] = (char) (48 + temp);
					}
				}
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] == '0' && !b[i][j]) {
						dfs(i, j);
						realcnt++;
					}

				}
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] != '0' && map[i][j] != '\u0000' && map[i][j] != '*' && !b[i][j]) {
						realcnt++;
					}

				}
			}
//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < N + 1; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			System.out.println("#" + tc + " " + realcnt);
		}
	}

	static void dfs(int i, int j) {
		b[i][j] = true;
		for (int k = 0; k < 8; k++) {
			if (map[i + dy[k]][j + dx[k]] == '0' && !b[i + dy[k]][j + dx[k]]) {
				dfs(i + dy[k], j + dx[k]);
			} else if (map[i + dy[k]][j + dx[k]] != '\u0000' && map[i + dy[k]][j + dx[k]] != '*' && !b[i + dy[k]][j + dx[k]]) {
				b[i + dy[k]][j + dx[k]] = true;
			}
		}
	}
//	static void bfs(int i, int j) {
//		Queue<BFS> q = new LinkedList<>();
//		q.offer(new BFS(i, j));
//		BFS go;
//		int tempcnt = 0;
//		b[i][j] = true;
//		while (!q.isEmpty()) {
//			go = q.poll();
//			tempcnt = 0;
//			realcnt++;
//			for (int k = 0; k < 8; k++) {
//				if (map[go.i + dy[k]][go.j + dx[k]] == '*')
//					tempcnt++;
//			}
//			if (tempcnt == 0) {
//				btwo[go.i][go.j] = true;
//				for (int k = 0; k < 8; k++) {
//					if (map[go.i + dy[k]][go.j + dx[k]] != '\u0000') {
//						if (!btwo[go.i + dy[k]][go.j + dx[k]]) {
//							realcnt--;
//							btwo[go.i + dy[k]][go.j + dx[k]] = true;
//						}
//					}
//					if (b[go.i + dy[k]][go.j + dx[k]])
//						continue;
//					if (map[go.i + dy[k]][go.j + dx[k]] != '\u0000') {
//						q.offer(new BFS(go.i + dy[k], go.j + dx[k]));
//						b[go.i + dy[k]][go.j + dx[k]] = true;
//
//					}
//				}
//			}
//			if (tempcnt > 0) {
//				for (int k = 0; k < 8; k++) {
//					if (btwo[go.i + dy[k]][go.j + dx[k]])
//						continue;
//					if (b[go.i + dy[k]][go.j + dx[k]])
//						continue;
//					if (map[go.i + dy[k]][go.j + dx[k]] == '*')
//						continue;
//					if (map[go.i + dy[k]][go.j + dx[k]] == '\u0000')
//						continue;
//					q.offer(new BFS(go.i + dy[k], go.j + dx[k]));
//					b[go.i + dy[k]][go.j + dx[k]] = true;
//				}
//			}
//
//			map[go.i][go.j] = (char) (48 + tempcnt);
//		}
//	}
//
//	static class BFS {
//		int i, j;
//
//		public BFS(int i, int j) {
//			super();
//			this.i = i;
//			this.j = j;
//		}
//	}
}
