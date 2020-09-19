package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RGB거리 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int[] mapbefore = new int[3];
		int[] map = new int[3];

		int sol = 0;
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				for (int j = 0; j < 3; j++) {
					mapbefore[j] = sc.nextInt();
				}
			} else {
				for (int j = 0; j < 3; j++) {
					map[j] = sc.nextInt();
				}
				int[] temp = new int[3];
				temp[0] = Math.min(map[0] + mapbefore[1], map[0] + mapbefore[2]);
				temp[1] = Math.min(map[1] + mapbefore[0], map[1] + mapbefore[2]);
				temp[2] = Math.min(map[2] + mapbefore[0], map[2] + mapbefore[1]);
				mapbefore[0] = temp[0];
				mapbefore[1] = temp[1];
				mapbefore[2] = temp[2];
			}
		}
		sol = mapbefore[0];
		for (int i = 0; i < 2; i++) {
			sol = Math.min(sol, mapbefore[i + 1]);
		}
		System.out.println(sol);
	}
}
