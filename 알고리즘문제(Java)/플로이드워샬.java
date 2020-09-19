package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 플로이드워샬 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		final int M = Integer.MAX_VALUE;
		int[][] D = { { 0, M, 2, 3 }, { 4, 0, 1, 8 }, { 2, 5, 0, M }, { M, 9, 6, 0 } };

		for (int k = 0; k < D.length; k++) {
			for (int i = 0; i < D.length; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < D.length; j++) {
					if (k == j || i == j)
						continue;

					if (D[i][j] > D[i][k] + D[k][j] && D[i][k] != M && D[k][j] != M) {
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}

		for (int k = 0; k < D.length; k++) {
			for (int i = 0; i < D.length; i++) {
				System.out.print(D[k][i]+" ");
			}
			System.out.println();
		}

	}
}
