//정올 1329
package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Othello {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int TC = 0; TC < T; TC++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N + 2][N + 2];

			for (int i = N / 2; i < N / 2 + 2; i++) {
				for (int j = N / 2; j < N / 2 + 2; j++) {
					if (i == N / 2) {
						if (j == N / 2) {
							map[i][j] = 2;
						}
						if (j != N / 2) {
							map[i][j] = 1;
						}
					}
					if (i != N / 2) {
						if (j == N / 2) {
							map[i][j] = 1;
						}
						if (j != N / 2) {
							map[i][j] = 2;
						}
					}

				}
			}

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			for (int i = 0; i < M; i++) {
				int cnt = 0;
				int x = sc.nextInt();
				int y = sc.nextInt();
				int stone = sc.nextInt();
				if (stone == 1) {
					if (map[y][x - 1] == 2) {
						if (map[y][x - 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y][x - l] == 0)
									break;
								if (map[y][x - l] == 1) {
									cnt++;
									break;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y][x - l] = 1;
							}
						}
					}
					if (map[y][x + 1] == 2) {
						if (map[y][x + 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y][x + l] == 0)
									break;
								if (map[y][x + l] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y][x + l] = 1;
							}
						}
					}
					if (map[y + 1][x] == 2) {
						if (map[y + 2][x] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y+l][x] == 0)
									break;
								if (map[y+l][x] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y+l][x] = 1;
							}
						}
					}
					if (map[y - 1][x] == 2) {
						if (map[y - 2][x] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y-l][x] == 0)
									break;
								if (map[y-l][x] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y-l][x] = 1;
							}
						}
					}
					if (map[y - 1][x - 1] == 2) {
						if (map[y - 2][x - 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y-l][x - l] == 0)
									break;
								if (map[y-l][x - l] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y-l][x - l] = 1;
							}
						}
					}
					if (map[y + 1][x - 1] == 2) {
						if (map[y + 2][x - 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y+l][x - l] == 0)
									break;
								if (map[y+l][x - l] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y+l][x - l] = 1;
							}
						}
					}
					if (map[y - 1][x + 1] == 2) {
						if (map[y - 2][x + 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y-l][x + l] == 0)
									break;
								if (map[y-l][x + l] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y-l][x + l] = 1;
							}
						}
					}
					if (map[y + 1][x + 1] == 2) {
						if (map[y + 2][x + 2] == 1) {
							for (int l = 2; l < x; l++) {
								if (map[y+l][x + l] == 0)
									break;
								if (map[y+l][x + l] == 1) {
									cnt++;
								}
							}
							map[y][x] = 1;
							for (int l = 0; l < cnt; l++) {
								map[y+l][x + l] = 1;
							}
						}
					}

				}
				if (stone == 2) {
					if (map[y][x - 1] == 1) {
						if (map[y][x - 2] == 2) {
							map[y][x] = 2;
							map[y][x - 1] = 2;
						}
					}
					if (map[y][x + 1] == 1) {
						if (map[y][x + 2] == 2) {
							map[y][x] = 2;
							map[y][x + 1] = 2;
						}
					}
					if (map[y + 1][x] == 1) {
						if (map[y + 2][x] == 2) {
							map[y][x] = 2;
							map[y + 1][x] = 2;
						}
					}
					if (map[y - 1][x] == 1) {
						if (map[y - 2][x] == 2) {
							map[y][x] = 2;
							map[y - 1][x] = 2;
						}
					}
					if (map[y - 1][x - 1] == 1) {
						if (map[y - 2][x - 2] == 2) {
							map[y][x] = 2;
							map[y - 1][x - 1] = 2;
						}
					}
					if (map[y + 1][x - 1] == 1) {
						if (map[y + 2][x - 2] == 2) {
							map[y][x] = 2;
							map[y + 1][x - 1] = 2;
						}
					}
					if (map[y - 1][x + 1] == 1) {
						if (map[y - 2][x + 2] == 2) {
							map[y][x] = 2;
							map[y - 1][x + 1] = 2;
						}
					}
					if (map[y + 1][x + 1] == 1) {
						if (map[y + 2][x + 2] == 2) {
							map[y][x] = 2;
							map[y + 1][x + 1] = 2;
						}
					}
				}
				for (int k = 1; k < N + 1; k++) {
					for (int j = 1; j < N + 1; j++) {
						System.out.print(map[k][j]);
					}
					System.out.println();
				}
				System.out.println();
			}
			int wcnt = 0;
			int bcnt = 0;

			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] == 1)
						bcnt++;
					if (map[i][j] == 2)
						wcnt++;
				}
			}
//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < N + 1; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			System.out.println(bcnt + " " + wcnt);

		}
	}
}
