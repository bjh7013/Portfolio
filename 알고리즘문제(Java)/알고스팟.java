package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 알고스팟 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt();
		int N = sc.nextInt();
		int[][] map = new int[N + 2][M + 2];
		int[][] check = new int[N + 2][M + 2];
		int result = Integer.MAX_VALUE;

		String s = "";
		for (int i = 1; i < N + 1; i++) {
			s = sc.next();
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = ((int) s.charAt(j - 1)) - 48;
			}
		}
		for(int[] a : check) {
			Arrays.fill(a, Integer.MAX_VALUE);
		}
		Queue<Miro> q = new LinkedList<>();
		q.add(new Miro(1, 1, 0));

		while (!q.isEmpty()) {
			Miro miro = q.poll();
			if (miro.y == N && miro.x == M) {
				result = Math.min(result, miro.bcnt);
				continue;
			}
			if (check[miro.y][miro.x] <= miro.bcnt)
				continue;
			check[miro.y][miro.x] = miro.bcnt;

			for (int k = 0; k < 4; k++) {
				if (miro.y + dy[k] < 1 || miro.y + dy[k] > N || miro.x + dx[k] < 1 || miro.x + dx[k] > M)
					continue;
				if (map[miro.y + dy[k]][miro.x + dx[k]] == 0) {
					if (check[miro.y + dy[k]][miro.x + dx[k]] < miro.bcnt)
						continue;
					q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.bcnt));
				}
				if (map[miro.y + dy[k]][miro.x + dx[k]] == 1) {
					if (check[miro.y + dy[k]][miro.x + dx[k]] < miro.bcnt)
						continue;
					q.add(new Miro(miro.y + dy[k], miro.x + dx[k], miro.bcnt + 1));
				}
			}
		}
		System.out.println(result);
	}

	static class Miro {
		int y, x, bcnt;

		public Miro(int y, int x, int bcnt) {
			super();
			this.y = y;
			this.x = x;
			this.bcnt = bcnt;
		}
	}
}
