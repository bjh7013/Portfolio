package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class LIS {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		
			int N = sc.nextInt();
			int[] lis = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				lis[i] = sc.nextInt();
			}
			int[] sol = new int[N + 1];
			int result = 0;
			// 1 3 2 5 4
			for (int i = 1; i < N + 1; i++) {
				sol[i] = 1;
				for (int j = 1; j < i; j++) {
					if (lis[i] > lis[j] && sol[j] + 1 > sol[i]) {
						sol[i] = sol[j] + 1;
					}
				}
				result = Math.max(result, sol[i]);
			}
			System.out.println(result);
		
	}

}
