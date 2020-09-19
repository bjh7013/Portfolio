package testProject;

/*
 * 백준 1012번 유기농 배추
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 유기농배추 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int M = sc.nextInt(); // 가로
			int N = sc.nextInt(); // 세로
			int K = sc.nextInt(); // 갯수
			int[][] map = new int[N][M];
			boolean[][] check = new boolean[N][M];
			Queue<Vegetable> q = new LinkedList<>();
			int cnt=0;

			for (int i = 0; i < K; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[y][x] = 1;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (check[i][j])
						continue;
					if (map[i][j] == 1) {
						q.add(new Vegetable(i, j));
						check[i][j]=true;
						while (!q.isEmpty()) {
							Vegetable V = q.poll();

							for (int k = 0; k < 4; k++) {
								if (V.y + dy[k] < 0 || V.y + dy[k] > N - 1 || V.x + dx[k] < 0 || V.x + dx[k] > M - 1)
									continue;
								if(map[V.y+dy[k]][V.x+dx[k]]==0)
									continue;
								if(check[V.y+dy[k]][V.x+dx[k]])
									continue;
								check[V.y+dy[k]][V.x+dx[k]]=true;
								q.add(new Vegetable(V.y+dy[k],V.x+dx[k]));
							}
						}
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

	static class Vegetable {
		int y, x;

		public Vegetable(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		public Vegetable() {
			super();
			// TODO Auto-generated constructor stub
		}

	}
}
