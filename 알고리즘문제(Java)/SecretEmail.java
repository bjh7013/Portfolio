package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class SecretEmail {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		LinkedList<Integer> list = new LinkedList<Integer>();
		int R = 0;
		int C = 0;
		String s = sc.nextLine();
		int N = s.length();
//		int N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			if (N % i == 0) {
				list.add(i);
//				System.out.println(i);
			}
		}
		int a = list.size();

		for (int i = 0; i < a; i++) {
			if (a % 2 == 0) {
				if (i == (a / 2) - 1)
					C = list.peek();
				if (i == (a / 2))
					R = list.peek();
			}
			if (a % 2 == 1) {
				if (i == (a / 2)) {
					R = list.peek();
					C = list.peek();
				}
			}
			list.remove();

		}
		
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(i * C + j);
			}
		}
		
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				System.out.print(map[j][i]);
			}
		}
	}
}