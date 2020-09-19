package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 달이차오른다가자 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 세로
		int M = sc.nextInt(); // 가로
		int result = -1;
		char[][] map = new char[N + 2][M + 2];
		int[][] memo = new int[N + 2][M + 2];
		boolean[][][] check = new boolean[N + 2][M + 2][65];
		int[][] memokey = new int[N + 2][M + 2];
		Queue<Miro> q = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			String s = sc.next();
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = s.charAt(j - 1);
				if (map[i][j] == '0') {
					q.add(new Miro(i, j, 0, 0));
				}
			}
		}

		while (!q.isEmpty()) {
			Miro miro = q.poll();
			if (map[miro.y][miro.x] == '1') {
				result = miro.cnt;
				break;
			}
			if (check[miro.y][miro.x][miro.key])
				continue;
			check[miro.y][miro.x][miro.key] = true;
//			System.out.println(miro.y + " " + miro.x + " " + miro.cnt);
			for (int k = 0; k < 4; k++) {
				if (miro.y + dy[k] < 1 || miro.y + dy[k] > N || miro.x + dx[k] < 1 || miro.x + dx[k] > M)
					continue;
				if (map[miro.y + dy[k]][miro.x + dx[k]] == '#') {
					continue;
				}
				if (map[miro.y + dy[k]][miro.x + dx[k]] == '.' || map[miro.y + dy[k]][miro.x + dx[k]] == '0'
						|| map[miro.y + dy[k]][miro.x + dx[k]] == '1') {
					q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
					continue;
				}
				if (map[miro.y + dy[k]][miro.x + dx[k]] == 'a' || map[miro.y + dy[k]][miro.x + dx[k]] == 'b'
						|| map[miro.y + dy[k]][miro.x + dx[k]] == 'c' || map[miro.y + dy[k]][miro.x + dx[k]] == 'd'
						|| map[miro.y + dy[k]][miro.x + dx[k]] == 'e' || map[miro.y + dy[k]][miro.x + dx[k]] == 'f') { // 열쇠
					if (map[miro.y + dy[k]][miro.x + dx[k]] == 'a') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 1, miro.cnt + 1));
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'b') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 2, miro.cnt + 1));
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'c') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 4, miro.cnt + 1));
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'd') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 8, miro.cnt + 1));
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'e') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 16, miro.cnt + 1));
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'f') {
						q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key | 32, miro.cnt + 1));
					}
					q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
					continue;
				}
				if (map[miro.y + dy[k]][miro.x + dx[k]] == 'A' || map[miro.y + dy[k]][miro.x + dx[k]] == 'B'
						|| map[miro.y + dy[k]][miro.x + dx[k]] == 'C' || map[miro.y + dy[k]][miro.x + dx[k]] == 'D'
						|| map[miro.y + dy[k]][miro.x + dx[k]] == 'E' || map[miro.y + dy[k]][miro.x + dx[k]] == 'F') { // 잠긴문
					if (map[miro.y + dy[k]][miro.x + dx[k]] == 'A') {
						if ((miro.key & 1) == 1) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'B') {
						if ((miro.key & 2) == 2) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'C') {
						if ((miro.key & 4) == 4) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'D') {
						if ((miro.key & 8) == 8) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'E') {
						if ((miro.key & 16) == 16) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					} else if (map[miro.y + dy[k]][miro.x + dx[k]] == 'F') {
						if ((miro.key & 32) == 32) {
							q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.key, miro.cnt + 1));
						}
						continue;
					}
					continue;
				}
			}
		}
		System.out.println(result);
	}

	static class Miro {
		int y, x, key, cnt;

		public Miro(int y, int x, int key, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.key = key;
			this.cnt = cnt;
		}

		public Miro() {
			super();
			// TODO Auto-generated constructor stub
		}

	}
}
