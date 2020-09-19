package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class LIS2 {
	//시간 날 때 해보자!
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] lis = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				lis[i] = sc.nextInt();
			}
			int[] sol = new int[N + 1];
			int result = 0;
			// 1 3 2 5 4
			int cnt = 1;
			for (int i = 1; i < N + 1; i++) {
				if (i == 1)
					sol[cnt] = lis[i];
				else if (sol[cnt] > lis[i]) {
					sol[cnt] = lis[i];
				} else {
					sol[++cnt] = lis[i];
				}

			}
//			for (int i = 1; i < cnt+1; i++) {
//				System.out.print(sol[i]);
//			}
			System.out.println("#" + tc + " " + cnt);
		}
	}

}
