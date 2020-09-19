package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class 하나로프림복습 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] temp = new int[N][2];
			double[][] val = new double[N][N];
			for (int i = 0; i < N; i++) {
				temp[i][0] = sc.nextInt();
			}
			for (int i = 0; i < N; i++) {
				temp[i][1] = sc.nextInt();
			}
			double E = sc.nextDouble();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double dist = cal(temp[i][0], temp[j][0], temp[i][1], temp[j][1]);
					val[i][j] = dist * E;
					val[j][i] = dist * E;
				}
			}
			double[] key = new double[N];
			boolean[] check = new boolean[N];
			Arrays.fill(key, Double.MAX_VALUE);
			key[0] = 0;
			double min = Double.MAX_VALUE;
			int index = -1;
			for (int i = 0; i < N - 1; i++) {
				index = -1;
				min = Double.MAX_VALUE;
				for (int j = 0; j < N; j++) {
					if (!check[j] && key[j] < min) {
						min = key[j];
						index = j;
					}
				}
				for (int j = 0; j < N; j++) {
					if (!check[j] && val[index][j] != 0 && val[index][j] < key[j]) {
						key[j] = val[index][j];
					}
				}
				check[index] = true;

			}
			double result=0;
			for (int i = 0; i < N; i++) {
				result+=key[i];
			}
			System.out.println("#"+tc+" "+Math.round(result));
		}
	}

	static double cal(int valx1, int valx2, int valy1, int valy2) {
		return (Math.pow((valx1 - valx2), 2) + Math.pow((valy1 - valy2), 2));
	}
}
