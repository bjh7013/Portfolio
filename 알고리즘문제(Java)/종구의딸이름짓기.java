package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 종구의딸이름짓기 {
	static char[][] map;
	static boolean[][] b;
	static char[] val;
	static int N;
	static int M;
	static boolean check;
	static StringBuffer sb;
	static String sol;
	static Queue<BFS> q;;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new char[N + 2][M + 2];
			b = new boolean[N + 2][M + 2];
			val = new char[N + M + 1];
			q = new LinkedList<>();
			for (int i = 0; i < N + M + 1; i++) {
				val[i] = Character.MAX_VALUE;
			}
			sb = new StringBuffer("");
			sol = "";
			check = false;
			String[] s = new String[N];
			for (int i = 1; i <= N; i++) {
				s[i - 1] = sc.next();
				for (int j = 1; j <= M; j++) {
					map[i][j] = s[i - 1].charAt(j - 1);
				}
			}
			q.offer(new BFS(map[1][1], 1, 1, 1,0));
			while (!q.isEmpty()) {
				BFS bfs = q.poll();
				if (val[bfs.cnt - 1] != Character.MAX_VALUE && val[bfs.cnt - 1]!=bfs.before) {
					continue;
				}
				val[bfs.cnt] = (char) Math.min(val[bfs.cnt], bfs.ch);
				if (bfs.i == N && bfs.j == M) {
					System.out.print("#"+tc+" ");
					for (int k = 1; k < val.length; k++) {
						if (val[k] == Character.MAX_VALUE)
							break;
						System.out.print(val[k]);
					}
					break;
				}
				if(b[bfs.i][bfs.j])
					continue;
				b[bfs.i][bfs.j]=true;
				if (val[bfs.cnt] != map[bfs.i][bfs.j]) {
					continue;
				}
//				System.out.print(bfs.i + " " + bfs.j + "     ");
				if (map[bfs.i + 1][bfs.j] > map[bfs.i][bfs.j + 1]) {
					if (map[bfs.i][bfs.j + 1] != '\u0000')
						q.offer(new BFS(map[bfs.i][bfs.j + 1], bfs.i, bfs.j + 1, bfs.cnt + 1,map[bfs.i][bfs.j]));
					if (map[bfs.i][bfs.j + 1] == '\u0000')
						q.offer(new BFS(map[bfs.i + 1][bfs.j], bfs.i + 1, bfs.j, bfs.cnt + 1,map[bfs.i][bfs.j]));
				}
				if (map[bfs.i + 1][bfs.j] < map[bfs.i][bfs.j + 1]) {
					if (map[bfs.i + 1][bfs.j] != '\u0000')
						q.offer(new BFS(map[bfs.i + 1][bfs.j], bfs.i + 1, bfs.j, bfs.cnt + 1,map[bfs.i][bfs.j]));
					if (map[bfs.i + 1][bfs.j] == '\u0000')
						q.offer(new BFS(map[bfs.i][bfs.j + 1], bfs.i, bfs.j + 1, bfs.cnt + 1,map[bfs.i][bfs.j]));
				}
				if (map[bfs.i + 1][bfs.j] == map[bfs.i][bfs.j + 1]) {
					q.offer(new BFS(map[bfs.i][bfs.j + 1], bfs.i, bfs.j + 1, bfs.cnt + 1,map[bfs.i][bfs.j]));
					q.offer(new BFS(map[bfs.i + 1][bfs.j], bfs.i + 1, bfs.j, bfs.cnt + 1,map[bfs.i][bfs.j]));
				}
			}
//			System.out.println("#" + tc + " " + sol);
			System.out.println();
		}
	}

	static class BFS {
		char ch;
		int i, j;
		int cnt;
		int before;
		public BFS(char ch, int i, int j, int cnt, int before) {
			super();
			this.ch = ch;
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.before = before;
		}
	}

}
