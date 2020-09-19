package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 스티커 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt();
			int[][] map = new int[2][N + 2];
			int[][] newmap = new int[2][N + 2];
			for (int i = 0; i < 2; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			newmap[0][1] = map[0][1];
			newmap[1][1] = map[1][1];
			for (int j = 2; j < N + 1; j++) {
				for (int i = 0; i < 2; i++) {
					if (i == 0) { // 위
						newmap[i][j] = Math.max(map[i][j] + newmap[i + 1][j - 1], map[i][j] + newmap[i + 1][j - 2]);
					} else {
						newmap[i][j] = Math.max(map[i][j] + newmap[i - 1][j - 1], map[i][j] + newmap[i - 1][j - 2]);
					}
				}
			}
			int result = Math.max(newmap[0][N], newmap[1][N]);
			System.out.println(result);
		}
	}
}
