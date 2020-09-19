package testProject;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Lotto {
	static int[] temp;
	static boolean[] b;
	static final int LOTTO = 6;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		while (true) {
			int N = sc.nextInt();
			if (N == 0) {
				break;
			}
			int[] num = new int[N];
			temp = new int[LOTTO + 1];
			b = new boolean[N + 1];
			for (int i = 0; i < N; i++) {
				num[i] = sc.nextInt();
			}
			Arrays.sort(num);

			dfs(num, N, 0);
			System.out.println();
		}
		sc.close();
	}

	static public void dfs(int[] num, int N, int cnt)  {
		if (cnt == LOTTO) {
			for (int i = 0; i < LOTTO; i++) {
				System.out.print(temp[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < N; i++) {
			if (b[i])
				continue;
			for (int j = 0; j <= i; j++) {
				b[j] = true;
			}
			temp[cnt] = num[i];
			dfs(num, N, cnt + 1);
			for (int j = 0; j <= i; j++) {
				b[j] = false;
			}
		}
	}
}
