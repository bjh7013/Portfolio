package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Water {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int cnt = 0;
			int temp = -1;
			char[][] map = new char[N + 2][N + 2];
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					map[i][j] = sc.next().charAt(0);
				}
			}
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (map[i][j] == 'W') {
						if (map[i - 1][j - 1] == 'W')
							cnt++;
						if (map[i - 1][j] == 'W')
							cnt++;
						if (map[i - 1][j + 1] == 'W')
							cnt++;
						if (map[i][j - 1] == 'W')
							cnt++;
						if (map[i][j + 1] == 'W')
							cnt++;
						if (map[i + 1][j - 1] == 'W')
							cnt++;
						if (map[i + 1][j] == 'W')
							cnt++;
						if (map[i + 1][j + 1] == 'W')
							cnt++;
						if (cnt == 0)
							cnt++;
						if (temp < cnt)
							temp = cnt;
					}
					cnt = 0;
				}
			}
			
			System.out.println("#"+tc+" "+temp);
		}
	}
}