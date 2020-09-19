package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Maximummoney {
	private static final int Comparator = 0;
	static char[] ch;
	static char[] chtemp;
	static char maxnum = '\u0000';

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			String s = sc.next();
			int num = sc.nextInt();
			ch = new char[s.length()];
			chtemp = new char[s.length()];
			for (int i = 0; i < s.length(); i++) {
				ch[i] = s.charAt(i);
			}
			int kk = 0;
			max(0, num);
			Arrays.sort(chtemp);
			for (int i = 0; i < ch.length - 1; i++) {
				if ((chtemp[i] > 0) && (chtemp[i] == chtemp[i + 1])) {
					Arrays.sort(ch);
					kk = 1;
				}
			}
			if (kk == 1) {
				for (int i = ch.length-1; i >= 0; i--) {
					System.out.print(ch[i]);
				}
				System.out.println();
			} else {
				for (int i = 0; i < ch.length; i++) {
					System.out.print(ch[i]);
				}
				System.out.println();
			}
		}
	}

	static int temptt = 0;

	static public void max(int cnt, int num) {
		if (cnt == num) {
			return;
		}
		int tempi = 0;
		maxnum = '\u0000';

		for (int i = ch.length - 1; i > cnt-1; i--) {
			if (ch[i] > maxnum) {
				maxnum = ch[i];
				tempi = i;
			}

		}

		if (cnt >= ch.length) {
			if ((num - cnt) % 2 == 0)
				swap(ch.length - 1, ch.length - 2);
			return;
//			max(cnt + 1, num);
		} else {
			chtemp[cnt] = (char) maxnum;
			swap(cnt, tempi);
			max(cnt + 1, num);
		}
	}

	static public void swap(int i, int j) {
		char temp = 0;

		temp = ch[i];
		ch[i] = ch[j];
		ch[j] = temp;
	}
}
