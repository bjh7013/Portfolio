package testProject;

/*
 * 백준 2630번 색종이만들기
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 색종이만들기 {
	static int[][] map;
	static int[] count;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		map = new int[N + 1][N + 1];
		count = new int[2];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(1, 1, N, N);
		System.out.println(count[0]);
		System.out.println(count[1]);
	}

	static void dfs(int starty, int startx, int endy, int endx) {
		int value = map[starty][startx];
		boolean check = false;
		All: for (int i = starty; i < endy + 1; i++) {
			for (int j = startx; j < endx + 1; j++) {
				if (map[i][j] != value) {
					break All;
				}
			}
			if (i == endy) {
				check = true;
			}
		}
		if (check) {
			count[map[starty][startx]]++;
		} else {
			dfs(starty, startx, (starty + endy - 1) / 2, (startx + endx - 1) / 2); // y1~4 x1~4
			dfs(starty, (startx + endx - 1) / 2 + 1, (starty + endy - 1) / 2, endx); // y1~4 x5~8 
			dfs((starty + endy - 1) / 2 + 1, startx, endy, (startx + endx - 1) / 2); // y5~8 x1~4
			dfs((starty + endy - 1) / 2 + 1, (startx + endx - 1) / 2 + 1, endy, endx); // y5~8
		}

	}
}
