package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 활주로건설 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();// 맵크기 N
			int X = sc.nextInt();// 경사로길이X
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				int cnt = 0;
				for (int j = 1; j < N; j++) {
					if (cnt == 0 && map[i][j - 1] != map[i][j]) {
						cnt++;
						continue;
					}
					if (cnt > 0 && map[i][j - 1] < map[i][j]) {
						break;
					}
					if(cnt==X) {
						
					}
					if (cnt > 0 && map[i][j - 1] == map[i][j]) {
						cnt++;
					}
				}
			}
		}

	}
}
