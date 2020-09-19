//정올 1329
package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SnailSqure {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int cnt = 1;
		int N = sc.nextInt();
		int temp = 0;
		int[][] map = new int[N][N];
		int round = 0;

		All: for (int i = 0; i < N; i++) {
			if (cnt > N * N)
				break;
			if (temp == 0) {
				for (int j = i; j < N - i; j++) {
					map[i][j] = cnt++;
				}

				temp = 1;

				continue;
			}
			if (temp == 1) {
				for (int j = i; j < N-i+1; j++) {
					map[j][N - i] = cnt++;
				}

				temp = 2;
				i--;

				continue;
			}
			if (temp == 2) {
				for (int j = i; j < N-i+1; j++) {
					map[N - i][N - j -1] = cnt++;

				}
				temp = 3;
				i--;
				round++;
				continue;
			}
			if (temp == 3) {
				for (int j = i; j < N - i; j++) {
					map[N - j - 1][i-1] = cnt++;
				}
				temp = 0;
				i--;
				continue;
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}