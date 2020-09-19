package testProject;

/*
 * 백준 1676번 팩토리얼0의개수
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 팩토리얼0의개수 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		long result = 1;
		int val = 0;
		int two = 0;
		int five = 0;

		for (int i = 1; i < N + 1; i++) {
			val = i;
			while (val % 2 == 0 || val % 5 == 0) {
				if (val % 2 == 0) {
					val /= 2;
					two++;
				}
				if (val % 5 == 0) {
					val /= 5;
					five++;
				}
			}
		}
		System.out.println(five);
	}
}
