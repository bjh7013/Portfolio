package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gam {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		StringBuffer val = new StringBuffer("");
		String[] vals = new String[T];
		int[] valr = new int[T];
		int[] valreal = new int[T];
		int[] result = new int[T];
		String a = "";
		String b = "";
		boolean tt=false;

		for (int i = 0; i < T; i++) {
			a = sc.next();
			val = val.append(a);
			vals[i] = val.toString();
			valr[i] = Integer.valueOf(vals[i]);
			vals[i] = (val.reverse()).toString();
			valreal[i] = Integer.valueOf(vals[i]);
			// System.out.println(valr[i] + " " + valreal[i]);
			val.delete(0, val.length());
		}
		for (int i = 0; i < T; i++) {
			result[i] = valr[i] * valreal[i];
			b = a.valueOf(result[i]);
			char[] vale = new char[b.length()];
			
			for (int j = 0; j < b.length(); j++) {
				vale[j] = b.charAt(j);
			}
			
			for (int j = 0; j < b.length() - 1; j++) {
				if (vale[j] >= vale[j + 1]) {
					tt = true;
				} else {
					tt = false;
					break;
				}
			}
			if (tt)
				System.out.println("#" + (i + 1) + " True");
			else
				System.out.println("#" + (i + 1) + " False");
		}

	}
}
