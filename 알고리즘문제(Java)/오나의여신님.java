package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 오나의여신님 {
	static int N;
	static int M;
	static char[][] map;
	static int[][] db;
	static int[][] mb;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N + 2][M + 2];
			db = new int[N + 2][M + 2];
			mb = new int[N + 2][M + 2];
			String[] s = new String[N];

			for (int i = 0; i < s.length; i++) {
				s[i] = sc.next();
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					map[i][j] = s[i - 1].charAt(j - 1);
					db[i][j] = -1;
					mb[i][j] = -2;
				}
			}

//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < M + 1; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			int a = sol();
			if (a == -1) {
				System.out.println("#" + tc + " GAME OVER");
			} else {
				System.out.println("#" + tc + " " + a);
			}

		}
	}

	static int sol() {
		Queue<BFS> q = new LinkedList<>();

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (map[i][j] == '*') {
					q.offer(new BFS(i, j, 0, 0));
				}
				if (map[i][j] == 'S') {
					q.offer(new BFS(i, j, 0, 1));
				}
			}
		}
		BFS bfs = null;
		while (!q.isEmpty()) {
			bfs = q.poll();
//			if (bfs.type == 0) { // -1이 악마 -2 나
//				for (int i = 1; i < N + 1; i++) {
//					for (int j = 1; j < M + 1; j++) {
//						System.out.print(db[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
//			if (bfs.type == 1) {
//				for (int i = 1; i < N + 1; i++) {
//					for (int j = 1; j < M + 1; j++) {
//						System.out.print(mb[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println();
//			}
			if (db[bfs.i][bfs.j] <= mb[bfs.i][bfs.j] && db[bfs.i][bfs.j]!=-1) {
//			System.out.print(bfs.i+" "+bfs.j+"    ");
				continue;
//				return -1;
			}
			for (int i = 0; i < 4; i++) {
				if (bfs.i + dy[i] == 0 || bfs.j + dx[i] == 0 || bfs.i + dy[i] == N + 1 || bfs.j + dx[i] == M + 1) {
					continue;
				}
				if (bfs.type == 0) { // 악마일때
					if (db[bfs.i + dy[i]][bfs.j + dx[i]] > 0) {
						continue;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == 'D') {
						continue;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == 'X') {
						continue;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == '.' || map[bfs.i + dy[i]][bfs.j + dx[i]] == 'S') {
//						System.out.print(bfs.i + " " + bfs.j);
						q.offer(new BFS(bfs.i + dy[i], bfs.j + dx[i], bfs.time + 1, 0));
						db[bfs.i + dy[i]][bfs.j + dx[i]] = bfs.time + 1;
						continue;
					}
				}
				if (bfs.type == 1) {// 사람일때
					if (mb[bfs.i + dy[i]][bfs.j + dx[i]] > 0)
						continue;
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == 'D') {
						return bfs.time + 1;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == 'X') {
						continue;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == '*') {
						continue;
					}
					if (map[bfs.i + dy[i]][bfs.j + dx[i]] == '.') {
						q.offer(new BFS(bfs.i + dy[i], bfs.j + dx[i], bfs.time + 1, 1));
						mb[bfs.i + dy[i]][bfs.j + dx[i]] = bfs.time + 1;
						continue;
					}
				}
			}
		}
		return -1;
	}

	static class BFS {
		int i, j, time, type; // 0이면 악마 1이면 자신

		public BFS(int i, int j, int time, int type) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
			this.type = type;
		}

	}
}
