package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class 어른상어 {
	static int N;
	static int M;
	static int K;
	static int[][] map;
	static List<Integer>[][] priority;
	static int[][] smell;
	static int[][] time;
	static List<Shark> list;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int result = 0;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // N x N 격자
		M = sc.nextInt(); // M마리 상어 0 ~ M-1번
		K = sc.nextInt(); // K의 냄새 시간
		// 상,하,좌,우
		map = new int[N + 2][N + 2];
		priority = new ArrayList[M + 1][4];
		smell = new int[N + 2][N + 2];
		time = new int[N + 2][N + 2];
		list = new ArrayList<>();
		List<Shark> templist = new ArrayList<>();

		for (int i = 0; i < M + 1; i++) {
			for (int j = 0; j < 4; j++) {
				priority[i][j] = new ArrayList<>();
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] > 0) {
					smell[i][j] = map[i][j];
					time[i][j] = K;
					templist.add(new Shark(map[i][j], i, j, 0));
				}
			}
		}
		Collections.sort(templist, new Comparator<Shark>() {

			@Override
			public int compare(Shark arg0, Shark arg1) {
				if (arg0.index < arg1.index) {
					return -1;
				}else {
					return 1;
				}
			}
		});
		for (int i = 0; i < M; i++) {
			int num = sc.nextInt();
			list.add(new Shark(templist.get(i).index, templist.get(i).y, templist.get(i).x, num));
		}

		for (int i = 1; i < M + 1; i++) { // i번째 상어
			for (int j = 0; j < 4; j++) { // 상하좌우
				for (int k = 0; k < 4; k++) { // 우선순위
					priority[i][j].add(sc.nextInt());
				}
			}
		}
		dfs(0);
		System.out.println(result);
	}

	static void dfs(int cnt) {
//		if (cnt > 1) {
//			return;
//		}
		if (cnt > 1000) {
			result = -1;
			return;
		}
		int sharkcnt = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j] > 0)
					sharkcnt++;
			}
		}
		if (sharkcnt == 1) {
			result = cnt;
			return;
		}
		int listsize = list.size();
		for (int i = 0; i < listsize; i++) {
			boolean checkgo = false;
			boolean[] yesgo = new boolean[4];
			for (int k = 0; k < 4; k++) {
				if (list.get(i).y + dy[k] < 1 || list.get(i).y + dy[k] > N || list.get(i).x + dx[k] < 1
						|| list.get(i).x + dx[k] > N)
					continue;
				if (smell[list.get(i).y + dy[k]][list.get(i).x + dx[k]] > 0)
					continue;
				checkgo = true;
				yesgo[k] = true;
			}
			if (checkgo) { // 사방향에 냄새 없는 곳이 존재
				for (int k = 0; k < 4; k++) {
//					if (list.get(i).index == 5) {
//						System.out.println(list.get(i).bang - 1);
//						System.out.println(list.get(0));
//					}
					if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 1 && yesgo[0]) {
						if (map[list.get(i).y - 1][list.get(i).x] > 0
								&& map[list.get(i).y - 1][list.get(i).x] < list.get(i).index) {
							map[list.get(i).y][list.get(i).x] = 0;
							break;
						}
						map[list.get(i).y - 1][list.get(i).x] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 2 && yesgo[1]) {
						if (map[list.get(i).y + 1][list.get(i).x] > 0
								&& map[list.get(i).y + 1][list.get(i).x] < list.get(i).index) {
							map[list.get(i).y][list.get(i).x] = 0;
							break;
						}
						map[list.get(i).y + 1][list.get(i).x] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 3 && yesgo[2]) {
						if (map[list.get(i).y][list.get(i).x - 1] > 0
								&& map[list.get(i).y][list.get(i).x - 1] < list.get(i).index) {
							map[list.get(i).y][list.get(i).x] = 0;
							break;
						}
						map[list.get(i).y][list.get(i).x - 1] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 4 && yesgo[3]) {
						if (map[list.get(i).y][list.get(i).x + 1] > 0
								&& map[list.get(i).y][list.get(i).x + 1] < list.get(i).index) {
							map[list.get(i).y][list.get(i).x] = 0;
							break;
						}
						map[list.get(i).y][list.get(i).x + 1] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					}
				}

			} else { // 사방향에 냄새 없는 곳이 존재x
				for (int k = 0; k < 4; k++) {

					if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 1
							&& smell[list.get(i).y - 1][list.get(i).x] == list.get(i).index) {
						map[list.get(i).y - 1][list.get(i).x] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 2
							&& smell[list.get(i).y + 1][list.get(i).x] == list.get(i).index) {
						map[list.get(i).y + 1][list.get(i).x] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 3
							&& smell[list.get(i).y][list.get(i).x - 1] == list.get(i).index) {
						map[list.get(i).y][list.get(i).x - 1] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					} else if (priority[list.get(i).index][list.get(i).bang - 1].get(k) == 4
							&& smell[list.get(i).y][list.get(i).x + 1] == list.get(i).index) {
						map[list.get(i).y][list.get(i).x + 1] = list.get(i).index;
						map[list.get(i).y][list.get(i).x] = 0;
						break;
					}
				}
			}
		}
		// smell,time
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (time[i][j] > 0) {
					time[i][j]--;
					if (time[i][j] == 0) {
						smell[i][j] = 0;
					}
				}
			}
		}

		list.clear();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j] > 0) {
					smell[i][j] = map[i][j];
					time[i][j] = K;
					for (int k = 0; k < 4; k++) {
						if (i + dy[k] < 1 || i + dy[k] > N || j + dx[k] < 1 || j + dx[k] > N) {
							continue;
						}
						if (smell[i + dy[k]][j + dx[k]] == map[i][j] && time[i + dy[k]][j + dx[k]] == K - 1) {
							if (dy[k] == -1 && dx[k] == 0) {
								list.add(new Shark(map[i][j], i, j, 2));
							} else if (dy[k] == 1 && dx[k] == 0) {
								list.add(new Shark(map[i][j], i, j, 1));
							} else if (dy[k] == 0 && dx[k] == -1) {
								list.add(new Shark(map[i][j], i, j, 4));
							} else {
								list.add(new Shark(map[i][j], i, j, 3));
							}
							break;
						}
					}
				}
			}
		}
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		dfs(cnt + 1);
	}

	static class Shark {
		int index, y, x, bang;

		public Shark() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Shark(int index, int y, int x, int bang) {
			super();
			this.index = index;
			this.y = y;
			this.x = x;
			this.bang = bang;
		}

	}
}
