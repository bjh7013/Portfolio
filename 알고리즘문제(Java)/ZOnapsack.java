package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ZOnapsack {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt(); // 가방 부피
			int[] V = new int[N + 1]; // 부피
			int[] C = new int[N + 1]; // 가치
			int[][] result = new int[N + 1][K + 1];
			for (int i = 1; i < N + 1; i++) {
				V[i] = sc.nextInt();
				C[i] = sc.nextInt();
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < K + 1; j++) {
					if (i == 1 && j >= V[i]) {
						result[i][j] = C[i];
					} else if (j >= V[i]) {
						result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - V[i]] + C[i]);
					} else if (j < V[i]) {
						result[i][j] = result[i-1][j];
					}
					System.out.print(result[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println("#"+tc+" "+result[N][K]);
		}
	}
}
