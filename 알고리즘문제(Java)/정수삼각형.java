package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 정수삼각형 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// N = 5
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				map[i - 1][j] = Math.max(map[i][j] + map[i - 1][j], map[i][j + 1] + map[i - 1][j]);
			}
		}
		System.out.println(map[0][0]);
	}
}
