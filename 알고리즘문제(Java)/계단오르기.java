package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 계단오르기 {
	static int[] stair;
	static int[] map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		stair = new int[N + 1];
		map = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			stair[i] = sc.nextInt();
		}
		System.out.println(dfs(N));
	}

	static int dfs(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return stair[n];
		}
		if (n == 2) {
			return Math.max(stair[n] + stair[n - 1], stair[n]);
		}
		if(map[n]!=0) {
			return map[n];
		}
		return map[n] = Math.max(stair[n] + stair[n - 1] + dfs(n - 3), stair[n] + dfs(n - 2));
	}
}
