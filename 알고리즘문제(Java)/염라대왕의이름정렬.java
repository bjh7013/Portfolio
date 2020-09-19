package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 염라대왕의이름정렬 {
	static int N;
	static String[] s;
	static List<String>[] list;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			s = new String[N + 1];
			list = new LinkedList[51];
			int maxlen=0;
			for (int i = 0; i < 51; i++) {
				list[i] = new LinkedList<>();
			}
			for (int i = 1; i < N + 1; i++) {
				s[i] = sc.next();
				if (!list[s[i].length()].contains(s[i]))
					list[s[i].length()].add(s[i]);
			}
			for (int i = 50; i > 0; i--) {
				if(!list[i].isEmpty()) {
					maxlen=i;
					break;
				}
			}
			for (int i = 1; i < maxlen+1; i++) {
				if(list[i].isEmpty())
					continue;
				Collections.sort(list[i], new Comparator<String>() {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						if (o1.length() < o2.length())
							return -1;
						if (o1.length() > o2.length())
							return 1;
						for (int i = 0; i < o1.length(); i++) {
							if (o1.charAt(i) < o2.charAt(i)) {
								return -1;
							}
							if (o1.charAt(i) > o2.charAt(i)) {
								return 1;
							}
						}
						return 0;
					}
				});
			}
			System.out.println("#" + tc);
			for (int i = 1; i < maxlen+1; i++) {
				if (!list[i].isEmpty()) {
					for (int j = 0; j < list[i].size(); j++) {
						System.out.println(list[i].get(j));
					}
				}
			}
		}
	}
}
