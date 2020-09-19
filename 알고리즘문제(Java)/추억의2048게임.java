package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 추억의2048게임 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		// 해당 방향에서 같은 숫자의 타일이 있으면 합친다.
		// 합쳐진것과 뒤에 밀려오는 숫자가 같으면 안합친다.
		// 홀수개가 같은 숫자가 있으면 해당방향에 먼저 닿는것먼저 처리!
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			String s = sc.next();
			map = new int[N + 2][N + 2];
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			if (s.equals("up")) {
				for (int j = 1; j < N + 1; j++) {
					for (int i = 1; i < N + 1; i++) {
						int tempzero = i + 1;
						boolean check = false;
						for (int k = i + 1; k < N + 1; k++) {
							if (map[i][j] == map[k][j] && map[i][j] != 0) {
								map[i][j] *= 2;
								map[k][j] = 0;
								break;
							}
							if (map[i][j] == 0 && map[k][j] != 0) {
								map[i][j] = map[k][j];
								map[k][j] = 0;
								i--;
								break;
							}
							if (map[i][j] != 0 && map[k][j] == 0) {
								if (check) {
//									continue;
								} else {
									tempzero = k;
									check = true;
								}
								continue;
							}
							if (map[i][j] != 0 && map[k][j] != 0) {
								map[tempzero][j] = map[k][j];
								if (tempzero != k)
									map[k][j] = 0;
								break;
							}
							if (map[i][j] == 0 && map[k][j] == 0) {
								tempzero = i;
							}
						}
					}
				}
			} else if (s.equals("down")) {
				for (int j = 1; j < N + 1; j++) {
					for (int i = N; i > 0; i--) {
						int tempzero = i - 1;
						boolean check = false;
						for (int k = i - 1; k > 0; k--) {
							if (map[i][j] == map[k][j] && map[i][j] != 0) {
								map[i][j] *= 2;
								map[k][j] = 0;
								break;
							}
							if (map[i][j] == 0 && map[k][j] != 0) {
								map[i][j] = map[k][j];
								map[k][j] = 0;
								i++;
								break;
							}
							if (map[i][j] != 0 && map[k][j] == 0) {
								if (check) {
//									continue;
								} else {
									tempzero = k;
									check = true;
								}
								continue;
							}
							if (map[i][j] != 0 && map[k][j] != 0) {
								map[tempzero][j] = map[k][j];
								if (tempzero != k)
									map[k][j] = 0;
								break;
							}
							if (map[i][j] == 0 && map[k][j] == 0) {
								tempzero = i;
							}
						}
					}
				}
			} else if (s.equals("left")) {
				for (int i = 1; i < N + 1; i++) {
					for (int j = 1; j < N + 1; j++) {
						int tempzero = j + 1;
						boolean check = false;
						for (int k = j + 1; k < N + 1; k++) {
							if (map[i][j] == map[i][k] && map[i][j] != 0) {
								map[i][j] *= 2;
								map[i][k] = 0;
								break;
							}
							if (map[i][j] == 0 && map[i][k] != 0) {
								map[i][j] = map[i][k];
								map[i][k] = 0;
								j--;
								break;
							}
							if (map[i][j] != 0 && map[i][k] == 0) {
								if (check) {
//									continue;
								} else {
									tempzero = k;
									check = true;
								}
								continue;
							}
							if (map[i][j] != 0 && map[i][k] != 0) {
								map[i][tempzero] = map[i][k];
								if (tempzero != k)
									map[i][k] = 0;
								break;
							}
							if (map[i][j] == 0 && map[i][k] == 0) {
								tempzero = j;
							}
						}
					}
				}
			} else {
				for (int i = 1; i < N + 1; i++) {
					for (int j = N; j > 0; j--) {
						int tempzero = j - 1;
						boolean check = false;
						for (int k = j - 1; k > 0; k--) {
							if (map[i][j] == map[i][k] && map[i][j] != 0) {
								map[i][j] *= 2;
								map[i][k] = 0;
								break;
							}
							if (map[i][j] == 0 && map[i][k] != 0) {
								map[i][j] = map[i][k];
								map[i][k] = 0;
								j++;
								break;
							}
							if (map[i][j] != 0 && map[i][k] == 0) {
								if (check) {
//									continue;
								} else {
									tempzero = k;
									check = true;
								}
								continue;
							}
							if (map[i][j] != 0 && map[i][k] != 0) {
								map[i][tempzero] = map[i][k];
								if (tempzero != k)
									map[i][k] = 0;
								break;
							}
							if (map[i][j] == 0 && map[i][k] == 0) {
								tempzero = j;
							}
						}
					}
				}
			}
			System.out.println("#"+tc);
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
