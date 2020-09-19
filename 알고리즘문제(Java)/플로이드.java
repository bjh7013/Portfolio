package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 플로이드 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N + 2][N + 2];
		int max = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
//				if (i == j)
//					continue;
				map[i][j] = max;
			}
		}
		for (int i = 0; i < M; i++) {
			int a =sc.nextInt();
			int b =sc.nextInt();
			int c =sc.nextInt();
			if(map[a][b]!=max && map[a][b]<c) {
				continue;
			}
			map[a][b] = c;
		}
//		for (int i = 1; i < N + 1; i++) {
//			for (int j = 1; j < N + 1; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				if (i == k)
					continue;
				for (int j = 1; j < N + 1; j++) {
					if (i == j || j == k)
						continue;
					if (map[i][j] > map[k][j] + map[i][k] && map[k][j] != max && map[i][k] != max)
						map[i][j] = map[k][j] + map[i][k];
				}
			}
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (map[i][j] == max) {
					System.out.print(0+" ");
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}
