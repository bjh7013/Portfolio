package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 감시 {
	static int N;
	static int M;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 }; // 상우하좌
	static int[] dy = { -1, 0, 1, 0 };
	static int cntcam;
	static List<Camera> list;
	static int[][] check;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		cntcam = 0;
		result = Integer.MAX_VALUE;
		map = new int[N + 1][M + 1];
		check = new int[N + 1][M + 1];
		list = new ArrayList<>();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0 && map[i][j] < 6) {
					check[i][j]++;
					list.add(new Camera(i, j, map[i][j]));
					cntcam++;
				}
				if (map[i][j] == 6)
					check[i][j]++;
			}
		}
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < M + 1; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		dfs(0);
		System.out.println(result);
	}

	static void dfs(int cnt) {
		if (cnt == cntcam) {
			int tempcnt = 0;
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < M + 1; j++) {
					if (check[i][j] == 0)
						tempcnt++;
				}
			}
//			for (int i = 1; i < N + 1; i++) {
//				for (int j = 1; j < M + 1; j++) {
//					System.out.print(check[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			result = Math.min(result, tempcnt);
			return;
		}
		for (int k = 0; k < 4; k++) {
			int y = list.get(cnt).y;
			int x = list.get(cnt).x;
//			System.out.println((y+dy[k])+" "+(x+dx[k]));
			if (list.get(cnt).type == 1) {
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]++;
				}
				dfs(cnt + 1);
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]--;
				}
			}
			if (list.get(cnt).type == 2) {
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k+2)%4] * i && y + dy[(k+2)%4] * i < N + 1 && 0 < x + dx[(k+2)%4] * i
						&& x + dx[(k+2)%4] * i < M + 1; i++) {
					if (map[y + dy[(k+2)%4] * i][x + dx[(k+2)%4] * i] == 6) {
						break;
					}
					check[y + dy[(k+2)%4] * i][x + dx[(k+2)%4] * i]++;
				}
				dfs(cnt + 1);
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k+2)%4] * i && y + dy[(k+2)%4] * i < N + 1 && 0 < x + dx[(k+2)%4] * i
						&& x + dx[(k+2)%4] * i < M + 1; i++) {
					if (map[y + dy[(k+2)%4] * i][x + dx[(k+2)%4] * i] == 6) {
						break;
					}
					check[y + dy[(k+2)%4] * i][x + dx[(k+2)%4] * i]--;
				}
			}
			if (list.get(cnt).type == 3) {
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]++;
				}
				dfs(cnt + 1);
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]--;
				}
			}
			if (list.get(cnt).type == 4) {
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 2) % 4] * i && y + dy[(k + 2) % 4] * i < N + 1
						&& 0 < x + dx[(k + 2) % 4] * i && x + dx[(k + 2) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i]++;
				}
				dfs(cnt + 1);
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 2) % 4] * i && y + dy[(k + 2) % 4] * i < N + 1
						&& 0 < x + dx[(k + 2) % 4] * i && x + dx[(k + 2) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i]--;
				}
			}
			if (list.get(cnt).type == 5) {
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 2) % 4] * i && y + dy[(k + 2) % 4] * i < N + 1
						&& 0 < x + dx[(k + 2) % 4] * i && x + dx[(k + 2) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i]++;
				}
				for (int i = 1; 0 < y + dy[(k + 3) % 4] * i && y + dy[(k + 3) % 4] * i < N + 1
						&& 0 < x + dx[(k + 3) % 4] * i && x + dx[(k + 3) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 3) % 4] * i][x + dx[(k + 3) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 3) % 4] * i][x + dx[(k + 3) % 4] * i]++;
				}
				dfs(cnt + 1);
				for (int i = 1; 0 < y + dy[k] * i && y + dy[k] * i < N + 1 && 0 < x + dx[k] * i
						&& x + dx[k] * i < M + 1; i++) {
					if (map[y + dy[k] * i][x + dx[k] * i] == 6) {
						break;
					}
					check[y + dy[k] * i][x + dx[k] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 1) % 4] * i && y + dy[(k + 1) % 4] * i < N + 1
						&& 0 < x + dx[(k + 1) % 4] * i && x + dx[(k + 1) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 1) % 4] * i][x + dx[(k + 1) % 4] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 2) % 4] * i && y + dy[(k + 2) % 4] * i < N + 1
						&& 0 < x + dx[(k + 2) % 4] * i && x + dx[(k + 2) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 2) % 4] * i][x + dx[(k + 2) % 4] * i]--;
				}
				for (int i = 1; 0 < y + dy[(k + 3) % 4] * i && y + dy[(k + 3) % 4] * i < N + 1
						&& 0 < x + dx[(k + 3) % 4] * i && x + dx[(k + 3) % 4] * i < M + 1; i++) {
					if (map[y + dy[(k + 3) % 4] * i][x + dx[(k + 3) % 4] * i] == 6) {
						break;
					}
					check[y + dy[(k + 3) % 4] * i][x + dx[(k + 3) % 4] * i]--;
				}
			}
		}
	}

	static class Camera {
		int y, x, type;

		public Camera(int y, int x, int type) {
			super();
			this.y = y;
			this.x = x;
			this.type = type;
		}
	}
}
