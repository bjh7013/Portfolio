package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LongestPath {
	static int N;
	static int M;
	static int[][] map;
	static boolean[] b;
	static int[] v;
	static int cnt = 1;
	static int resulttemp = 0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N + 2][N + 2];
			b = new boolean[N + 2];
			v = new int[N + 2];
			cnt = 1;
			int result = 0;
			resulttemp = 0;
			for (int i = 1; i <= M; i++) {
				int tempi = sc.nextInt();
				int tempj = sc.nextInt();
				map[tempi][tempj] = 1;
				map[tempj][tempi] = 1;
			}
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= N; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			for (int i = 1; i <= N; i++) {
				b = new boolean[N + 2];
				v = new int[N + 2];
				resulttemp = 1;
				dfs(1, i);
				result = Math.max(result, resulttemp);
			}
			System.out.println("#" + tc + " " + result);
		}
	}

	public static void dfs(int len, int i) {
		b[i] = true;

		if (len > resulttemp) {
			resulttemp = len;
		}
		for (int j = 1; j <= N; j++) {
			if (b[j])
				continue;
			if (map[i][j] == 1) {
				dfs(len + 1, j);
			}
		}
		b[i] = false;
		return;
	}
}
