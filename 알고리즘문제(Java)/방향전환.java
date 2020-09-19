package testProject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 방향전환 {
	static boolean[][][] b;
	static int[][][] map;
	static int[] dx = { -1, 1 };
	static int[] dy = { -1, 1 };

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("test.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			int y1 = sc.nextInt();
			int x1 = sc.nextInt();
			int y2 = sc.nextInt();
			int x2 = sc.nextInt();

			int tempy = Math.abs(y2 - y1);
			int tempx = Math.abs(x2 - x1);
			if (tempx == tempy) {
				System.out.println("#" + tc + " " + tempx * 2);
				continue;
			}

			int max = Math.max(tempx, tempy);
			int min = Math.min(tempx, tempy);
			int result = max - min;

			if (result % 2 == 1) {
				System.out.println("#" + tc + " " + (max * 2 - 1));
			} else {
				System.out.println("#" + tc + " " + max * 2);
			}
		}
	}

}
