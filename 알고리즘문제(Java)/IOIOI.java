package testProject;
/*
 * 백준 5430번 AC
 * 
 * SSAFY_구미_3기 백정훈 코드
 * 문자열패턴문제
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IOIOI {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		char[] ch = sc.next().toCharArray();
		int result = 0;
		int count = 0;
		for (int i = 1; i < M-1; i++) {
			if (ch[i - 1] == 'I' && ch[i] == 'O' && ch[i + 1] == 'I') {
				count++;
				if (count == N) {
					count--;
					result++;
				}
				i++;
			} else {
				count = 0;
			}
		}
		System.out.println(result);
	}
}
