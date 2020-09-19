package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class 문자열압축 {
	static List<String> list;
	static Stack<String> stack;
	static int result;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		String s = sc.next();// 16

		result = Integer.MAX_VALUE;
		for (int i = 1; i < s.length() / 2 + 1; i++) {
			String stemp = s.substring(0, i);
			int tempresult = i;
			boolean check = false;
			int count = 0;
			int state = 0;
			for (int j = i; j + i < s.length() + 1; j += i) {
				if (!stemp.equals(s.substring(j, j + i))) {
					stemp = s.substring(j, j + i);
					tempresult += i;
					check = false;
					count = 0;
					state = 0;
				} else if (!check && stemp.equals(s.substring(j, j + i))) {
					tempresult++;
					count++;
					count++;
					check = true;
				} else {
					count++;
					if (state == 0 && count > 9) {
						tempresult++;
						state = 1;
					} else if (state == 1 && count > 99) {
						tempresult++;
						state = 2;
					} else if (state == 2 && count > 999) {
						tempresult++;
						state = 3;
					}
				}
			}
			if(count>90) {
				System.out.println(count);
			}

//			if (state == 0 && count >= 9) {
//				tempresult++;
//				state = 1;
//			} else if (state == 1 && count >= 99) {
//				tempresult++;
//				state = 2;
//			} else if (state == 2 && count >= 999) {
//				tempresult++;
//				state = 3;
//			}

			if (s.length() % i != 0) {
				tempresult += (s.length() % i);
			}
			result = Math.min(result, tempresult);
		}
		if (s.length() == 1) {
			System.out.println(1);
		} else {
			System.out.println(result);
		}
	}

}
