package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 햄버거다이어트DP {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 재료의 수
			int L = sc.nextInt(); // 총 칼로리
			int[] Ti = new int[N + 1]; // 점수
			int[] Ki = new int[N + 1]; // 칼로리
			int[][] result = new int[N + 1][L + 1];
			for (int i = 1; i < N + 1; i++) {
				Ti[i] = sc.nextInt();
				Ki[i] = sc.nextInt();
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < L + 1; j++) {
					if (i == 1 && j >= Ki[i]) {
						result[i][j] = Ti[i];
					} else if (j >= Ki[i]) {
						result[i][j] = Math.max(result[i - 1][j], result[i - 1][j - Ki[i]] + Ti[i]);
					} else if (j < Ki[i]) {
						result[i][j] = result[i-1][j];
					}
//					System.out.print(result[i][j]+" ");
				}
//				System.out.println();
			}
			System.out.println("#"+tc+" "+result[N][L]);
		}
	}
}
