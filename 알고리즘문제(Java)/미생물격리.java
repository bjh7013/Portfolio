package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 미생물격리 {
	static int N;
	static int M;
	static int K;
	static int[][] warm;
	static int[][] map;
	static int[][] check;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= 1; tc++) {
			Queue<BFS> q = new LinkedList<>();
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			warm = new int[N][N]; // 벌레 이동방향
			map = new int[N][N]; // 토탈 맵
			check = new int[N][N]; // 겹치는거 맵
			int sol = 0;
			for (int i = 1; i < K + 1; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				map[a][b] = sc.nextInt();
				q.add(new BFS(sc.nextInt(), a, b, 0, map[a][b]));
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			BFS Q = q.peek();
			while (!q.isEmpty()) {
				BFS temp = Q;
				Q = q.poll();
				if (temp.time != Q.time) {
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							System.out.print(map[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println();
				}
				if (Q.time == M)
					break;
				for (int k = 0; k < 4; k++) {
					if (Q.move == k) {
						map[Q.i + dy[k]][Q.j + dx[k]] += map[Q.i][Q.j];
						
						if (check[Q.i + dy[k]][Q.j + dx[k]] < Q.before) {
							check[Q.i + dy[k]][Q.j + dx[k]] = Q.before;
							warm[Q.i + dy[k]][Q.j + dx[k]] = Q.move;
							q.add(new BFS(warm[Q.i + dy[k]][Q.j + dx[k]], Q.i + dy[k], Q.j + dx[k], Q.time+1, map));
						}
					}
				}

			}
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					sol += map[i][j];
//				}
//			}
//			System.out.println(sol);
		}
	}

	static class BFS {
		int move; // 방향 1 2 3 4 상 하 좌 우
		int i, j; // 좌표
		int time;
		int before;

		public BFS(int move, int i, int j, int time, int before) {
			super();
			this.move = move;
			this.i = i;
			this.j = j;
			this.time = time;
			this.before = before;
		}

	}
}
