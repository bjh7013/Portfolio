package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class 동전거스름돈 {
	static int N;
	static int[] D;
	static int[] val;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		D = new int[4];
		D[0] = 3;
		D[1] = 4;
		D[2] = 6;
		D[3] = 11;

		val = new int[N + 1];
		Arrays.fill(val, Integer.MAX_VALUE-1);
		val[0]=0;
		dfs(0);
		System.out.print(val[N]);
	}

	static void dfs(int cnt) {
		if (cnt == D.length) {
			return;
		}
		for (int i = 0; i < N + 1; i++) {
			if (i >= D[cnt])
				val[i] = Math.min(val[i], val[i - D[cnt]] + 1);
			System.out.print(val[i] + " ");
		}
		System.out.println();
		dfs(cnt + 1);
	}
}
