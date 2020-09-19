package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 보석쇼핑 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		String[] s = new String[10];

		HashSet<String> set = new HashSet<>();
		HashMap<String, Integer> map = new HashMap<>();
		Queue<String> q = new LinkedList<>();

		for (int i = 0; i < s.length; i++) {
			s[i] = sc.next();
			set.add(s[i]);
		}

		int start = 0;
		int savestart = 0;
		int solleng = 987654321;
		boolean check = false;
		for (int i = 0; i < s.length; i++) {
			q.add(s[i]);
			if (!map.containsKey(s[i])) {
				map.put(s[i], 1);
			} else {
				map.put(s[i], map.get(s[i]) + 1);
			}
			while (true) {
				String temps = q.peek();
				if (map.get(temps) > 1) {
					map.put(temps, map.get(temps) - 1);
					q.poll();
					savestart++;
				} else {
					break;
				}
			}
			if (set.size() == map.size()) {
				if (solleng > q.size()) {
					start = savestart;
					solleng = q.size();
				}
			}

		}

		System.out.println((start + 1) + " " + (start + solleng));

	}
}
