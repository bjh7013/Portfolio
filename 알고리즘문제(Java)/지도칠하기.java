package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 지도칠하기 {
	static int N;
	static int[][] map;
	static int[] color;
	static boolean[] check;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N + 1][N + 1];
			color = new int[N]; // 각 나라의 색상
			check = new boolean[N + 1];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				color[i] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for (int i = 0; i < N; i++) {
				dfs(i, 0, 0);
			}
			System.out.println("#"+tc+" "+result);
		}
	}

	static void dfs(int index, int cnt, int changecnt) {
		if (cnt == N) {
			result = Math.min(result, changecnt);
			return;
		}
		if (check[index])
			return;
		check[index] = true;
		for (int i = 0; i < N; i++) {
			if (map[index][i] == 1) {
				if (color[index] == color[i]) {
					for (int j = 1; j < 5; j++) {
						if (color[index] == j)
							continue;
						color[i] = j;
						dfs(i, cnt + 1, changecnt + 1);
					}
					color[i] = color[index];
				} else {
					dfs(i, cnt + 1, changecnt);
				}
			}
		}
		check[index] = false;
	}
}
