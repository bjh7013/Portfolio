package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 백준 1541번 잃어버린 괄호
 * 
 * SSAFY_구미_3기 백정훈 코드
 */
public class 잃어버린괄호 {
	static int total;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		String[] s = sc.nextLine().split("\\-");
		total = 0;
		for (int i = 0; i < s.length; i++) {
			if (i == 0) {
				total += calc(s[i]);
			}else {
				total -= calc(s[i]);				
			}
		}
		System.out.println(total);
	}

	static int calc(String s) {
		String[] temps = s.split("\\+");
		int tempvalue = 0;
		for (int i = 0; i < temps.length; i++) {
			tempvalue += Integer.parseInt(temps[i]);
		}
		return tempvalue;
	}
}
