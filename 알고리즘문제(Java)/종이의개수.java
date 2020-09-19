package testProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1780번 종이의 개수
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class 종이의개수 {
	static int[][] map;
	static int N;
	static int[] count;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("test.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine());
		map = new int[N + 1][N + 1];
		count = new int[4];
		for (int i = 1; i < N + 1; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1, 1, N, N);
		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);
	}

	static void dfs(int starty, int startx, int endy, int endx) {
		int tempval = map[starty][startx];
//		System.out.println(start+"와"+end);
		for (int i = starty; i < endy + 1; i++) {
			for (int j = startx; j < endx + 1; j++) {
				if (tempval != map[i][j]) {
					for (int a = 0; a < 3; a++) {
						for (int b = 0; b < 3; b++) {
							dfs(starty+((endy-starty+1)*b)/3, startx+((endx-startx+1)*a)/3, starty+((endy-starty+1)*(b+1))/3 - 1, startx+((endx-startx+1)*(a+1))/3 - 1);
						}
					}
					return;
				}
			}
		}
//		System.out.println(tempval);
		count[tempval + 1]++;
		return;
	}

}