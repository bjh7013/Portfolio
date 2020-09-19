package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 준환이의양팔저울 {
	static int N;
	static int[] bell;
	static int[] bellsave;
	static int result = 0;
	static boolean[] check;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			bell = new int[N];
			bellsave = new int[N];
			check = new boolean[N];
			result = 0;
			for (int i = 0; i < N; i++) {
				bell[i] = sc.nextInt();

			}
			dfs2(0);
			System.out.println("#" + tc + " " + result);
		}
	}

	static void dfs(int count, int left, int right) {
		if (left < right)
			return;
		if (count == N) {
			if (left >= right)
				result++;
			return;
		}

		dfs(count + 1, left, right + bellsave[count]);
		dfs(count + 1, left + bellsave[count], right);

	}

	static void dfs2(int count) {
		if (count == N) {
			dfs(0, 0, 0);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (check[i])
				continue;
			check[i] = true;
			bellsave[count] = bell[i];
			dfs2(count + 1);
			check[i] = false;

		}
	}
}
