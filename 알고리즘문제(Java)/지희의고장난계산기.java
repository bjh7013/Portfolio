package testProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 지희의고장난계산기 {
	static int X;
	static int min;
	static boolean[] btn;
	static int[] dp;
	static final int IMPOSSIBLE = Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			min = Integer.MAX_VALUE;
			btn = new boolean[10];
			for (int i = 0; i < 10; i++) {
				int temp = sc.nextInt();
				if (temp == 0)
					btn[i] = false;
				if (temp == 1)
					btn[i] = true;
			}
			int val = sc.nextInt();
			dp = new int[val + 10];
			for (int i = 0; i < 10; i++) {
				if (btn[i])
					dp[i] = 1;
			}
			solve(val);
			System.out.println("#" + tc + " " + (dp[val] == IMPOSSIBLE ? -1 : dp[val] + 1));
		}

	}

	static int solve(int num) {
		if (dp[num] != 0)
			return dp[num];
		dp[num] = count(num);

		for (int i = 1; i <= (int) Math.sqrt(num); i++) {
			if (num % i == 0) {
				int num1 = solve(i);
				int num2 = solve(num / i);
				dp[num] = Math.min(dp[num], num1 == IMPOSSIBLE || num2 == IMPOSSIBLE ? IMPOSSIBLE : num1 + num2 + 1);
			}
		}
		return dp[num];
	}

	static int count(int num) {
		int cnt = 0;

		while (num > 0) {
			int temp = num % 10;
			if (!btn[temp])
				return IMPOSSIBLE;
			num /= 10;
			cnt++;
		}
		return cnt;
	}

}
