//정올 1329
package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Star3 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		if ((N % 2 == 1) && (N <= 100)) {
			for (int j = 0; j < N / 2; j++) {
				for (int i = 0; i < j; i++) {
					System.out.print(" ");
				}
				for (int i = 0; i < j*2 + 1; i++) {
					System.out.print("*");
				}
				System.out.println();
			}
			for (int j = N / 2; j < N; j++) {
				for (int i = j+1; i < N; i++) {
					System.out.print(" ");
				}
				for (int i = j*2; i < N*2-1; i++) {
					System.out.print("*");
				}
				System.out.println();
			}
		}
		
		if (N > 100 ||  N % 2 == 0)
			System.out.println("INPUT ERROR!");

	}
}