package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 파도반수열 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		long[] map = new long[101];
		map[1] = 1;
		map[2] = 1;
		map[3] = 1;
		for (int i = 4; i < 101; i++) {
			map[i] = map[i - 2] + map[i - 3];
		}
		for (int tc = 0; tc < T; tc++) {
			int N = sc.nextInt();
			
			System.out.println(map[N]);
		}
	}
}
