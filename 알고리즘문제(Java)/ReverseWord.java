package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReverseWord {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		String a;
		a = sc.nextLine();

		System.out.println(a);

		String[] b;
		String[] c = new String[2];

		StringBuffer words = new StringBuffer("");
		StringBuffer result = new StringBuffer("");

		if (a.contains("<")) {
			b = a.split("<");
			for (int j = 0; j < b.length; j++) {
				System.out.println(b[j]);
				if (b[j].indexOf(">") == -1)
				{
					words.append(b[j]);
					result.append(words.reverse());
					words.delete(0, words.length());
					continue;
				}
				
				c[0] = b[j].substring(0, b[j].indexOf(">") + 1);
				c[1] = b[j].substring(b[j].indexOf(">") + 1);
				
				result.append("<" + c[0]);
				String[] d = c[1].split(" ");
				for (int k = 0; k < d.length; k++) {
					words.append(d[k]);
					if (k < d.length - 1) {
						result.append(words.reverse() + " ");
						words.delete(0, words.length());
					}
					if (k == d.length - 1) {
						if(b[j+1]=="1")
						result.append(words.reverse());
						words.delete(0, words.length());
					}
				}

			}
		}
		if (!a.contains("<")) {
			b = a.split(" ");
			for (int i = 0; i < b.length; i++) {
				words.append(b[i]);
				if (i < b.length - 1)
					result.append(words.reverse() + " ");
				if (i == b.length - 1)
					result.append(words.reverse());
				words.delete(0, words.length());
			}
		}

		System.out.println(result);
		result.delete(0, result.length());
	}
}