package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class CrossWord {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		String a = sc.next();
		String b = sc.next();
		char[] cha = new char[a.length()];
		char[] chb = new char[b.length()];
		char[][] map = new char[b.length()][a.length()];
		cha = a.toCharArray();
		chb = b.toCharArray();
		int tempa = 100;
		int tempb = 100;

		for (int i = 0; i < b.length(); i++) {
			for (int j = 0; j < a.length(); j++) {
				map[i][j] = '.';
			}
		}

		for (int i = 0; i < b.length(); i++) {
			for (int j = 0; j < a.length(); j++) {
				if (chb[i] == cha[j]) {
					if (tempb > j) {
						tempb = j;
						tempa = i;
					}
					break;
				}
			}
		}

//		for (int i = 0; i < a.length(); i++) {
//			for (int j = 0; j < b.length(); j++) {
//				if (chb[j] == cha[i]) {
//					if (tempa > j) {
//						tempa = j;
//						tempb = i;
//					}
//					break;
//				}
//			}
//		}

		System.out.println(tempa + " " + tempb);

		for (int i = 0; i < b.length(); i++) {
			for (int j = tempb; j < tempb + 1; j++) {
				map[i][j] = chb[i];
			}
		}

		for (int i = tempa; i < tempa + 1; i++) {
			for (int j = 0; j < a.length(); j++) {
				map[i][j] = cha[j];
			}
		}

		for (int i = 0; i < b.length(); i++) {
			for (int j = 0; j < a.length(); j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}
}
