package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 미세먼지안녕 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int R = sc.nextInt(); // 행
		int C = sc.nextInt(); // 열
		int T = sc.nextInt(); // 시간
		int[][][] map = new int[R][C][2];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j][0] = sc.nextInt();
			}
		}

		for (int t = 0; t < T; t++) {
			for (int i = 0; i < R; i++) { // 1번 미세먼지 확산
				for (int j = 0; j < C; j++) {
					if (map[i][j][t % 2] == -1) {
						map[i][j][(t + 1) % 2] = -1;
						continue;
					}
					if (map[i][j][t % 2] > 0) {
						int cnt = 0;
						for (int k = 0; k < 4; k++) {
							if (i + dy[k] < 0 || i + dy[k] > R - 1 || j + dx[k] < 0 || j + dx[k] > C - 1)
								continue;
							if (map[i + dy[k]][j + dx[k]][0] == -1) {
								continue;
							}
							if (map[i + dy[k]][j + dx[k]][1] == -1) {
								continue;
							}
							map[i + dy[k]][j + dx[k]][(t + 1) % 2] += (map[i][j][t % 2] / 5);
							cnt++;
						}
						map[i][j][(t + 1) % 2] += (map[i][j][t % 2] - ((map[i][j][t % 2] / 5) * cnt));
					}
				}
			}

//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j][(t + 1) % 2] + " ");
//				}
//				System.out.println();
//			}
//
//			System.out.println();
			boolean check = false;
			for (int i = 0; i < R; i++) { // 2번 공기청정기 작동
				if (!check && map[i][0][0] == -1) {
					check = true;
					// 위 공기청정기
					for (int j = i - 1; j > 0; j--) {
						map[j][0][(t + 1) % 2] = map[j - 1][0][(t + 1) % 2];
					}
					for (int j = 0; j < C - 1; j++) {
						map[0][j][(t + 1) % 2] = map[0][j + 1][(t + 1) % 2];
					}
					for (int j = 0; j < i; j++) {
						map[j][C - 1][(t + 1) % 2] = map[j + 1][C - 1][(t + 1) % 2];
					}
					for (int j = C - 1; j > 1; j--) {
						map[i][j][(t + 1) % 2] = map[i][j - 1][(t + 1) % 2];
					}
					map[i][1][(t + 1) % 2] = 0;
					continue;
				}
				if (check && map[i][0][0] == -1) { // i는 행R
					// 아랫 공기청정기
					for (int j = i + 1; j < R - 1; j++) {
						map[j][0][(t + 1) % 2] = map[j + 1][0][(t + 1) % 2];
					}
					for (int j = 0; j < C - 1; j++) {
						map[R - 1][j][(t + 1) % 2] = map[R - 1][j + 1][(t + 1) % 2];
					}
					for (int j = R - 1; j > i; j--) {
						map[j][C - 1][(t + 1) % 2] = map[j - 1][C - 1][(t + 1) % 2];
					}
					for (int j = C - 1; j > 1; j--) {
						map[i][j][(t + 1) % 2] = map[i][j - 1][(t + 1) % 2];
					}
					map[i][1][(t + 1) % 2] = 0;
				}
			}

//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j][(t + 1) % 2] + " ");
//				}
//				System.out.println();
//			}
//
//			System.out.println();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j][t % 2] = 0;
				}
			}
		}
		int result = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j][T % 2] > 0) {
					result += map[i][j][T % 2];
				}
			}
		}

		System.out.println(result);

	}
}
