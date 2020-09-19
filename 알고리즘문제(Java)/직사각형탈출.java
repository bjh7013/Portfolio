package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 직사각형탈출 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int N;
	static int M;
	static int H;
	static int W;
	static int startr;
	static int startc;
	static int finishr;
	static int finishc;
	static int result;
	static int[][] map;
	static boolean[][] b;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		result = -1;
		map = new int[N + 2][M + 2];
		b = new boolean[N + 2][M + 2];
		Queue<Squre> q = new LinkedList<>();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		H = sc.nextInt();
		W = sc.nextInt();
		startr = sc.nextInt();
		startc = sc.nextInt();
		finishr = sc.nextInt();
		finishc = sc.nextInt();
		q.offer(new Squre(startc, startr, startc + W - 1, startr + H - 1, 0)); // x,y
		while (!q.isEmpty()) {
			Squre s = q.poll();
			b[s.y][s.x] = true;
			if (s.x == finishc && s.y == finishr) {
				result = s.cnt;
				break;
			}
//			System.out.println(s.y+" "+s.x);
//			System.out.println(s.y2+" "+s.x2);
//			System.out.println();
			All:for (int i = 0; i < 4; i++) {
				if (b[s.y + dy[i]][s.x + dx[i]])
					continue;
				if (s.x + dx[i] < 1 || s.y + dy[i] < 1 || s.x2 + dx[i] > M || s.y2 + dy[i] > N)
					continue;

				if (i == 0) { // 상
					for (int j = s.x; j < s.x2 + 1; j++) {
						if (map[s.y + dy[i]][j] == 1)
							continue All;
					}
				}
				if (i == 1) { // 하
					for (int j = s.x; j < s.x2 + 1; j++) {
						if (map[s.y2 + dy[i]][j] == 1)
							continue All;
					}
				}
				if (i == 2) { // 좌
					for (int j = s.y; j < s.y2 + 1; j++) {
						if (map[j][s.x+dx[i]] == 1)
							continue All;
					}
				}
				if (i == 3) { // 우
					for (int j = s.y; j < s.y2 + 1; j++) {
						if (map[j][s.x2+dx[i]] == 1)
							continue All;
					}
				}

				b[s.y + dy[i]][s.x + dx[i]] = true;
				q.offer(new Squre(s.x + dx[i], s.y + dy[i], s.x2 + dx[i], s.y2 + dy[i], s.cnt + 1));
			}
			if (q.isEmpty()) {
				result = -1;
			}
		}
		System.out.println(result);
	}

	static class Squre {
		int x;
		int y;
		int x2;
		int y2;
		int cnt;

		public Squre(int x, int y, int x2, int y2, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
		}

		public Squre() {
			super();
			// TODO Auto-generated constructor stub
		}

	}
}
