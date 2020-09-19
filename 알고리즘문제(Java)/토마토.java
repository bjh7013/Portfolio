package testProject;
/*
 * 백준 7569번 토마토
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토마토 {
	static int[] dy = { -1, 1, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int M = sc.nextInt(); // 가로k x
		int N = sc.nextInt(); // 세로j y
		int H = sc.nextInt(); // 높이i z
		int result = -1;
		int[][][] map = new int[H][N][M];
		boolean[][][] check = new boolean[H][N][M];
		Queue<Tomato> q = new LinkedList<>();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					map[i][j][k] = sc.nextInt();
					if (map[i][j][k] == 1) {
						check[i][j][k] = true;
						q.add(new Tomato(i, j, k, 0));
					}
				}
			}
		}

		while (!q.isEmpty()) {
			Tomato t = q.poll();
			result = t.day;
			for (int k = 0; k < 6; k++) {
				if(t.z+dz[k]<0 || t.z+dz[k]>=H || t.y+dy[k]<0 || t.y+dy[k]>=N || t.x+dx[k]<0 || t.x+dx[k]>=M) 
					continue;
				if(check[t.z+dz[k]][t.y+dy[k]][t.x+dx[k]])
					continue;
				if(map[t.z+dz[k]][t.y+dy[k]][t.x+dx[k]]==0) {
				map[t.z+dz[k]][t.y+dy[k]][t.x+dx[k]]=1;
				check[t.z+dz[k]][t.y+dy[k]][t.x+dx[k]]=true;
				q.add(new Tomato(t.z+dz[k],t.y+dy[k],t.x+dx[k],t.day+1));
				}
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[i][j][k] == 0) {
						result = -1;
					}
				}
			}
		}
		
		System.out.println(result);

	}

	static class Tomato {
		int z, y, x, day;

		public Tomato() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Tomato(int z, int y, int x, int day) {
			super();
			this.z = z;
			this.y = y;
			this.x = x;
			this.day = day;
		}

	}
}
