package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 구간합구하기5 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		int[][] dpval = new int[N + 2][N + 2];

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				dpval[i][j] = dpval[i - 1][j] + dpval[i][j - 1] - dpval[i - 1][j - 1] + map[i][j];
			}
		}

		for (int i = 0; i < M; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			System.out.println(dpval[x2][y2] - dpval[x1 - 1][y2] - dpval[x2][y1 - 1] + dpval[x1 - 1][y1 - 1]);
		}
	}
}
