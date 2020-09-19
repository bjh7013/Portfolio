package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NQueen {
	static int sol;
	static int N;
	static boolean[][] check;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		sol = 0;
		for (int i = 1; i < N + 1; i++) {
			int[] col = new int[N + 1];
			col[1] = i;
			dfs(col, 1);
		}
		System.out.println(sol);
	}

	static void dfs(int[] col, int row) {
		if (row == N) {
			sol++;
		} else {
			for (int i = 1; i < N + 1; i++) {
				col[row + 1] = i;
				if (isPossible(col, row + 1)) {
					dfs(col, row + 1);
				}
			}
		}
	}

	static boolean isPossible(int[] col, int row) {
		for (int i = 1; i < row; i++) {
			if (col[i] == col[row])
				return false;
			if (Math.abs(i - row) == Math.abs(col[i] - col[row]))
				return false;
		}
		return true;
	}
}
