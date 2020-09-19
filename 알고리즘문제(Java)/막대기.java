package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class 막대기 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int val = sc.nextInt();// 목표길이
		int stick = 64; // 나무길이
		int current = 0; // 현재길이
		int count = 0; // 횟수

		while (true) {
			if (val == stick) { // 나무길이와 목표길이가 같으면
				count = 1;
				break;
			}
			stick /= 2;
			
			if (current + stick <= val) { // 나무반똥이 목표길이보다 작으면
				current += stick;
				count++;
			}
			if (val == current) { // 현재길이와 목표가 같으면
				break;
			}
		}

		System.out.println(count);
	}
}
