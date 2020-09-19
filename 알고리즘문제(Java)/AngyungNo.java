package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AngyungNo {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		String left;
		String right;
		StringBuffer l = new StringBuffer("");
		StringBuffer r = new StringBuffer("");

		for (int i = 0; i < TC; i++) {
			left = sc.next();
			right = sc.next();
			l.delete(0, l.length());
			r.delete(0, r.length());
			int cntlt = 0;
			int cntrt = 0;
			int cntlo = 0;
			int cntro = 0;

			for (int j = 0; j < left.length(); j++) {
				if (left.charAt(j) == 'B') {
					l.append("B");
				} else if (left.charAt(j) == 'A' || left.charAt(j) == 'D' || left.charAt(j) == 'O'
						|| left.charAt(j) == 'P' || left.charAt(j) == 'Q' || left.charAt(j) == 'R') {
					l.append("A");
				} else
					l.append("C");

			}
			for (int j = 0; j < right.length(); j++) {

				if (right.charAt(j) == 'B') {
					r.append("B");
				} else if (right.charAt(j) == 'A' || right.charAt(j) == 'D' || right.charAt(j) == 'O'
						|| right.charAt(j) == 'P' || right.charAt(j) == 'Q' || right.charAt(j) == 'R') {
					r.append("A");
				} else
					r.append("C");
			}
//			System.out.println(l);
//			System.out.println(r);
			left = l.toString();
			right = r.toString();
			if (left.equals(right))
				System.out.println("#" + (i + 1) + " " + "SAME");

			if (!left.equals(right))
				System.out.println("#" + (i + 1) + " " + "DIFF");
		}

	}
}
