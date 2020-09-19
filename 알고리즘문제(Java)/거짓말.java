package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 거짓말 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int fact = sc.nextInt();
		boolean[] checkbefore = new boolean[N + 1];
		boolean[] check = new boolean[N + 1];
		List<Integer>[] list = new ArrayList[M + 1];
//		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < M + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < fact; i++) {
//			set.add(sc.nextInt());
			check[sc.nextInt()] = true;
		}

		for (int i = 0; i < M; i++) {
			int tempnum = sc.nextInt();
			for (int j = 0; j < tempnum; j++) {
				int temp = sc.nextInt();
				list[i].add(temp);
			}
		}
		int sol = 0;
		boolean clear = false;
		while (!clear) {
			clear = true;
			int count = 0;
			for (int i = 0; i < M; i++) {
				boolean tempcheck = false;
				for (int j = 0; j < list[i].size(); j++) {
					if (check[list[i].get(j)]) {
						tempcheck = true;
						break;
					}
				}
				if (tempcheck) {
					for (int j = 0; j < list[i].size(); j++) {
						check[list[i].get(j)] = true;
					}
				} else {
					count++;
				}
			}
			
			for (int i = 0; i < N + 1; i++) {
				if (check[i] != checkbefore[i]) {
					checkbefore[i] = check[i];
					clear = false;
				}
			}
			
			if (clear) {
				sol = count;
			}
		}
		
		System.out.println(sol);
	}
}
