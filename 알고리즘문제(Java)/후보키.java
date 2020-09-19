package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class 후보키 {
	static int[] check;
	static int[] check2;
	static String[][] map;
	static int result;
	static boolean plus;

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		map = new String[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[i][j] = sc.next();
			}
		}
		result = 0;
		check = new int[5];
		check2 = new int[5];
		plus = false;
		Arrays.fill(check, Integer.MAX_VALUE);
		Arrays.fill(check2, Integer.MIN_VALUE);
		String[] temps = new String[map.length];
		for (int i = 0; i < map[0].length; i++) {
			temps = new String[map.length];
			for (int j = 0; j < temps.length; j++) {
				temps[j] = "";
			}
			for (int j = 0; j < map[0].length; j++) {
				dfs(j, temps, i, 0);
				plus = false;
			}
			for (int j = 0; j < map[0].length; j++) {
				if (check2[j] > -1) {
					check[j] = check2[j];
				}
			}
		}
		System.out.println(result);
		HashSet<String> set = new HashSet<>();
	}

	static void dfs(int current, String[] s, int max, int count) {
		if (count == max + 1 || current == map[0].length) {
			return;
		}
		if (check[current] < max)
			return;
		boolean clear = false;
		int tempresult = result;
		HashSet<String> set = new HashSet<>();
		String[] s1 = new String[map.length];
		for (int i = 0; i < map.length; i++) {
			s1[i] = s[i] + map[i][current];
		}
		for (int i = 0; i < map.length; i++) {
			if (!set.contains(s1[i])) {
				set.add(s1[i]);
			} else {
				clear = true;
			}
		}

		String[] ss = new String[map.length];
		ss = s1.clone();
		for (int i = current; i < map[0].length; i++) {
			dfs(i + 1, ss, max, count + 1);
			if (!clear) {
				System.out.println(current - 1);
				for (int j = 0; j < map.length; j++) {
					System.out.print(s[j] + " ");
				}
				System.out.println();
				plus = true;
				result++;
				break;
			}
		}

		if (plus && result != tempresult) {
			check2[current] = max;
		}
	}
}
