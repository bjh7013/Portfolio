package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 디저트카페 {
	static int N;
	static int result;
	static Stack<Integer> s = new Stack<>();
	static int[][] map;
	static boolean[] b;
	static boolean[][] check;
	static int[] Bang;
	static int[] dx = { 1, 1, -1, -1 }; // 오위, 오아, 왼아, 왼위
	static int[] dy = { -1, 1, 1, -1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N + 2][N + 2];
			check = new boolean[N + 2][N + 2];
			b = new boolean[101]; // 숫자 체크
			Bang = new int[4]; // 방향 체크
			result = -1;
			s.clear();
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
//			for (int i = 1; i <= N; i++) {
//				for (int j = 1; j <= N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if ((i == 1 && j == 1) || (i == N && j == N))
						continue;
					dfs(i, j, 0, 0, i, j); // 방향 1,2,3,4다 하자
					dfs(i, j, 0, 1, i, j); // 방향 1,2,3,4다 하자
					dfs(i, j, 0, 2, i, j); // 방향 1,2,3,4다 하자
					dfs(i, j, 0, 3, i, j); // 방향 1,2,3,4다 하자
				}
			}
//			dfs(1, 2, 0, 1, 1, 2);
			System.out.println("#"+tc+" "+result);
		}
	}

	public static void dfs(int starti, int startj, int cnt, int bang, int i, int j) {
		if (b[map[i][j]] && starti == i && startj == j) {
//			System.out.print(i + " " + j + "    ");
//			System.out.println("hi");
			result = Math.max(result, cnt);
			return;
		}
		if (b[map[i][j]])
			return;
		if (i == 0 || j == 0 || i == N + 1 || j == N + 1)
			return;
		if (Bang[bang] == 1)
			return;
		b[map[i][j]] = true;
//		System.out.print(map[i][j]+" ");
//		System.out.print(i + " " + j + "    ");
		
//		for (int m = 1; m <= N; m++) {
//			for (int n = 1; n <= N; n++) {
//				if(check[m][n])
//					continue;
//				check[m][n]=true;
//
//			}
//		}
		
		for (int k = 0; k < 4; k++) {
			if(k%2==bang%2 && k!=bang)
				continue;
			if (bang == k) {
				
//				if (map[i + dy[k]][j + dx[k]] > 0) {

					dfs(starti, startj, cnt + 1, k, i + dy[k], j + dx[k]);
					
//				}
//				if (map[i + dy[k]][j + dx[k]] > 0) {
					
					Bang[k] = 1;
					dfs(starti, startj, cnt + 1, k, i + dy[k], j + dx[k]);
					dfs(starti, startj, cnt + 1, (k % 2) == 0 ? 1 : 0, i + dy[(k % 2) == 0 ? 1 : 0],
							j + dx[(k % 2) == 0 ? 1 : 0]);
					dfs(starti, startj, cnt + 1, (k % 2) == 0 ? 3 : 2, i + dy[(k % 2) == 0 ? 3 : 2],
							j + dx[(k % 2) == 0 ? 3 : 2]);
					b[map[i][j]] = false;
					Bang[k] = 0;
//				}
			}
		}
		b[map[i][j]] = false;
	}
}
