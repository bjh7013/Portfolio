package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class 달리기 {
	static int N;
	static int M;
	static int count = 0;
	static int[] needs;
	static long[] memo;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			count = 0;
			memo = new long[(1<<N)];
			needs = new int[N];
			for (int i = 0; i < M; i++) {
				int f = sc.nextInt() - 1;
				int s = sc.nextInt() - 1;
				needs[f] |= (1 << s);
			}
			long r = dfs(0);
			System.out.println("#" + tc + " " + r);

		}

	}

	static long dfs(int flag) {
		if (flag == (1 << N) - 1) {
//			count++;
			return 1;
		}
		if(memo[flag]>0) {
			return memo[flag];
		}
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) == 0) {
				if ((flag & needs[i]) == needs[i]) {
					memo[flag]+=dfs(flag | 1 << i);
				}
			}
		}
		return memo[flag];
	}
}
