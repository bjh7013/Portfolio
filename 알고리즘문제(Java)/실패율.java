package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class 실패율 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		List<Integer> list = new LinkedList<>();
		List<Num> list2 = new LinkedList<>();
		int[] map = new int[N + 2];
		int[] maps = new int[N + 2];
		for (int i = 0; i < 8; i++) {
			int val = sc.nextInt();
			maps[val]++;
		}
		double number = 8;
		for (int i = 1; i < N + 1; i++) {
			list2.add(new Num(maps[i] / number, i));
			number -= maps[i];
		}

		Collections.sort(list2, new Comparator<Num>() {
			@Override
			public int compare(Num o1, Num o2) {
				if (o1.value > o2.value) {
					return -1;
				} else if (o1.value < o2.value) {
					return 1;
				} else {
					return 0;
				}
			}
		});

		for (int i = 0; i < N; i++) {
			map[i] = list2.get(i).index;
			System.out.print(map[i] + " ");
		}

	}

	static class Num {
		double value;
		int index;

		public Num() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Num(double value, int index) {
			super();
			this.value = value;
			this.index = index;
		}

	}
}
