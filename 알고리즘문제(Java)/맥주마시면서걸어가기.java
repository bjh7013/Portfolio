package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 맥주마시면서걸어가기 {
	static int N;
	static boolean[] check;
	static boolean sol;
	static List<Beer> list;
	static int[][] map;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			sol = false;
			map = new int[N + 2][N + 2];
			list = new ArrayList<>();
			check = new boolean[N + 3];
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					map[i][j] = 987654321;
				}
			}
			for (int i = 0; i < N + 2; i++) {
				list.add(new Beer(sc.nextInt(), sc.nextInt()));
			}
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j)
						continue;
					Beer current = list.get(i);
					Beer after = list.get(j);
					if (Math.abs(current.y - after.y) + Math.abs(current.x - after.x) <= 1000) {
						map[i][j] = 1;
					}

				}
			}
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						if (map[i][j] > map[i][k] + map[k][j]) {
							map[i][j] = map[i][k] + map[k][j];
						}
					}
				}
			}

			if (map[0][N + 1] > 0 && map[0][N + 1] < 987654321) {
				System.out.println("happy");
			} else {
				System.out.println("sad");
			}
		}
	}

	static class Beer {
		int y, x;

		public Beer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Beer(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}
}
