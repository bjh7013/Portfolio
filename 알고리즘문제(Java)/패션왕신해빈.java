package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class 패션왕신해빈 {
	static TreeMap<String, Integer> map;
	static int N;
	static List<String> list;
	static int count = 1;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 0; tc < T; tc++) {
			N = sc.nextInt();
			map = new TreeMap<>();
			list = new ArrayList<>();
			count = 1;
			for (int i = 0; i < N; i++) {
				sc.next();
				String s = sc.next();
				if (map.containsKey(s)) {
					map.put(s, map.get(s) + 1);
				} else {
					map.put(s, 1);
				}
			}
			if (N == 0) {
				System.out.println(0);
				continue;
			}
			int size = map.size();
			for (int i = 0; i < size; i++) {
				count *= (map.firstEntry().getValue() + 1);
				map.pollFirstEntry();
			}
			System.out.println(count - 1);
		}
	}

}
