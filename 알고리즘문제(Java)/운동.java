package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 운동 {
	static int N;
	static int M;
	static int[] S;
	static int[] E;
	static int[] C;
	static int[][] map;
	static boolean[][] b;
	static int sol = Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 건물의 수
			M = sc.nextInt(); // 도로의 수
			map = new int[N + 2][N + 2];
			b = new boolean[N + 2][N + 2];
			sol = Integer.MAX_VALUE;
			S = new int[M + 1]; // 시작 건물
			E = new int[M + 1]; // 끝 건물
			C = new int[M + 1]; // 도로 길이
			for (int i = 1; i < M + 1; i++) {
				S[i] = sc.nextInt();
				E[i] = sc.nextInt();
				C[i] = sc.nextInt();
				map[S[i]][E[i]] = C[i];
			}
//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < N + 1; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}

			for (int i = 1; i < N + 1; i++) {
				dfs(i, 0, i);
			}

			System.out.print("#" + tc + " ");
			if (sol == Integer.MAX_VALUE) {
				System.out.println("-1");
			} else {
				System.out.println(sol);
			}
		}

	}

	public static void dfs(int start, int cnt, int original) {
		if (start == original && cnt != 0) {
			sol = Math.min(sol, cnt);
			return;
		}
		if (cnt >= sol)
			return;
		for (int i = 1; i < N + 1; i++) {
			if (b[start][i])
				continue;
			if (map[start][i] > 0) {
				b[start][i] = true;
				dfs(i, cnt + map[start][i], original);
				b[start][i] = false;
			}
		}
	}
}
