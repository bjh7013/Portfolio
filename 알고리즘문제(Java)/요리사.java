package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 요리사 {
	static int N;
	static int[][] map;
	static boolean[] b;
	static int start = 0;
	static int link = 0;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			map = new int[N + 2][N + 2];
			b = new boolean[N + 2];
			start = 0;
			link = 0;
			result = Integer.MAX_VALUE;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			dfs(0, 0);
			System.out.println("#" + tc + " " + result);
		}
	}

	static void dfs(int num, int cnt) {
		if (cnt == N / 2) {
			Stack<Integer> s1 = new Stack<>();
			Stack<Integer> s2 = new Stack<>();
			for (int i = 1; i < N + 1; i++) {
				if (b[i])
					s1.push(i);
				if (!b[i])
					s2.push(i);
			}
			start = 0;
			link = 0;
			for (int i = 0; i < s1.size(); i++) {
				for (int j = i + 1; j < s2.size(); j++) {
					int starttemp1 = s1.get(i);
					int starttemp2 = s1.get(j);
					int linktemp1 = s2.get(i);
					int linktemp2 = s2.get(j);
					start += map[starttemp1][starttemp2] + map[starttemp2][starttemp1];
					link += map[linktemp1][linktemp2] + map[linktemp2][linktemp1];
				}
			}
			result = Math.min(result, Math.abs(start - link));
			return;
		}
		for (int i = num+1; i < N + 1; i++) {
			if (b[i])
				continue;
			b[i] = true;
			dfs(i, cnt + 1);
			b[i] = false;
		}
	}
}
