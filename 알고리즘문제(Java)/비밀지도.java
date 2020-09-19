package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 비밀지도 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] arr1 = new int[N];
		int[] arr2 = new int[N];
		char[][] map = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr1[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			arr2[i] = sc.nextInt();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < Integer.toBinaryString(arr1[i]).length(); j++) {
				if (Integer.toBinaryString(arr1[i]).charAt(j) == '1') {
					map[i][N - Integer.toBinaryString(arr1[i]).length() + j] = '#';
				} else {

				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < Integer.toBinaryString(arr2[i]).length(); j++) {
				if (Integer.toBinaryString(arr2[i]).charAt(j) == '1') {
					map[i][N - Integer.toBinaryString(arr2[i]).length() + j] = '#';
				} else {

				}
			}
		}
		String[] answer = new String[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != '#')
					map[i][j] = ' ';
			}
		}
		for (int i = 0; i < N; i++) {
			answer[i] = String.copyValueOf(map[i]);
		}
		System.out.println(answer[1]);

	}
}
