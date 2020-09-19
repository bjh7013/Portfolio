package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 보호필름 {
	static int D;
	static int W;
	static int K;
	static int[][] map;
	static char[] check;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();// 세로길이두께
			W = sc.nextInt();// 가로길이
			K = sc.nextInt();// 합격기준연속K
			map = new int[D][W];
			check = new char[D + 1];
			result = Integer.MAX_VALUE;
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			if (K == 1) {
				System.out.println("#" + tc + " 0");
				continue;
			}
			dfs(0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}

	static void dfs(int cnt, int tempcnt) {
		if (cnt == D) {
			int count = 1;
			int cur, next;
			for (int j = 0; j < W; j++) {
				count = 1;
				for (int i = 0; i < D - 1; i++) {
					cur = map[i][j];
					next = map[i + 1][j];
					if (check[i] > 0) {
						cur = check[i] - 1;
					}
					if (check[i + 1] > 0) {
						next = check[i + 1] - 1;
					}
					if (cur == next) {
						count++;
						if (count >= K)
							break;
					} else {
						count = 1;
					}
				}
				if (count < K)
					return;
			}
			result = Math.min(result, tempcnt);
			return;
		}
		if (tempcnt >= result)
			return;
		check[cnt] = 0; // 아무것도 x
		dfs(cnt + 1, tempcnt);
		check[cnt] = 2; // 다 B로
		dfs(cnt + 1, tempcnt + 1);
		check[cnt] = 1; // 다 A로
		dfs(cnt + 1, tempcnt + 1);
	}
}
