package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 연구소3 {
	static int N;
	static int M;
	static int[][] map;
	static List<Virus> list;
	static boolean[] check;
	static int[] comb;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();// 연구소 크기
		M = sc.nextInt();// 바이러스 개수
		map = new int[N][N];
		list = new ArrayList<>();
		comb = new int[M];
		result = Integer.MAX_VALUE;
		int zerocnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					list.add(new Virus(i, j, 0));
				}
				if (map[i][j] == 0)
					zerocnt++;
			}
		}
		if (zerocnt == 0) {
			System.out.println("0");
		} else {
			check = new boolean[list.size()];
			Combination(0, 0);
			if (result == Integer.MAX_VALUE) {
				System.out.println("-1");
			} else {
				System.out.println(result);
			}
		}

	}

	static void Combination(int idx, int cnt) {
		if (cnt == M) {
			bfs();
			return;
		}

		for (int i = idx; i < list.size(); i++) {
			if (check[i])
				return;
			check[i] = true;
			comb[cnt] = i;
			Combination(i + 1, cnt + 1);
			check[i] = false;
		}
	}

	static void bfs() {
		Queue<Virus> q = new LinkedList<>();
		boolean[][] b = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			q.add(list.get(comb[i]));
		}
		int tempcnt = -1;
		while (!q.isEmpty()) {
			Virus virus = q.poll();
			if (b[virus.y][virus.x])
				continue;
			b[virus.y][virus.x] = true;
			if (map[virus.y][virus.x] != 2) {
				if (result <= virus.cnt)
					return;
				tempcnt = Math.max(tempcnt, virus.cnt);
			}
			for (int k = 0; k < 4; k++) {
				int x = virus.x + dx[k];
				int y = virus.y + dy[k];
				if (x < 0 || x > N - 1 || y < 0 || y > N - 1) {
					continue;
				}
				if (map[y][x] == 1)
					continue;
				if (b[y][x])
					continue;
				q.add(new Virus(y, x, virus.cnt + 1));
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!b[i][j] && (map[i][j] != 1)) {
					return;
				}
			}
		}
		System.out.println(tempcnt);
		result = Math.min(result, tempcnt);
	}

	static class Virus {
		int y, x, cnt;

		public Virus(int y, int x, int cnt) {
			super();
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}
	}
}
