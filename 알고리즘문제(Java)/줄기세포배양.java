package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 줄기세포배양 {
	static int N;
	static int M;
	static int K;
	static boolean[][] check;
	static int[][][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			s = br.readLine();
			st = new StringTokenizer(s);
			N = Integer.parseInt(st.nextToken()); // 행 1~50
			M = Integer.parseInt(st.nextToken()); // 열 1~50
			K = Integer.parseInt(st.nextToken()); // 배양시간(1~300)
			// 줄기 세포 생명력 X는 1~10,무한한 번식장소
			int y=654;
			int x=654;
			int y1=300;
			int x1=300;
			map = new int[2][y][x];
			check = new boolean[y][x];
			Queue<Biology> q = new LinkedList<>();
			for (int i = 1; i < N + 1; i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				for (int j = 1; j < M + 1; j++) {
					int temp = Integer.parseInt(st.nextToken());
					;
					map[0][i + y1][j + x1] = temp;
					if (temp > 0) {
						q.add(new Biology(i + y1, j + x1, temp, temp, 0, 0));// 0은 비활성 1은 활성
					}
				}
			}
//			1. 모든방향 무한 번식
//			2. 생명력이 X는 X시간동안 비활성, 이후 활성
//			3. 활성시간이X시간 + 번식가능 이후 사망
//			4. 사망한 곳은 못 감
//			5. 생명체가 이미 존재하면 번식못함
//			6. 생명체가 동시에 번식 가능시 더 생명력 높은것이 감
			while (!q.isEmpty()) {
				Biology bio = q.poll();
//				System.out.println((bio.y-400)+" "+(bio.x-400)+" "+bio.life);
				if (bio.day == K)
					break;
				if (map[0][bio.y][bio.x] != bio.life)
					continue;
				check[bio.y][bio.x] = true;
				if (bio.status == 0) {
					if (bio.curlife == 1) {
						q.add(new Biology(bio.y, bio.x, bio.life, bio.life, bio.day + 1, 1));
					} else {
						q.add(new Biology(bio.y, bio.x, bio.life, bio.curlife - 1, bio.day + 1, 0));
					}
					continue;
				}
				if (bio.status == 1 && bio.curlife == bio.life) {
					if (bio.life != 1) {
						q.add(new Biology(bio.y, bio.x, bio.life, bio.curlife - 1, bio.day + 1, 1));
					}
					for (int i = 0; i < 4; i++) {
						if (check[bio.y + dy[i]][bio.x + dx[i]])
							continue;
						if (map[0][bio.y + dy[i]][bio.x + dx[i]] > 0
								&& map[0][bio.y + dy[i]][bio.x + dx[i]] >= bio.life) {
							continue;
						} else {
							if (map[1][bio.y + dy[i]][bio.x + dx[i]] > 0
									&& bio.day != map[1][bio.y + dy[i]][bio.x + dx[i]]) {
								continue;
							}
							map[0][bio.y + dy[i]][bio.x + dx[i]] = bio.life;
							map[1][bio.y + dy[i]][bio.x + dx[i]] = bio.day;
						}
						q.add(new Biology(bio.y + dy[i], bio.x + dx[i], bio.life, bio.life, bio.day + 1, 0));
					}
					if (bio.life == 1) {
						map[0][bio.y][bio.x] = -1;
					}
					continue;
				}
				if (bio.status == 1 && bio.curlife != 1) {
					q.add(new Biology(bio.y, bio.x, bio.life, bio.curlife - 1, bio.day + 1, 1));
					continue;
				}
				map[0][bio.y][bio.x] = -1;
			}
			int cnt = 0;
			for (int i = 1; i < y; i++) {
				for (int j = 1; j < x; j++) {
					if (map[0][i][j] > 0)
						cnt++;
				}
			}

			System.out.println("#" + tc + " " + cnt);

		}
	}

	static class Biology {
		int y, x, life, curlife, day, status;

		public Biology(int y, int x, int life, int curlife, int day, int status) {
			super();
			this.y = y;
			this.x = x;
			this.life = life;
			this.curlife = curlife;
			this.day = day;
			this.status = status;
		}

	}
}
