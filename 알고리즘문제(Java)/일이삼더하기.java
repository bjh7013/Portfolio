package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 백준 9095번 1,2,3더하기
 * 
 * SSAFY_구미_3기 백정훈 코드
 * 
 */
public class 일이삼더하기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		int[] map = new int[12];
		map[1] = 1;
		map[2] = 2;
		map[3] = 4;
		for (int i = 4; i < 11; i++) {
			map[i] = map[i - 1] + map[i - 2] + map[i - 3];
		}
		for (int tc = 0; tc < T; tc++) {
			System.out.println(map[sc.nextInt()]);
		}
	}
}
