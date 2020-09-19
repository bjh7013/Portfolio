package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽돌깨기 {
	static int N;
	static int W;
	static int H;
	static int[] P;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] maptemp;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			W = sc.nextInt();
			H = sc.nextInt();
			result = Integer.MAX_VALUE;
			map = new int[H + 2][W + 2];
			P = new int[N + 1];
			maptemp = new int[H + 2][W + 2];
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			pdfs(0);
			System.out.println("#" + tc + " " + result);

		}
	}

	static void pdfs(int cnt) {
		if (cnt == N) {
			for (int i = 1; i <= H; i++) {
				maptemp[i] = map[i].clone();
			}
			for (int i = 0; i < N; i++) {
				crash(P[i]);
			}
			int solcnt = 0;
			for (int i = 1; i <= H; i++) {
				for (int j = 1; j <= W; j++) {
					if (maptemp[i][j] > 0) {
						solcnt++;
					}
				}
			}
			result = Math.min(result, solcnt);
			return;
		}
		for (int i = 1; i < W + 1; i++) {
			P[cnt] = i;
			pdfs(cnt + 1);
		}
	}

	static void crash(int loc) {
		Queue<Block> q = new LinkedList<>();
		for (int i = 0; i < H + 1; i++) {
			if (maptemp[i][loc] > 0) {
				q.offer(new Block(i, loc, maptemp[i][loc]));
				break;
			}
		}
		Block block = null;
		while (!q.isEmpty()) {
			block = q.poll();
			maptemp[block.i][block.j] = 0;

			for (int i = 0; i < 4; i++) {
				if (block.i == 0 || block.j == 0 || block.i == H + 1 || block.j == W + 1)
					continue;
				if (block.num == 1)
					continue;
				for (int j = 1; j < block.num; j++) {
					if (block.i + dy[i] * j == 0 || block.i + dy[i] * j == H + 1 || block.j + dx[i] * j == 0
							|| block.j + dx[i] * j == W + 1)
						break;
					if (maptemp[block.i + dy[i] * j][block.j + dx[i] * j] == 0)
						continue;
					q.offer(new Block(block.i + dy[i] * j, block.j + dx[i] * j,
							maptemp[block.i + dy[i] * j][block.j + dx[i] * j]));
					maptemp[block.i + dy[i] * j][block.j + dx[i] * j] = 0;
				}
			}
		}
		for (int j = 1; j <= W; j++) {
			All: for (int i = H; i > 0; i--) {
				if (maptemp[i][j] == 0) {
					for (int k = 1; k < i; k++) {
						if (maptemp[i - k][j] > 0) {
							maptemp[i][j] = maptemp[i - k][j];
							maptemp[i - k][j] = 0;
							break;
						}
						if (k == i - 1) {
							break All;
						}
					}
				}

			}
		}
	}

	static class Block {
		int i, j, num;

		public Block(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}
	}
}
