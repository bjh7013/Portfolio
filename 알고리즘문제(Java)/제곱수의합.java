package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 제곱수의합 {
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		result = Integer.MAX_VALUE;
		dfs(N, Integer.MAX_VALUE, 0);
		System.out.println(result);
	}

	static void dfs(int N, int before, int cnt) {
		if (N == 0) {
			result = Math.min(result, cnt);
			return;
		}
		int temp = (int) Math.sqrt(N);
		if (temp > before) {
			return;
		}
		if (result < cnt)
			return;
		if (result == Integer.MAX_VALUE) {
			for (int i = 1; i < temp + 1; i++) {
				dfs(N - (i * i), i, cnt + 1);
			}
		} else {
			for (int i = 1; i < temp + 1; i++) {
				if (i > before) {
					continue;
				}
				dfs(N - (i * i), i, cnt + 1);
			}
		}

	}
}
